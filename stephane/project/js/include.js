async function loadComponent(id, file) {
  const el = document.getElementById(id);

  const response = await fetch(file);

  if (!response.ok) {
    throw new Error("Cannot load component");
  }

  el.innerHTML = await response.text();
}

loadComponent("header", "/header.html");