const fs = require('fs');
const app = fs.readFileSync('js/app.js', 'utf8');
const fn1 = app.match(/function buildLocalizedImagePath[\s\S]*?^}\s*/m)[0];
const fn2 = app.match(/function getCarouselOriginalPath[\s\S]*?^}\s*/m)[0];
const buildLocalizedImagePath = new Function('src','lang','filename', fn1 + '; return buildLocalizedImagePath(src, lang, filename);');
const getCarouselOriginalPath = new Function('src', fn2 + '; return getCarouselOriginalPath(src);');
const tests = [
  {src:'../images/carousel/contact/1.webp', lang:'en', filename:'ceeb-contact-environnement-collaboratif-echange.webp'},
  {src:'../images/carousel/contact/2.webp', lang:'en', filename:'ceeb-contact-expertise-accessible-equipes-multidisciplinaires.webp'},
  {src:'../images/carousel/contact/en/1.webp', lang:'en', filename:'ceeb-contact-environnement-collaboratif-echange.webp'},
  {src:'../images/carousel/contact/en/2.webp', lang:'en', filename:'ceeb-contact-expertise-accessible-equipes-multidisciplinaires.webp'}
];
for (const t of tests) {
  console.log('src=' + t.src + ' lang=' + t.lang + ' filename=' + t.filename);
  console.log('localized=' + buildLocalizedImagePath(t.src, t.lang, t.filename));
  console.log('original=' + getCarouselOriginalPath(t.src));
}
