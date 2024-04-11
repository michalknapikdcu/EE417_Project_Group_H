/**
 * Opens the side navigation menu.
 */
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

/**
 * Closes the side navigation menu.
 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}



/**
 * Fetches the sensor data from the server.
 * @param {Object} data - The sensor data.
 * @param {string} data.location - The location of the sensor.
 * @param {number} data.lastReading - The last reading of the sensor.
 * @param {string} data.unit - The unit of the sensor.
 */
function fetchData(data) {
  if (!document.getElementById(data.name + '-widget')) {
    var container = document.getElementsByClassName('container')[0];
    var widget = document.createElement('div');
    widget.id = data.name + '-widget';
    widget.className = 'widget';
    container.appendChild(widget);
    widget.innerHTML = '<h2>' + data.name.charAt(0).toUpperCase() + data.name.slice(1) + '</h2>';
    widget.innerHTML += '<p>Last Updated: <span id="noise-widget-update">' + new Date().toLocaleTimeString() + '</span></p>';
  }
  var widget = document.getElementById(data.name + '-widget');
  widget.innerHTML += '<p>' + data.location + ' | available: <span id="' + data.location + '-' + data.name + '">' + data.lastReading + '</span> ' + data.unit + '</p>';
}


/**
 * Fetches all sensor data from the server.
 * Initializes the widget, fetches the sensor data, and processes it based on the sensor type.
 * Finally, ends the widget and logs any errors that occur during the process.
 */
function fetchAllSensorData() {
  initWidget();
  var container = document.getElementsByClassName('container')[0];
  container.innerHTML = '';
  fetch('sensors')
    .then(response => response.json())
    .then(data => {
      data._embedded["sensorList"].forEach(sensor => {
        fetchData(sensor);
      });
    })
    .finally(() => {
    })
    .catch(error => console.error('Error:', error));
}

// Call fetchData every 10 seconds
setInterval(fetchAllSensorData, 10000);

// Initial data load
fetchAllSensorData();
