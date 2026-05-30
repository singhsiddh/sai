 
/* =========================
   GLOBAL LANGUAGE VARIABLE
========================= */

let currentLanguage = "en";

/* =========================
   LANGUAGE PATH HELPERS
========================= */

function getLanguageFilePath(lang) {
    const segments = window.location.pathname.split("/");
    const depth = segments.length - 2;
    const prefix = "../".repeat(Math.max(0, depth));
    return `${prefix}lang/${lang}.json`;
}

/* =========================
   LOAD LANGUAGE
========================= */

async function loadLanguage(lang) {

    try {

        const response =
            await fetch(getLanguageFilePath(lang));

        if (!response.ok) {
            throw new Error(
                `Cannot load language file: ${lang}`
            );
        }

        const translations =
            await response.json();

        // SET HTML LANG
        document.documentElement.lang = lang;

        // UPDATE TEXT CONTENT
        document.querySelectorAll("[data-i18n]")
        .forEach(element => {

            const key =
                element.getAttribute("data-i18n");

            const keys = key.split(".");

            let value = translations;

            keys.forEach(k => {

                if (value && value[k] !== undefined) {
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

        // UPDATE CURRENT LANGUAGE
        currentLanguage = lang;

        // SAVE LANGUAGE
        localStorage.setItem("language", lang);

        // UPDATE BUTTON LABEL
        document.getElementById("langToggle")
            .textContent = lang.toUpperCase();

    }
    catch(error) {

        console.error(
            "Language loading failed:",
            error
        );

    }

}

/* =========================
   UPDATE CAROUSEL IMAGES
========================= */

function updateCarouselImages(lang) {

    const slides = document.querySelectorAll(".carousel-slide");

    slides.forEach(slide => {

        const currentBgImage = slide.style.backgroundImage;

        if (!currentBgImage || currentBgImage.includes("about-assets")) {
            return;
        }

        // Extract the image number from current path (01, 02, etc.)
        const match = currentBgImage.match(/(\d{2})/);

        if (match) {

            const imageNumber = match[1];

            const pathSegments = window.location.pathname.split("/").filter(Boolean);
            const depth = Math.max(0, pathSegments.length - 1);
            const relativePrefix = "../".repeat(depth);

            // Try language-specific path first
            const langSpecificImage = `${relativePrefix}images/carousel/${lang}/${imageNumber}.png`;

            // Default image path
            const defaultImage = `${relativePrefix}images/carousel/${imageNumber}.png`;

            const tempImg = new Image();

            tempImg.onload = () => {
                slide.style.backgroundImage = `url('${langSpecificImage}')`;
            };

            tempImg.onerror = () => {
                slide.style.backgroundImage = `url('${defaultImage}')`;
            };

            tempImg.src = langSpecificImage;

        }

    });

}

/* =========================
   PAGE READY
========================= */

document.addEventListener("DOMContentLoaded", () => {

    const supportedLanguages = [
        "en",
        "fr",
        "de",
        "es",
        "it",
        "tr"
    ];

    /* =========================
       LANGUAGE SELECTOR
    ========================= */

    const langSelector =
        document.getElementById("langSelector");

    const langToggle =
        document.getElementById("langToggle");

    // OPEN DROPDOWN
    langToggle.addEventListener("click", () => {

        langSelector.classList.toggle("active");

    });

    // CLOSE DROPDOWN
    document.addEventListener("click", (e) => {

        if (!langSelector.contains(e.target)) {

            langSelector.classList.remove("active");

        }

    });

    /* =========================
       DEFAULT LANGUAGE
    ========================= */

    let savedLanguage =
        localStorage.getItem("language");

    if (!savedLanguage) {

        const browserLanguage =
            navigator.language.slice(0, 2);

        if (
            supportedLanguages.includes(
                browserLanguage
            )
        ) {

            savedLanguage = browserLanguage;

        }
        else {

            savedLanguage = "en";

        }

    }

    // INITIAL LOAD
    loadLanguage(savedLanguage);

    /* =========================
       LANGUAGE CLICK EVENTS
    ========================= */

    document.querySelectorAll("[data-lang]")
    .forEach(link => {

        link.addEventListener("click", async function(e) {

            e.preventDefault();

            const selectedLanguage =
                this.getAttribute("data-lang");

            await loadLanguage(selectedLanguage);

            langSelector.classList.remove("active");

        });

    });

});



/* =========================
   HERO CAROUSEL
========================= */

const slides =
    document.querySelectorAll(".carousel-slide");

let currentSlide = 0;

/* CHANGE DELAY HERE */

const carouselDelay = 5000;

/*
5000 = 5 seconds
3000 = 3 seconds
8000 = 8 seconds
*/

function showSlide(index) {

    slides.forEach(slide => {
        slide.classList.remove("active");
    });

    slides[index].classList.add("active");

}

function nextSlide() {

    currentSlide++;

    if (currentSlide >= slides.length) {
        currentSlide = 0;
    }

    showSlide(currentSlide);

}

/* AUTO PLAY */

setInterval(nextSlide, carouselDelay);


   
      emailjs.init({
  publicKey: "8-8boZaUBzxk5e6Y7",
});
    
  

  document
    .getElementById("contact-form")
    .addEventListener("submit", function (event) {

      event.preventDefault();

      emailjs.sendForm(
        "service_iwwjsjm",
        "template_0pmpvt9",
        this
      )
      .then(() => {
        alert("Email sent successfully!");
      })
      .catch((error) => {
        console.log("FAILED...", error);
      });
    });
