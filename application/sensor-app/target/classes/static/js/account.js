document.addEventListener('DOMContentLoaded', function () {
    var registrationForm = document.getElementById('registrationForm');

    // Event listener for the registration form
    registrationForm.addEventListener('submit', function (event) {
        event.preventDefault();
        validateRegistrationForm();
    });

    // Function to validate the registration form
    function validateRegistrationForm() {
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;

        // Example validation checks
        if (username.length < 10) {
            document.getElementById('usernameHint').innerText = 'Username must be at least 10 characters.';
        } else {
            document.getElementById('usernameHint').innerText = '';
        }

        if (password.length < 6) {
            document.getElementById('passwordHint').innerText = 'Password must be at least 6 characters including uppsercase and lowercase letters.';
        } else {
            document.getElementById('passwordHint').innerText = '';
        }
    }
});
