function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}

function fetchData() {
  // Fetch and update population
  document.getElementById('population').textContent = '22,100';
  document.getElementById('population-update').textContent = new Date().toLocaleTimeString();

  // Fetch and update transporation
  document.getElementById('transportation').textContent = '1300';
  document.getElementById('cars-count').textContent = '1000';
  document.getElementById('buses-count').textContent = '200';
  document.getElementById('bikes-count').textContent = '100';
  document.getElementById('transportation-update').textContent = new Date().toLocaleTimeString();

  // Fetch and update air quality
  document.getElementById('air-quality').textContent = 'Good';
  document.getElementById('air-quality-update').textContent = new Date().toLocaleTimeString();

  // Fetch and update noise level
  document.getElementById('noise-level').textContent = 'High';
  document.getElementById('lecture-halls').textContent = 'Moderate';
  document.getElementById('classrooms').textContent = 'Low';
  document.getElementById('u-building').textContent = 'High';
  document.getElementById('library').textContent = 'Low';
  document.getElementById('sports-center').textContent = 'High';
  document.getElementById('inter-faith-center').textContent = 'Moderate';
  document.getElementById('cafeteria').textContent = 'High';
  document.getElementById('noise-level-update').textContent = new Date().toLocaleTimeString();

  // Fetch and update room occupancy
  document.getElementById('room-occupancy').textContent = '20';
  document.getElementById('room-occupancy-update').textContent = new Date().toLocaleTimeString();

  // Fetch and update campus occupancy
  document.getElementById('vehicle-count').textContent = '50';
  document.getElementById('pedestrian-count').textContent = '100';
  document.getElementById('campus-occupancy-update').textContent = new Date().toLocaleTimeString();

  // Fetch and update parking sensors
  document.getElementById('total-spaces').textContent = '200';
  document.getElementById('available-spaces').textContent = '50';
  document.getElementById('parking-sensors-update').textContent = new Date().toLocaleTimeString();
}

// Call fetchData every 1 second
setInterval(fetchData, 1000);

// Initial data load
fetchData();