const currentPath = window.location.pathname;

// Get all sidebar items
const items = document.querySelectorAll(".item");

// Remove all active classes
items.forEach(item => item.classList.remove("active"));

// Set active based on URL
if (currentPath === "/") {
    document.querySelector("#hjem")?.classList.add("active");
}
if (currentPath === "/lejeaftaler") {
    document.querySelector("#lejeaftaler")?.classList.add("active");
}
if (currentPath === "/skader") {
    document.querySelector("#skader")?.classList.add("active");
}
if (currentPath === "/biler") {
    document.querySelector("#biler")?.classList.add("active");
}
if (currentPath === "/profil") {
    document.querySelector("#profil")?.classList.add("active");
}
if (currentPath === "/dashboard") {
    document.querySelector("#dashboard")?.classList.add("active");
}
if (currentPath === "/register") {
    document.querySelector("#registrer")?.classList.add("active");
}
if (currentPath === "/login") {
    document.querySelector("#login")?.classList.add("active");
}
if (currentPath === "/indstillinger") {
    document.querySelector("#indstillinger")?.classList.add("active");
}