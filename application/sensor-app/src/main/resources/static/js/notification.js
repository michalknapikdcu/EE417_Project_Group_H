// notification.js

document.addEventListener("DOMContentLoaded", function () {
    const notificationToggle = document.getElementById('notificationToggle');
    const notificationArea = document.getElementById('notificationArea');

    notificationToggle.addEventListener('change', function () {
        if (notificationToggle.checked) {
            // Notifications are enabled
            // Display dummy notifications
            notificationArea.innerHTML = "<div class='notification'>Dummy notification 1</div><div class='notification'>Dummy notification 2</div>";
        } else {
            // Notifications are disabled
            notificationArea.innerHTML = "";
        }
    });
});
