// theme.js

document.addEventListener("DOMContentLoaded", function () {
    const themeToggle = document.getElementById('themeToggle');
    const content = document.getElementById('content');

    themeToggle.addEventListener('change', function () {
        if (themeToggle.checked) {
            // Dark theme is enabled
            content.classList.add('dark-theme');
        } else {
            // Light theme is enabled
            content.classList.remove('dark-theme');
        }
    });
});
