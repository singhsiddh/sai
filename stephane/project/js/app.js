const browserLang = navigator.language.slice(0,2);

const supported = ["en", "fr", "de", "es"];

const lang = supported.includes(browserLang)
    ? browserLang
    : "en";

loadLanguage(lang);


async function loadLanguage(lang) {
    const response = await fetch(`lang/${lang}.json`);
    const translations = await response.json();

    document.querySelectorAll("[data-i18n]").forEach(element => {
        const key = element.getAttribute("data-i18n");
        const keys = key.split(".");

        let value = translations;

        keys.forEach(k => {
            value = value[k];
        });

        if (value) {
            element.textContent = value;
        }
    });

    localStorage.setItem("language", lang);
}

document.addEventListener("DOMContentLoaded", () => {

    const savedLang = localStorage.getItem("language") || "en";

    loadLanguage(savedLang);

    document.querySelectorAll("[data-lang]").forEach(button => {

        button.addEventListener("click", (e) => {
            e.preventDefault();

            const lang = button.dataset.lang;

            loadLanguage(lang);
        });

    });

});