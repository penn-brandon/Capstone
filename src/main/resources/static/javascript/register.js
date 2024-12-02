async function register() {
    const username = document.getElementById("user").value;
    const password = document.getElementById("password").value;
    const name = document.getElementById("name").value;
    const gender = document.getElementById("gender").value;

    if (username !== "" && password !== "" && name !== "" && gender !== "") {
        const response = fetch('/Capstone/register', {
            method: 'POST', headers: {"Content-Type": "application/json"}, body: JSON.stringify({
                "username": username, "password": password, "name": name, "gender": gender
            })
        });
        if (!response.ok) {

            console.log("ERROR: " + response.status);
        }
        const json = await response.json();
        window.location.href = json["redirectUrl"];
    }
}



