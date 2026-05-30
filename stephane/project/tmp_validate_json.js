const fs = require('fs');
const path = require('path');
const dir = path.join(process.cwd(), 'lang');
const files = fs.readdirSync(dir).filter(f => f.endsWith('.json'));
let ok = true;
files.forEach(file => {
  const p = path.join(dir, file);
  try {
    const json = JSON.parse(fs.readFileSync(p, 'utf8'));
    const has = Object.prototype.hasOwnProperty.call(json, 'aboutPage');
    process.stdout.write(file + ': ' + (has ? 'aboutPage OK' : 'NO aboutPage') + '\n');
    if (!has) ok = false;
  } catch (e) {
    ok = false;
    process.stdout.write(file + ': JSON ERROR ' + e.message + '\n');
  }
});
process.stdout.write(ok ? 'ALL JSON OK\n' : 'SOME JSON PROBLEMS\n');
