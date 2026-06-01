/* =========================================
   GLOBAL LANGUAGE VARIABLE
========================================= */

let currentLanguage = "en";

/* =========================================
   PATH HELPERS
   Uses a script-based base path for compatibility
========================================= */

const appBasePath = (() => {
    let script = document.currentScript;

    if (!script) {
        const scripts = document.getElementsByTagName("script");
        script = scripts[scripts.length - 1];
    }

    if (!script || !script.src) {
        return "./";
    }

    try {
        const scriptUrl = new URL(script.src, window.location.href);
        return scriptUrl.href.replace(/\/js\/app\.js$/, "/");
    }
    catch (error) {
        return "./";
    }
})();

function getAppBasePath() {
    return appBasePath;
}

function getLanguageFilePath(lang) {
    return `${getAppBasePath()}lang/${lang}.json`;
}

function getCarouselImagePath(lang, imageNumber) {
    return `${getAppBasePath()}images/carousel/${lang}/${imageNumber}.png`;
}

function getDefaultCarouselImagePath(imageNumber) {
    return `${getAppBasePath()}images/carousel/${imageNumber}.png`;
}

/* =========================================
   LOAD LANGUAGE
========================================= */

async function loadLanguage(lang) {

    try {

        const response = await fetch(
            getLanguageFilePath(lang),
            {
                cache: "no-cache"
            }
        );

        if (!response.ok) {
            throw new Error(
                `Cannot load language file: ${lang}`
            );
        }

        const translations = await response.json();

        // SET HTML LANG
        document.documentElement.lang = lang;

        // UPDATE TRANSLATIONS
        document.querySelectorAll("[data-i18n]")
        .forEach(element => {

            const key =
                element.getAttribute("data-i18n");

            const keys = key.split(".");

            let value = translations;

            keys.forEach(k => {

                if (
                    value &&
                    value[k] !== undefined
                ) {
                    value = value[k];
                } else {
                    value = null;
                }

            });

            if (value !== null) {

                if (typeof value === "string") {

                    value = value
                        .replace(/\\n/g, "<br>")
                        .replace(/\n/g, "<br>");

                }

                element.innerHTML = value;

            }

        });

        // UPDATE CAROUSEL IMAGES
        updateCarouselImages(lang);

        // SAVE CURRENT LANGUAGE
        currentLanguage = lang;

        // SAVE TO LOCAL STORAGE
        localStorage.setItem(
            "language",
            lang
        );

        // UPDATE BUTTON LABEL
        const langToggle =
            document.getElementById("langToggle");

        if (langToggle) {
            langToggle.textContent =
                lang.toUpperCase();
        }

    }
    catch(error) {

        console.error(
            "Language loading failed:",
            error
        );

    }

}

/* =========================================
   UPDATE CAROUSEL IMAGES
========================================= */

function updateCarouselImages(lang) {

    const slides =
        document.querySelectorAll(".carousel-slide");

    slides.forEach(slide => {

        const currentBgImage =
            slide.style.backgroundImage;

        if (
            !currentBgImage ||
            !currentBgImage.includes("carousel")
        ) {
            return;
        }

        // EXTRACT IMAGE NUMBER
        const match =
            currentBgImage.match(/(\d{2})\.png/);

        if (!match) {
            return;
        }

        const imageNumber = match[1];

        const langSpecificImage =
            getCarouselImagePath(
                lang,
                imageNumber
            );

        const defaultImage =
            getDefaultCarouselImagePath(
                imageNumber
            );

        const tempImg = new Image();

        tempImg.onload = () => {

            slide.style.backgroundImage =
                `url('${langSpecificImage}')`;

        };

        tempImg.onerror = () => {

            slide.style.backgroundImage =
                `url('${defaultImage}')`;

        };

        tempImg.src = langSpecificImage;

    });

}

/* =========================================
   PAGE READY
========================================= */

document.addEventListener(
    "DOMContentLoaded",
    () => {

    /* =========================================
       SUPPORTED LANGUAGES
    ========================================= */

    const supportedLanguages = [
        "en",
        "fr",
        "de",
        "es",
        "it",
        "tr",
        "pl"
    ];

    /* =========================================
       LANGUAGE SELECTOR
    ========================================= */

    const langSelector =
        document.getElementById(
            "langSelector"
        );

    const langToggle =
        document.getElementById(
            "langToggle"
        );

    // OPEN / CLOSE DROPDOWN
    if (langToggle && langSelector) {

        langToggle.addEventListener(
            "click",
            (e) => {

            e.stopPropagation();

            langSelector.classList.toggle(
                "active"
            );

        });

        // CLOSE WHEN CLICKING OUTSIDE
        document.addEventListener(
            "click",
            (e) => {

            if (
                !langSelector.contains(e.target)
            ) {

                langSelector.classList.remove(
                    "active"
                );

            }

        });

    }

    /* =========================================
       DEFAULT LANGUAGE
    ========================================= */

    let savedLanguage =
        localStorage.getItem("language");

    if (!savedLanguage) {

        const browserLanguage =
            navigator.language
            .slice(0, 2)
            .toLowerCase();

        if (
            supportedLanguages.includes(
                browserLanguage
            )
        ) {

            savedLanguage =
                browserLanguage;

        }
        else {

            savedLanguage = "en";

        }

    }

    // INITIAL LOAD
    loadLanguage(savedLanguage);

    /* =========================================
       LANGUAGE CLICK EVENTS
    ========================================= */

    document.querySelectorAll("[data-lang]")
    .forEach(link => {

        link.addEventListener(
            "click",
            async function(e) {

            e.preventDefault();

            const selectedLanguage =
                this.getAttribute(
                    "data-lang"
                );

            await loadLanguage(
                selectedLanguage
            );

            if (langSelector) {

                langSelector.classList.remove(
                    "active"
                );

            }

        });

    });

    /* =========================================
       HERO CAROUSEL
    ========================================= */

    const slides =
        document.querySelectorAll(
            ".carousel-slide"
        );

    const prevButton =
        document.querySelector(
            ".carousel-prev"
        );

    const nextButton =
        document.querySelector(
            ".carousel-next"
        );

    const indicatorsContainer =
        document.querySelector(
            ".carousel-indicators"
        );

    let currentSlide = 0;

    // CHANGE DELAY HERE
    const carouselDelay = 5000;

    function updateIndicators(index) {

        if (!indicatorsContainer) {
            return;
        }

        const indicators =
            indicatorsContainer.querySelectorAll(
                ".carousel-indicator"
            );

        indicators.forEach((dot, i) => {

            dot.classList.toggle(
                "active",
                i === index
            );

        });

    }

    function createIndicators() {

        if (
            !indicatorsContainer ||
            slides.length === 0
        ) {
            return;
        }

        if (
            indicatorsContainer.children.length
        ) {
            return;
        }

        slides.forEach((_, i) => {

            const indicator =
                document.createElement(
                    "button"
                );

            indicator.type = "button";

            indicator.className =
                "carousel-indicator";

            indicator.addEventListener(
                "click",
                () => {

                showSlide(i);

            });

            indicatorsContainer.appendChild(
                indicator
            );

        });

    }

    function showSlide(index) {

        if (slides.length === 0) {
            return;
        }

        currentSlide = index;

        if (currentSlide < 0) {
            currentSlide =
                slides.length - 1;
        }

        if (
            currentSlide >= slides.length
        ) {
            currentSlide = 0;
        }

        slides.forEach((slide, i) => {

            slide.classList.toggle(
                "active",
                i === currentSlide
            );

        });

        updateIndicators(currentSlide);

    }

    function prevSlide() {
        showSlide(currentSlide - 1);
    }

    function nextSlide() {
        showSlide(currentSlide + 1);
    }

    if (slides.length > 0) {

        createIndicators();

        showSlide(currentSlide);

        if (prevButton) {

            prevButton.addEventListener(
                "click",
                prevSlide
            );

        }

        if (nextButton) {

            nextButton.addEventListener(
                "click",
                nextSlide
            );

        }

        setInterval(
            nextSlide,
            carouselDelay
        );

    }

    /* =========================================
       EMAILJS CONTACT FORM
    ========================================= */

    const contactForm =
        document.getElementById(
            "contact-form"
        );

    if (
        contactForm &&
        typeof emailjs !== "undefined"
    ) {

        emailjs.init({
            publicKey:
                "8-8boZaUBzxk5e6Y7"
        });

        contactForm.addEventListener(
            "submit",
            function(event) {

            event.preventDefault();

            emailjs.sendForm(
                "service_iwwjsjm",
                "template_0pmpvt9",
                this
            )
            .then(() => {

                alert(
                    "Email sent successfully!"
                );

                contactForm.reset();

            })
            .catch((error) => {

                console.error(
                    "FAILED...",
                    error
                );

                alert(
                    "Failed to send email."
                );

            });

        });

    }
    else {

        console.warn(
            "EmailJS or contact form not found."
        );

    }

});