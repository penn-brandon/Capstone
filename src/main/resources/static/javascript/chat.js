function profile_click() {
    const dropdown = document.getElementById('profile-dropdown');

    if (dropdown) {
        dropdown.remove();

    } else {
        const profile_dropdown = document.createElement("div");
        profile_dropdown.id = 'profile-dropdown';
        profile_dropdown.className = 'profile-dropdown';

        const profile = document.createElement('a');
        const logout = document.createElement('a');

        profile.href = "/Capstone/profile";
        profile.id = "profile-link";
        profile.innerHTML = "Profile";

        logout.href = "/Capstone/logout";
        logout.id = "logout-link";
        logout.innerHTML = "Logout";

        profile_dropdown.appendChild(profile);
        profile_dropdown.appendChild(logout);

        document.getElementById("profile-div").appendChild(profile_dropdown);
    }
}
