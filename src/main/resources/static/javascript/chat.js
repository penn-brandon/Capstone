function profile_click(image) {
    const profile = document.getElementById("profile");
    const profile_dropdown = document.getElementById("profile-dropdown");
    if (profile_dropdown.style.display === "none") {
        profile_dropdown.style.display = "block";
    } else {
        profile_dropdown.style.display = "none";
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