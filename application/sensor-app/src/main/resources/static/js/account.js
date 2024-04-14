document.addEventListener('DOMContentLoaded', function () {
    var loginForm = document.getElementById('registrationForm'); // Update ID to 'registrationForm'

    // Check if loginForm exists before adding event listener
    if (loginForm) {
        loginForm.addEventListener('submit', function (event) {
            event.preventDefault();
            loginUser();
        });
    } else {
        console.error('Element with ID "registrationForm" not found'); // Update error message
    }

    // Function to login an existing user
    function loginUser() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        var data = {
            username: username,
            password: password
        };

        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.ok) {
                // Authentication successful
                return response.json();
            } else {
                // Authentication failed
                throw new Error('Invalid username or password');
            }
        })
        .then(login => {
            // Check the access_level from the login response
            const role = login.role;
            if (role === 'ADMIN') {
                // Redirect to admin dashboard
                window.location.href = 'admin.html';
            } else if (role === 'USER') {
                // Redirect to user dashboard
                window.location.href = 'index.html';
            } else {
                throw new Error('Unknown user role');
            }
        })
        .catch(error => {
            console.error('Error:', error.message);
            // Display error message
            alert(error.message);
        });
    }
});
