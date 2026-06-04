
/**
 * ============================================================
 * CEEB SID — app.js
 * Multilingual Carousel + Image Fallback System
 * Linux + Windows compatible
 * ============================================================
 *
 * IMAGE PRIORITY:
 *
 * 1. images/carousel/index/fr/2.webp
 * 2. images/carousel/index/2.webp
 * 3. images/carousel/index/en/2.webp
 *
 * ============================================================
 */

/* ============================================================
   CONFIG
   ============================================================ */

const CEEB_CONFIG = {

  langs: [
    'fr',
    'en',
    'it',
    'pl',
    'tr',
    'es',
    'de'
  ],

  fallbackLang: 'en',

  storageKey: 'ceeb_lang',

  carouselAutoplay: 5000
};


/* ============================================================
   GLOBAL STATE
   ============================================================ */

let currentLang = CEEB_CONFIG.fallbackLang;
window.currentLang = currentLang;

let translations = {};


/* ============================================================
   HELPERS
   ============================================================ */

function getLangFromUrl() {

  const params = new URLSearchParams(
    window.location.search
  );

  const lang = params.get('lang');

  return CEEB_CONFIG.langs.includes(lang)
    ? lang
    : null;
}


function getPageRelativeLangPrefix() {
  const parts = window.location.pathname
    .split('/')
    .filter(
      p =>
        p &&
        !/\.html?$/.test(p.toLowerCase())
    );

  const depth = parts.length;

  return depth > 0
    ? '../'.repeat(depth)
    : './';
}

function getLangFilePrefix() {
  if (typeof window.ceebLangPrefix === 'string') {
    return window.ceebLangPrefix;
  }

  if (typeof window.getPageRelativeLangPrefix === 'function') {
    return window.getPageRelativeLangPrefix();
  }

  return getPageRelativeLangPrefix();
}

function getPageName() {

  const dataPage =
    document.body.getAttribute('data-page');

  if (dataPage) {
    return dataPage;
  }

  const parts = window.location.pathname
    .split('/')
    .filter(Boolean);

  if (parts.length === 0) {
    return 'index';
  }

  const last =
    parts[parts.length - 1].toLowerCase();

  if (/^index\.(html?|htm?)$/.test(last)) {

    return parts.length >= 2
      ? parts[parts.length - 2]
      : 'index';
  }

  if (/\.(html?|htm?)$/.test(last)) {

    return parts.length >= 2
      ? parts[parts.length - 2]
      : last.replace(/\.(html?|htm?)$/, '');
  }

  return parts[0] || 'index';
}


/* ============================================================
   LOCALIZED IMAGE PATH
   ============================================================ */

function buildLocalizedImagePath(src, lang, filename) {

  if (!src || !lang) {
    return src;
  }

  // normalize windows slashes
  src = String(src).replace(/\\/g, '/');

  let prefix = '';

  // preserve prefixes
  if (src.startsWith('./')) {
    prefix = './';
    src = src.substring(2);
  }
  else if (src.startsWith('../')) {
    prefix = '../';
    src = src.substring(3);
  }
  else if (src.startsWith('/')) {
    prefix = '/';
    src = src.substring(1);
  }

  const parts = src
    .split('/')
    .filter(Boolean);

  const carouselIndex =
    parts.indexOf('carousel');

  // not carousel
  if (carouselIndex === -1) {
    return prefix + parts.join('/');
  }

  // invalid structure
  if (parts.length < carouselIndex + 3) {
    return prefix + parts.join('/');
  }

  const originalFilename = parts.pop();
  const maybeLang =
    parts[parts.length - 1];

  if (
    CEEB_CONFIG.langs.includes(maybeLang)
  ) {
    parts.pop();
  }

  const finalFilename = filename || originalFilename;

  const finalPath = [
    ...parts,
    lang,
    finalFilename
  ].join('/');

  return prefix + finalPath;
}

function getCarouselOriginalPath(src) {
  if (!src) return src;

  src = String(src).replace(/\\/g, '/');

  let prefix = '';

  if (src.startsWith('./')) {
    prefix = './';
    src = src.substring(2);
  }
  else if (src.startsWith('../')) {
    prefix = '../';
    src = src.substring(3);
  }
  else if (src.startsWith('/')) {
    prefix = '/';
    src = src.substring(1);
  }

  const parts = src
    .split('/')
    .filter(Boolean);

  const carouselIndex =
    parts.indexOf('carousel');

  if (carouselIndex === -1 || parts.length < carouselIndex + 3) {
    return prefix + parts.join('/');
  }

  const filename = parts.pop();
  const maybeLang = parts[parts.length - 1];

  if (
    CEEB_CONFIG.langs.includes(maybeLang)
  ) {
    parts.pop();
  }

  return prefix + [...parts, filename].join('/');
}


/* ============================================================
   IMAGE FALLBACK LOGIC
   ============================================================ */

function setupImageFallback(img, lang) {

  if (!img) return;

  const currentSrc = img.getAttribute('src');

  const originalSrc =
    img.dataset.originalSrc ||
    getCarouselOriginalPath(currentSrc);

  if (!img.dataset.originalSrc) {
    img.dataset.originalSrc = originalSrc;
  }

  const filename =
    img.dataset.filename ||
    img.getAttribute('data-filename');

  const fallbackLang =
    CEEB_CONFIG.fallbackLang;

  const candidates = [];

  if (filename) {
    candidates.push(
      buildLocalizedImagePath(
        originalSrc,
        lang,
        filename
      )
    );
  }

  candidates.push(
    buildLocalizedImagePath(
      originalSrc,
      lang
    )
  );

  candidates.push(originalSrc);

  if (lang !== fallbackLang) {
    if (filename) {
      candidates.push(
        buildLocalizedImagePath(
          originalSrc,
          fallbackLang,
          filename
        )
      );
    }

    candidates.push(
      buildLocalizedImagePath(
        originalSrc,
        fallbackLang
      )
    );
  }

  const uniqueCandidates = Array.from(
    new Set(candidates.filter(Boolean))
  );

  let attemptIndex = 0;

  img.onerror = function () {
    attemptIndex += 1;

    if (attemptIndex < uniqueCandidates.length) {
      const nextSrc = uniqueCandidates[attemptIndex];
      console.warn(
        '[IMG] fallback to:',
        nextSrc,
        'for original:',
        originalSrc
      );
      img.src = nextSrc;
      return;
    }

    console.warn(
      '[IMG] all fallbacks failed for:',
      originalSrc
    );
    img.onerror = null;
  };

  img.src = uniqueCandidates[0];
}


/* ============================================================
   UPDATE ALL LANGUAGE IMAGES
   ============================================================ */

function updateLanguageAwareImages(lang) {

  document.querySelectorAll('img')
    .forEach(img => {

      const src =
        img.getAttribute('src');

      if (!src) return;

      // only carousel images
      if (
        !src.includes('images/carousel/')
      ) {
        return;
      }

      setupImageFallback(img, lang);
    });
}


/* ============================================================
   TRANSLATIONS
   ============================================================ */

async function loadTranslations(lang) {

  if (translations[lang]) {
    return translations[lang];
  }

  const translationUrl =
    `${getLangFilePrefix()}lang/${lang}.json`;

  try {

    const response = await fetch(
      translationUrl
    );

    if (!response.ok) {

      throw new Error(
        `HTTP ${response.status}`
      );
    }

    translations[lang] =
      await response.json();

    return translations[lang];
  }
  catch (e) {

    console.warn(
      `Translation ${lang} missing`
    );

    if (
      lang !==
      CEEB_CONFIG.fallbackLang
    ) {

      return loadTranslations(
        CEEB_CONFIG.fallbackLang
      );
    }

    return {};
  }
}


function getNestedValue(obj, path) {

  if (!obj || !path) {
    return undefined;
  }

  return path
    .split('.')
    .reduce((acc, key) => {

      return (
        acc &&
        acc[key] !== undefined
      )
        ? acc[key]
        : undefined;

    }, obj);
}


/* ============================================================
   APPLY TRANSLATIONS
   ============================================================ */

function applyTranslations(t) {

  // text
  document
    .querySelectorAll('[data-i18n]')
    .forEach(el => {

      const key =
        el.getAttribute('data-i18n');

      const value =
        getNestedValue(t, key);

      if (value !== undefined) {

        el.innerHTML = value;
      }
    });

  // placeholders
  document
    .querySelectorAll(
      '[data-i18n-placeholder]'
    )
    .forEach(el => {

      const key =
        el.getAttribute(
          'data-i18n-placeholder'
        );

      const value =
        getNestedValue(t, key);

      if (value !== undefined) {

        el.placeholder = value;
      }
    });

  document.documentElement.lang =
    currentLang;
}


/* ============================================================
   CHANGE LANGUAGE
   ============================================================ */

async function changeLang(lang) {

  if (
    !CEEB_CONFIG.langs.includes(lang)
  ) {
    return;
  }

  currentLang = lang;
  window.currentLang = currentLang;

  localStorage.setItem(
    CEEB_CONFIG.storageKey,
    lang
  );

  const t =
    await loadTranslations(lang);

  applyTranslations(t);

  // active button
  document
    .querySelectorAll('.lang-btn')
    .forEach(btn => {

      btn.classList.toggle(
        'active',
        btn.dataset.lang === lang
      );
    });

  // update images
  updateLanguageAwareImages(lang);

  document.dispatchEvent(
    new CustomEvent(
      'ceeb:langChanged',
      {
        detail: { lang }
      }
    )
  );
}


/* ============================================================
   CAROUSEL
   ============================================================ */

function initCarousel() {

  const carousel =
    document.querySelector(
      '.hero-carousel'
    );

  if (!carousel) return;

  const track =
    carousel.querySelector(
      '.carousel-track'
    );

  const slides =
    carousel.querySelectorAll(
      '.carousel-slide'
    );

  const prevBtn =
    carousel.querySelector(
      '.carousel-btn.prev'
    );

  const nextBtn =
    carousel.querySelector(
      '.carousel-btn.next'
    );

  const dots =
    carousel.querySelectorAll(
      '.carousel-dot'
    );

  let current = 0;

  const total = slides.length;

  function goTo(index) {

    current =
      (index + total) % total;

    track.style.transform =
      `translateX(-${current * 100}%)`;

    dots.forEach((dot, i) => {

      dot.classList.toggle(
        'active',
        i === current
      );
    });
  }

  // next
  nextBtn?.addEventListener(
    'click',
    () => {
      goTo(current + 1);
    }
  );

  // prev
  prevBtn?.addEventListener(
    'click',
    () => {
      goTo(current - 1);
    }
  );

  // dots
  dots.forEach((dot, i) => {

    dot.addEventListener(
      'click',
      () => {
        goTo(i);
      }
    );
  });

  // autoplay
  let autoplay = setInterval(() => {

    goTo(current + 1);

  }, CEEB_CONFIG.carouselAutoplay);

  carousel.addEventListener(
    'mouseenter',
    () => {

      clearInterval(autoplay);
    }
  );

  carousel.addEventListener(
    'mouseleave',
    () => {

      autoplay = setInterval(() => {

        goTo(current + 1);

      }, CEEB_CONFIG.carouselAutoplay);
    }
  );

  // swipe support
  let startX = 0;

  carousel.addEventListener(
    'touchstart',
    e => {

      startX =
        e.touches[0].clientX;
    },
    { passive: true }
  );

  carousel.addEventListener(
    'touchend',
    e => {

      const diff =
        startX -
        e.changedTouches[0].clientX;

      if (Math.abs(diff) > 50) {

        if (diff > 0) {

          goTo(current + 1);
        }
        else {

          goTo(current - 1);
        }
      }
    },
    { passive: true }
  );

  goTo(0);
}


/* ============================================================
   DETECT DEFAULT LANGUAGE
   ============================================================ */

async function detectDefaultLang() {

  // URL parameter takes top priority
  const urlLang =
    getLangFromUrl();

  if (urlLang) {
    return urlLang;
  }

  // Browser locale based on where the page is opened
  const browserLang =
    (navigator.languages || [navigator.language])
      .find(Boolean)
      ?.split('-')[0]
      ?.toLowerCase();

  if (
    browserLang &&
    CEEB_CONFIG.langs.includes(
      browserLang
    )
  ) {

    return browserLang;
  }

  // saved choice as fallback when browser locale is unavailable
  const saved =
    localStorage.getItem(
      CEEB_CONFIG.storageKey
    );

  if (
    saved &&
    CEEB_CONFIG.langs.includes(saved)
  ) {

    return saved;
  }

  return CEEB_CONFIG.fallbackLang;
}


/* ============================================================
   INIT
   ============================================================ */

document.addEventListener(
  'DOMContentLoaded',
  async () => {

    // detect language
    currentLang =
      await detectDefaultLang();

    // load translations
    const t =
      await loadTranslations(
        currentLang
      );

    applyTranslations(t);

    // language buttons
    document
      .querySelectorAll('.lang-btn')
      .forEach(btn => {

        btn.addEventListener(
          'click',
          () => {

            const lang =
              btn.dataset.lang;

            changeLang(lang);
          }
        );
      });

    // localized images
    updateLanguageAwareImages(
      currentLang
    );

    // carousel
    initCarousel();

    console.log(
      '[CEEB] initialized:',
      currentLang
    );
  }
);

