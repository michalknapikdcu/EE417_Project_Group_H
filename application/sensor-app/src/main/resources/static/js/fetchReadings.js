$(document).ready(function() {

// This code handles showing sensor readings.

// Author: Michal Knapik, based on https://github.com/Christian-Oette/demo-chat-app-sse-spring-boot 

// register stream source	
const eventSource = new EventSource("/sensors/stream");
    
    // handle incoming event by displaying readings in a table
    eventSource.onmessage = (event) => {
		
    // clear a div and rewrite with new with sensor readings
    const div = document.getElementById("reading");
    div.innerHTML = ""; 	
    div.textContent = `${event.data}`;
    };

    // handle errors
    eventSource.onerror = (error) => {
       console.error("Error:", error);
       eventSource.close();
    };
    
});
