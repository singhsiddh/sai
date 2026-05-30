const fs = require('fs');
const path = require('path');
const htmlPath = 'c:/Siddharth/Stephane-website/about/About CEEB _ 30 Years of Excellence in Transportation Engineering.html';
const html = fs.readFileSync(htmlPath, 'utf8');
const regex = /src=["']([^"']+)["']/g;
let m;
const refs = [];
while ((m = regex.exec(html))) {
  refs.push(m[1]);
}
console.log(JSON.stringify(refs, null, 2));
