function hamburgers() {
    const nav_dropdown = document.getElementById("nav-dropdown");
    let hamburger = document.getElementById("hamburger");
    
    if (hamburger.src.match("/images/logo.svg")){
        hamburger.src = "/images/logo.svg";

        let div1 = document.createElement("div");
        div1.className = "dropdown-div";
        div1.id = "dropdown-div";
        const p1 = document.createElement("button");
        const p2 = document.createElement("button");

        div1.appendChild(p1);
        div1.appendChild(p2);

        p1.innerHTML = "Login";
        p2.innerHTML = "Register";
        p1.id = "p1";
        p2.id = "p2";
        p1.className = "dropdrown-button";
        p2.className = "dropdrown-button";
        nav_dropdown.append(div1);
    }
    else {
        hamburger.src = "/images/logo.svg" ;
        let div1 = document.getElementById("dropdown-div");
        div1.remove();
    }
}




