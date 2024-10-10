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

function friends_click(location){
    const friends_list = document.getElementById("friends-list");
    const friends_drop = document.getElementById("friends-drop");
    if (friends_list.style.display === "block"){
        friends_list.style.display = "none";
        friends_drop.src = location + "chevron-up.svg";
    } else {
        friends_list.style.display = "block";
        friends_drop.src = location + "chevron-down.svg";
    }
}

function channels_click(location){
    const channels_list = document.getElementById("channels-list");
    const channels_drop = document.getElementById("channels-drop");
    if (channels_list.style.display === "block"){
        channels_list.style.display = "none";
        channels_drop.src = location + "chevron-down.svg";
    } else {
        channels_list.style.display = "block";
        channels_drop.src = location + "chevron-up.svg";
    }

}