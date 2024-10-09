function profile_click(image) {
    const profileDiv = document.getElementById("profileDiv");
    let dropped = document.getElementById("dropped");

    if (dropped.src.match(image)) {
        dropped.src = "/images/down.svg";

        let div1 = document.createElement("div");
        div1.className = "dropdown-div";
        div1.id = "dropdown-div";
        const p2 = document.createElement("button");

        div1.appendChild(p2);

        p2.innerHTML = "Logout";
        p2.onclick = () => {
            location.replace("/logout");
        };
        p2.id = "p2";
        p2.className = "dropdrown-button";
        nav_dropdown.append(div1);
    } else {
        hamburger.src = "/images/hamburger.svg";
        let div1 = document.getElementById("dropdown-div");
        div1.remove();
    }
}