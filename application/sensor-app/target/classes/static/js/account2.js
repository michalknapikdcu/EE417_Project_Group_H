function openModal(modalId) {
    var modal = document.getElementById(modalId);
    modal.style.display = 'flex';
}

function closeModal(modalId) {
    var modal = document.getElementById(modalId);
    modal.style.display = 'none';
}

function submitForm(formType) {
    var username, password;

    if (formType === 'login') {
        username = document.getElementById('username').value;
        password = document.getElementById('password').value;

        // Perform login logic (Add your specific login functionality here)
    } else if (formType === 'signup') {
        username = document.getElementById('newUsername').value;
        password = document.getElementById('newPassword').value;

        // Perform signup logic (Add your specific signup functionality here)
    }

    // Reset the form fields
    document.getElementById('username').value = '';
    document.getElementById('password').value = '';
    document.getElementById('newUsername').value = '';
    document.getElementById('newPassword').value = '';

    // Close the modal
    closeModal(formType + 'Modal');
}