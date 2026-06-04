const fs = require('fs');
const path = require('path');
const base = JSON.parse(fs.readFileSync(path.join('lang','en.json'),'utf8'));
const files = ['fr.json','es.json','it.json','pl.json','tr.json','de.json'];
const groups=['aboutPage','contactPage','recruitmentPage'];
const getPaths=(obj,pref='')=>Object.entries(obj).flatMap(([k,v])=>typeof v==='object'&&v!==null?getPaths(v,`${pref}${k}.`):[`${pref}${k}`]);
for(const file of files){
  const obj=JSON.parse(fs.readFileSync(path.join('lang',file),'utf8'));
  console.log(`\n---- ${file} ----`);
  for(const g of groups){
    const basePaths = getPaths(base[g]||{}).sort();
    const otherPaths = getPaths(obj[g]||{}).sort();
    const missing = basePaths.filter(p=>!otherPaths.includes(p));
    if(missing.length){
      console.log(` ${g} missing ${missing.length}`);
      console.log(missing.join(', '));
    } else {
      console.log(` ${g} OK`);
    }
  }
}
