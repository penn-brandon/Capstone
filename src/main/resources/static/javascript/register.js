async function register() {
    const username = document.getElementById("user").value;
    const password = document.getElementById("password").value;
    const name = document.getElementById("name").value;
    const gender = document.getElementById("gender").value;

    if (username !== "" && password !== "" && name !== "" && gender !== "") {
        // RUN QUERY HERE
        const register = await fetch('Capstone/register', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                "username": username,
                "password": password,
                "name": name,
                "gender": gender
            })
        });
        console.log("WOW");
        console.log(register.body);
        if (!register.ok) {
            console.log("ERROR: " + register.status);
        }
    }
}



