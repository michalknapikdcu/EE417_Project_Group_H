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

document.addEventListener("DOMContentLoaded", function() {
  var dropdownBtn = document.querySelector('.dropdown-btn');
  var dropdownContainer = document.querySelector('.dropdown-container');

  dropdownBtn.addEventListener('click', function() {
    dropdownContainer.classList.toggle('show');
  });
});

document.addEventListener("DOMContentLoaded", function() {
  var dropdownBtn = document.querySelector('.dropdown-btn2');
  var dropdownContainer = document.querySelector('.dropdown-container2');

  dropdownBtn.addEventListener('click', function() {
    dropdownContainer.classList.toggle('show');
  });
});


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
    widget.innerHTML += '<h4>Last Updated: <span id="noise-widget-update">' + new Date().toLocaleTimeString() + '</span></h4>';
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
  var container = document.getElementsByClassName('container')[0];
  container.innerHTML = '';
  fetch('sensors')
    .then(response => response.json())
    .then(data => {
      if (!data || !data._embedded || !data._embedded["sensorList"]) {
        return;
      }
      data._embedded["sensorList"].forEach(sensor => {
        fetchData(sensor);
      });
    })
    .finally(() => {
    })
    .catch(error => console.error('Error:', error));
}

function updateSensor(sensor) {
  var sensorElement = document.getElementById(sensor.location + '-' + sensor.name);
  if (sensorElement) {
    sensorElement.innerText = sensor.lastReading;
  }
}

function fetchSensorStream() {
  fetch('sensors/stream')
    .then(response => response.json())
    .then(data => {
      if (!data || !data._embedded || !data._embedded["sensorList"]) {
        return;
      }
      data._embedded["sensorList"].forEach(sensor => {
        updateSensor(sensor);
      });
    })
    .finally(() => {
    })
    .catch(error => console.error('Error:', error));
}

// $(document).ready(function() {
//   // Fetch the sensor data every 20 seconds
//   setInterval(fetchAllSensorData, 20000);

//   // Fetch the sensor stream every 10 seconds
//   setInterval(fetchSensorStream, 10000);

//   // Initial data load
//   fetchAllSensorData();
//   fetchSensorStream();
// } );

// // Call fetchData every 20 seconds
// setInterval(fetchAllSensorData, 20000);

// // Initial data load
// fetchAllSensorData();

// // Call fetchSensorStream every 10 seconds
// // setInterval(fetchSensorStream, 10000);

// // Initial data load
// fetchSensorStream(); 

