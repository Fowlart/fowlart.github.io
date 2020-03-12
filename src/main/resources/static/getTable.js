function getTable() {
    if (document.getElementById("power").checked) {
        let table;
        let xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:8080/api/getTable", true);
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                table = JSON.parse(this.response); // will send object
                let tableBodyLine = "";
                for (let index in table) {
                    let word = table[index];
                    let row = `<tr><td>${word.id}</td><td>${word.engword}</td><td>${word.ukrword}</td><td>${word.points}</td></tr>`;
                    tableBodyLine = tableBodyLine + row;
                }
                document.getElementById("tbod").innerHTML += tableBodyLine;
                document.getElementById("thad").innerHTML += "<th>id</th><th>english word</th><th>ukranian word</th><th>points</th>";
            };
        }
        xhttp.send();
    } else {
        hideTable()
    }
}

function hideTable() {
    document.getElementById("tbod").innerHTML = "";
    document.getElementById("thad").innerHTML = "";
}