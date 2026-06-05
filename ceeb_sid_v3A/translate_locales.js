const fs = require('fs');
const https = require('https');
const path = require('path');
const langDir = path.join(__dirname, 'lang');
const sourcePath = path.join(langDir, 'en.json');
const sourceData = JSON.parse(fs.readFileSync(sourcePath, 'utf8'));
const targetLangs = ['fr', 'it', 'pl', 'tr', 'de'];
const batchSize = 20;
function collectStrings(obj, pathPrefix='') {
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
function escapeTags(text) {
  const tags = [];
  const replaced = text.replace(/<[^>]+>/g, match => {
    const token = `__TAG_${tags.length}__`;
    tags.push(match);
    return token;
  });
  return { text: replaced, tags };
}
function restoreTags(text, tags) {
  let result = text;
  tags.forEach((tag, index) => {
    result = result.replace(`__TAG_${index}__`, tag);
  });
  return result;
}
function translateBatch(strings, targetLang) {
  return new Promise((resolve, reject) => {
    const qs = strings.map(item => `q=${encodeURIComponent(item.value)}`).join('&');
    const url = `https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=${targetLang}&dt=t&${qs}`;
    https.get(url, res => {
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
    }).on('error', reject);
  });
}
function translateLang(lang, uniqueStrings) {
  return new Promise(async (resolve, reject) => {
    const translatedMap = new Map();
    let idx = 0;
    while (idx < uniqueStrings.length) {
      const batch = uniqueStrings.slice(idx, idx + batchSize);
      try {
        const values = batch.map(item => item.value);
        const translations = await translateBatch(batch, lang);
        translations.forEach((translated, j) => {
          translatedMap.set(batch[j].value, translated);
        });
      } catch (err) {
        return reject(err);
      }
      idx += batchSize;
      await new Promise(res => setTimeout(res, 400));
    }
    resolve(translatedMap);
  });
}
function buildLocale(targetLang, translatedMap, allEntries) {
  const locale = JSON.parse(JSON.stringify(sourceData));
  allEntries.forEach(entry => {
    const escaped = escapeTags(entry.value);
    const translated = translatedMap.get(escaped.text) || entry.value;
    setValue(locale, entry.key, restoreTags(translated, escaped.tags));
  });
  return locale;
}
(async () => {
  const allEntries = collectStrings(sourceData);
  const uniqueMap = new Map();
  const uniqueEntries = [];
  allEntries.forEach(entry => {
    const escaped = escapeTags(entry.value);
    if (!uniqueMap.has(escaped.text)) {
      uniqueMap.set(escaped.text, entry.value);
      uniqueEntries.push({ value: escaped.text, original: entry.value });
    }
  });
  for (const lang of targetLangs) {
    console.log(`Translating ${uniqueEntries.length} unique strings into ${lang}...`);
    const translatedMap = await translateLang(lang, uniqueEntries);
    const locale = JSON.parse(JSON.stringify(sourceData));
    allEntries.forEach(entry => {
      const escaped = escapeTags(entry.value);
      const translated = translatedMap.get(escaped.text) || entry.value;
      setValue(locale, entry.key, restoreTags(translated, escaped.tags));
    });
    const targetPath = path.join(langDir, `${lang}.json`);
    fs.writeFileSync(targetPath, JSON.stringify(locale, null, 2), 'utf8');
    console.log(`Wrote ${targetPath}`);
  }
})().catch(err => { console.error(err); process.exit(1); });
