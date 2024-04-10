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
 * Initializes the widgets on the page.
 */
function initWidget() {
  document.getElementById('noise-widget').innerHTML = '<h2>Noise</h2>';
  document.getElementById('temperature-widget').innerHTML = '<h2>Temperature</h2>';
  document.getElementById('parking-widget').innerHTML = '<h2>Parking</h2>';
}

/**
 * Updates the last updated time for each widget.
 */
function endWidget() {
  document.getElementById('noise-widget').innerHTML += '<p>Last Updated: <span id="noise-widget-update">' + new Date().toLocaleTimeString() + '</span></p>';
  document.getElementById('temperature-widget').innerHTML += '<p>Last Updated: <span id="temperature-widget-update">' + new Date().toLocaleTimeString() + '</span></p>';
  document.getElementById('parking-widget').innerHTML += '<p>Last Updated: <span id="parking-widget-update">' + new Date().toLocaleTimeString() + '</span></p>';
}

/**
 * Fetches noise data and updates the noise widget.
 * @param {Object} data - The noise data to be displayed.
 * @param {string} data.location - The location of the noise reading.
 * @param {number} data.lastReading - The last recorded noise reading.
 * @param {string} data.unit - The unit of noise measurement.
 */
function fetchNoise(data) {
  var widget = document.getElementById('noise-widget');
  widget.innerHTML += '<p>' + data.location + ' noise level: <span id="' + data.location + '-noise">' + data.lastReading + '</span> ' + data.unit + '</p>';
}

/**
 * Fetches temperature data and updates the temperature widget.
 * @param {Object} data - The temperature data to be displayed.
 * @param {string} data.location - The location of the temperature reading.
 * @param {number} data.lastReading - The last recorded temperature reading.
 * @param {string} data.unit - The unit of temperature measurement.
 */
function fetchTemperature(data) {
  var widget = document.getElementById('temperature-widget');
  widget.innerHTML = '<h2>Temperature</h2>';
  widget.innerHTML += '<p>' + data.location + ' temperature: <span id="' + data.location + '-temperature">' + data.lastReading + '</span> ' + data.unit + '</p>';
}

/**
 * Fetches parking data and updates the parking widget.
 * @param {Object} data - The parking data to be displayed.
 * @param {string} data.location - The location of the parking reading.
 * @param {number} data.lastReading - The last recorded parking reading.
 */
function fetchParking(data) {
  var widget = document.getElementById('parking-widget');
  widget.innerHTML = '<h2>Parking</h2>';
  widget.innerHTML += '<p>' + data.location + ' | available: <span id="' + data.location + '-parking">' + data.lastReading + '</span></p>';
}

/**
 * Fetches all sensor data from the server.
 * Initializes the widget, fetches the sensor data, and processes it based on the sensor type.
 * Finally, ends the widget and logs any errors that occur during the process.
 */
function fetchAllSensorData() {
  initWidget();
  fetch('sensors')
    .then(response => response.json())
    .then(data => {
      data._embedded["sensorList"].forEach(sensor => {
        if (sensor.name === 'noise') {
          fetchNoise(sensor);
        } else if (sensor.name === 'temperature') {
          fetchTemperature(sensor);
        } else if (sensor.name === 'parking') {
          fetchParking(sensor);
        }
      });
    })
    .finally(() => {
      endWidget();
    })
    .catch(error => console.error('Error:', error));
}

// Call fetchData every 10 seconds
setInterval(fetchAllSensorData, 10000);

// Initial data load
fetchAllSensorData();
