document.addEventListener("DOMContentLoaded", function () {

    const loginForm = document.querySelector("form");


    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();


        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;


        const loginData = {
            email: email,
            password: password
        };


        fetch("https://backtaller6.duckdns.org:443/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error("Credenciales incorrectas");
            }
        })
        .then(data => {
            alert("Inicio de sesión exitoso: " + data);
            window.location.href = '../html/welcome.html';
        })
        .catch(error => {
            alert("Error: " + error.message);
        });
    });
});
