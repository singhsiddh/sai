const fs = require('fs');
const https = require('https');
const path = require('path');
const langDir = path.join(__dirname, 'lang');
const sourcePath = path.join(langDir, 'en.json');
const sourceData = JSON.parse(fs.readFileSync(sourcePath, 'utf8'));
const targetLangs = ['fr', 'it', 'pl', 'tr', 'de'];
const batchSize = 20;

function collectStrings(obj, pathPrefix = '') {
  let entries = [];
  if (typeof obj === 'string') {
    entries.push({ key: pathPrefix, value: obj });
  } else if (Array.isArray(obj)) {
    obj.forEach((item, index) => {
      entries = entries.concat(collectStrings(item, `${pathPrefix}[${index}]`));
    });
  } else if (obj && typeof obj === 'object') {
    Object.entries(obj).forEach(([k, v]) => {
      const nextKey = pathPrefix ? `${pathPrefix}.${k}` : k;
      entries = entries.concat(collectStrings(v, nextKey));
    });
  }
  return entries;
}

function setValue(target, path, value) {
  const parts = path.split('.');
  let obj = target;
  for (let i = 0; i < parts.length - 1; i++) {
    const part = parts[i];
    if (part.endsWith(']')) {
      const [arrayKey, index] = part.slice(0, -1).split('[');
      obj = obj[arrayKey];
      obj = obj[Number(index)];
    } else {
      obj = obj[part];
    }
  }
  const last = parts[parts.length - 1];
  if (last.endsWith(']')) {
    const [arrayKey, index] = last.slice(0, -1).split('[');
    obj[arrayKey][Number(index)] = value;
  } else {
    obj[last] = value;
  }
}

function translateBatch(strings, targetLang) {
  return new Promise((resolve, reject) => {
    const postData = strings.map(item => `q=${encodeURIComponent(item.value)}`).join('&');
    const options = {
      hostname: 'translate.googleapis.com',
      path: `/translate_a/single?client=gtx&sl=en&tl=${targetLang}&dt=t`,
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Content-Length': Buffer.byteLength(postData),
      },
    };
    const req = https.request(options, res => {
      let data = '';
      res.on('data', chunk => data += chunk);
      res.on('end', () => {
        try {
          const parsed = JSON.parse(data);
          const translations = parsed[0].map(el => el[0]);
          resolve(translations);
        } catch (err) {
          reject(new Error(`Failed parse response: ${err.message} ${data}`));
        }
      });
    });
    req.on('error', reject);
    req.write(postData);
    req.end();
  });
}

function loadOldTranslations(lang) {
  const oldPath = path.join(langDir, `${lang}-old.json`);
  if (!fs.existsSync(oldPath)) return new Map();
  const oldData = JSON.parse(fs.readFileSync(oldPath, 'utf8'));
  const entries = collectStrings(oldData);
  const map = new Map();
  entries.forEach(({ key, value }) => map.set(key, value));
  return map;
}

function buildLocaleWithTranslations(lang, oldMap, translatedMap, allEntries) {
  const locale = JSON.parse(JSON.stringify(sourceData));
  allEntries.forEach(entry => {
    const translated = oldMap.has(entry.key) ? oldMap.get(entry.key) : translatedMap.get(entry.key) || entry.value;
    setValue(locale, entry.key, translated);
  });
  return locale;
}

(async () => {
  const allEntries = collectStrings(sourceData);
  for (const lang of targetLangs) {
    const oldMap = loadOldTranslations(lang);
    console.log(`${lang}: ${oldMap.size} existing translations, filling ${allEntries.length - oldMap.size} missing keys with English fallback`);

    const locale = buildLocaleWithTranslations(lang, oldMap, new Map(), allEntries);
    const targetPath = path.join(langDir, `${lang}.json`);
    fs.writeFileSync(targetPath, JSON.stringify(locale, null, 2), 'utf8');
    console.log(`Wrote ${targetPath}`);
  }
})().catch(err => { console.error(err); process.exit(1); });
