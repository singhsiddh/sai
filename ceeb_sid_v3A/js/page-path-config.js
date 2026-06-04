/* ============================================================
   CEEB SID — page-path-config.js
   Page-specific path helper for shared app.js
   ============================================================ */

(function () {
  const path = window.location.pathname
    .replace(/\\/g, '/')
    .toLowerCase();

  const pagePrefixes = {
    '/': './',
    '/index.html': './',
    '/about/': '../',
    '/about/index.html': '../',
    '/contact/': '../',
    '/contact/index.html': '../',
    '/expertise/': '../',
    '/expertise/index.html': '../',
    '/recruitment/': '../',
    '/recruitment/index.html': '../'
  };

  window.ceebLangPrefix =
    pagePrefixes[path] ||
    '../';
})();
