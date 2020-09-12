let container;
let refreshButton;
let lastButton;

document.addEventListener('DOMContentLoaded', function() {
    container = document.getElementById("loggerContainer");
    refreshButton = document.getElementById("refresh");
    lastButton = document.getElementById("last");
    refreshButton.addEventListener('click', getLogger);
    lastButton.addEventListener('click', getLastLogs);
}, false);


function getLogger() {
    let logger;
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/api/getLogger", false);
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            logger = JSON.parse(this.response);
            container.innerHTML = logger.reduce((total, value) => total + value, "");
        }
    }
    xhttp.send();
}

function getLastLogs() {
    let lastItem;
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/api/getLastLogs", false);
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            lastItem = this.response;
            container.innerHTML = lastItem;
        }
    }
    xhttp.send();
}