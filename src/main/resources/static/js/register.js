document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const name = document.getElementById("name").value;
        const lastName = document.getElementById("lastName").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        const userData = {
            name: name,
            lastName: lastName,
            email: email,
            password: password
        };

        try {
            const response = fetch("https://backtaller6.duckdns.org:443/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(userData)
            });

            if (response.ok) {
                const result = await response.text();
                alert(result);
            } else {
                const error = await response.text();
                alert(`Error: ${error}`);
            }
        } catch (error) {
            console.error("Error al intentar registrar el usuario:", error);
            alert("Hubo un problema al intentar registrar al usuario.");
        }
    });
});

function redirectToLogin() {
        window.location.href = '../index.html';
}
