async function register() {
    const username = document.getElementById("user").value;
    const password = document.getElementById("password").value;
    const name = document.getElementById("name").value;
    const gender = document.getElementById("gender").value;

    if (username !== "" && password !== "" && name !== "" && gender !== "") {
        // RUN QUERY HERE
        console.log("WOW");
        const response =  fetch('/Capstone/register', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                "username": username,
                "password": password,
                "name": name,
                "gender": gender
            })
        });
        if (response.redirected) {
            window.location.href = response.url; // Redirect to the new URL
        }
    }
}



