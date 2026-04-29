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

function fillEditFormFromButton(btn) {

    document.getElementById("edit-id").value = btn.dataset.id;
    document.getElementById("edit-customerName").value = btn.dataset.name;
    document.getElementById("edit-carId").value = btn.dataset.car;
    document.getElementById("edit-userId").value = btn.dataset.user || "";
    document.getElementById("edit-startDate").value = btn.dataset.start;
    document.getElementById("edit-endDate").value = btn.dataset.end || "";
    document.getElementById("edit-monthlyPrice").value = btn.dataset.price;
    document.getElementById("edit-status").value = btn.dataset.status;

    // scroll to form
    document.querySelector("#edit-id").scrollIntoView({
        behavior: "smooth"
    });
}