const contactSlides = document.querySelectorAll('.carousel-slide');
const indicatorsContainer = document.querySelector('.carousel-indicators');
const prevButton = document.querySelector('.carousel-prev');
const nextButton = document.querySelector('.carousel-next');
let activeSlide = 0;

function updateContactCarousel(index) {
    contactSlides.forEach((slide, i) => {
        slide.classList.toggle('active', i === index);
    });
    const indicators = indicatorsContainer.querySelectorAll('.carousel-indicator');
    indicators.forEach((dot, i) => {
        dot.classList.toggle('active', i === index);
    });
}

function createContactIndicators() {
    contactSlides.forEach((_, i) => {
        const indicator = document.createElement('button');
        indicator.type = 'button';
        indicator.className = 'carousel-indicator';
        indicator.addEventListener('click', () => {
            activeSlide = i;
            updateContactCarousel(activeSlide);
        });
        indicatorsContainer.appendChild(indicator);
    });
}

function nextContactSlide() {
    activeSlide = (activeSlide + 1) % contactSlides.length;
    updateContactCarousel(activeSlide);
}

function prevContactSlide() {
    activeSlide = (activeSlide - 1 + contactSlides.length) % contactSlides.length;
    updateContactCarousel(activeSlide);
}

if (contactSlides.length && indicatorsContainer && prevButton && nextButton) {
    createContactIndicators();
    updateContactCarousel(activeSlide);
    nextButton.addEventListener('click', nextContactSlide);
    prevButton.addEventListener('click', prevContactSlide);
    setInterval(nextContactSlide, 6000);
}
