const form = document.getElementById("signup-form-form");

console.log("TEST");

form.addEventListener("submit", (e) => {
    e.preventDefault();
    const form = e.target;
    const fields = form.elements;
    const username = fields["username"].value;
    const password = fields["password"].value;

    console.log(username);
    console.log(password);

    hashPassword(password).then((digestHex) => console.log(digestHex));
});

async function hashPassword(password) {
    const encoder = new TextEncoder().encode(password);
    const hash = await window.crypto.subtle.digest("SHA-512", password);

    const hashArray = Array.from(new Uint8Array(hashBuffer));
    const hashHex = hashArray
        .map((b) => b.toString(16).padStart(2, "0"))
        .join("");
    return hashHex;
}
