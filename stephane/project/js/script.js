// Le fichier script.js original contenait des données de traduction, mais comme nous générons
// des fichiers HTML statiques et traduits, nous n'avons besoin que de la logique du carrousel
// et de la gestion du formulaire/langue.

// La logique de traduction dynamique est supprimée car elle est gérée par la génération statique.

// État de la langue - français par défaut (utilisé pour l'alerte du formulaire)
let currentLang = 'fr'; 

// Fonction pour gérer le menu déroulant (conservée pour l'interactivité du sélecteur de langue)
function initLanguageDropdown() {
    const langSelector = document.querySelector('.lang-selector');
    const langToggle = document.getElementById('langToggle');
    const langDropdown = document.getElementById('langDropdown');
    
    if (!langSelector || !langToggle || !langDropdown) return;
    
    // Afficher/masquer le dropdown au clic sur le bouton
    langToggle.addEventListener('click', (e) => {
        e.stopPropagation();
        langSelector.classList.toggle('active');
    });
    
    // Fermer le dropdown quand on clique ailleurs
    document.addEventListener('click', () => {
        langSelector.classList.remove('active');
    });
    
    // Gérer les clics sur les liens de langue
    const langLinks = langDropdown.querySelectorAll('a');
    langLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            // Le comportement par défaut (redirection) est conservé
            // La langue actuelle est mise à jour pour l'alerte du formulaire
            currentLang = link.getAttribute('data-lang');
        });
    });
}

// ===== CAROUSEL HERO MANAGEMENT =====

class HeroCarousel {
    constructor() {
        this.slides = document.querySelectorAll('.carousel-slide');
        this.currentSlide = 0;
        this.slideInterval = 8000; // 8 secondes par slide
        this.transitionDuration = 1500; // 1.5 secondes de transition
        
        if (this.slides.length > 0) {
            this.init();
        }
    }
    
    init() {
        // Démarrer le carrousel automatique
        this.startCarousel();
        
        // Ajouter les indicateurs
        this.addIndicators();
    }
    
    nextSlide() {
        this.slides[this.currentSlide].classList.remove('active');
        this.currentSlide = (this.currentSlide + 1) % this.slides.length;
        this.slides[this.currentSlide].classList.add('active');
        this.updateIndicators();
    }
    
    startCarousel() {
        setInterval(() => {
            this.nextSlide();
        }, this.slideInterval);
    }
    
    addIndicators() {
        const heroSection = document.querySelector('.hero');
        if (!heroSection) return;
        
        const indicatorsContainer = document.createElement('div');
        indicatorsContainer.className = 'carousel-indicators';
        
        for (let i = 0; i < this.slides.length; i++) {
            const indicator = document.createElement('div');
            indicator.className = 'carousel-indicator' + (i === 0 ? ' active' : '');
            indicator.addEventListener('click', () => this.goToSlide(i));
            indicatorsContainer.appendChild(indicator);
        }
        
        heroSection.appendChild(indicatorsContainer);
    }
    
    goToSlide(index) {
        this.slides[this.currentSlide].classList.remove('active');
        this.currentSlide = index;
        this.slides[this.currentSlide].classList.add('active');
        this.updateIndicators();
    }
    
    updateIndicators() {
        const indicators = document.querySelectorAll('.carousel-indicator');
        indicators.forEach((indicator, index) => {
            indicator.classList.toggle('active', index === this.currentSlide);
        });
    }
}

// Initialiser le carrousel au chargement du DOM
document.addEventListener('DOMContentLoaded', () => {
    // Initialiser le carrousel
    new HeroCarousel();
    
    // Initialiser le menu déroulant de langue
    initLanguageDropdown();
    
    // Mettre à jour l'année actuelle dans le footer
    const yearElement = document.getElementById('year');
    if (yearElement) {
        yearElement.textContent = new Date().getFullYear();
    }
    
    // Définir la langue actuelle pour l'alerte du formulaire
    const htmlLang = document.documentElement.lang;
    if (htmlLang) {
        currentLang = htmlLang;
    }
});

// Gestion du formulaire (simplifiée car les traductions sont statiques)
document.addEventListener('DOMContentLoaded', () => {
    const contactForm = document.getElementById('contactForm');
    if (contactForm) {
        contactForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            let alertMessage = "Message sent!"; // Anglais par défaut
            
            // Déterminer le message d'alerte en fonction de la langue de la page
            switch (currentLang) {
                case 'fr':
                    alertMessage = "Message envoyé !";
                    break;
                case 'de':
                    alertMessage = "Nachricht gesendet!";
                    break;
                case 'dk':
                    alertMessage = "Besked sendt!";
                    break;
                case 'es':
                    alertMessage = "¡Mensaje enviado!";
                    break;
                case 'ga':
                    alertMessage = "Teachtaireacht seolta!";
                    break;
                case 'it':
                    alertMessage = "Messaggio inviato!";
                    break;
                case 'pl':
                    alertMessage = "Wiadomość wysłana!";
                    break;
                case 'tr':
                    alertMessage = "Mesaj gönderildi!";
                    break;
                // 'en' est déjà géré par défaut
            }
            
            alert(alertMessage);
            this.reset();
        });
    }
});
