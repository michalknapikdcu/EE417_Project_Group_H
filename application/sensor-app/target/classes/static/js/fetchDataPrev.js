function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}

function initWidget() {
  document.getElementById('noise-widget').innerHTML = '<h2>Noise</h2>';
  document.getElementById('temperature-widget').innerHTML = '<h2>Temperature</h2>';
  document.getElementById('parking-widget').innerHTML = '<h2>Parking</h2>';
}

function endWidget() {
  document.getElementById('noise-widget').innerHTML += '<p>Last Updated: <span id="noise-widget-update">' + new Date().toLocaleTimeString() + '</span></p>';
  document.getElementById('temperature-widget').innerHTML += '<p>Last Updated: <span id="temperature-widget-update">' + new Date().toLocaleTimeString() + '</span></p>';
  document.getElementById('parking-widget').innerHTML += '<p>Last Updated: <span id="parking-widget-update">' + new Date().toLocaleTimeString() + '</span></p>';
}

function fecthNoise(data) {
  var widget = document.getElementById('noise-widget');
  widget.innerHTML += '<p>' + data.location + 'noise level: <span id="' + data.location + '-noise">' + data.lastReading + '</span> ' + data.unit + '</p>';
}

function fetchTemperature(data) {
  var widget = document.getElementById('temperature-widget');
  widget.innerHTML = '<h2>Temperature</h2>';
  widget.innerHTML += '<p>' + data.location + ' temperature: <span id="' + data.location + '-temperature">' + data.lastReading + '</span> ' + data.unit + '</p>';
}

function fetchParking(data) {
  var widget = document.getElementById('parking-widget');
  widget.innerHTML = '<h2>Parking</h2>';
  widget.innerHTML += '<p>' + data.location + ' | available: <span id="' + data.location + '-parking">' + data.lastReading + '</span></p>';
}

function fetchAllSensorData() {
  initWidget();
  fetch('sensors')
    .then(response => response.json())
    .then(data => {
      data._embedded["sensorList"].forEach(sensor => {
        if (sensor.name === 'noise') {
          fecthNoise(sensor);
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

// Call fetchData every 10 second
setInterval(fetchAllSensorData, 10000);

// Initial data load
fetchAllSensorData();
