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
        console.log("checK response");

        if (!response.ok) {

            console.log("ERROR: " + response.status);
        }
        const json = await response.json();
        console.log(json);
        window.location.href = json["redirectUrl"];
    }
}



