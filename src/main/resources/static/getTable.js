function getTable() {

    let table;
    let getTablePromise = new Promise(function(resolve, reject) {

        let xhttp = new XMLHttpRequest();
        xhttp.open("GET", "/api/getTable", true);
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    table = JSON.parse(this.response); // will send object
                    var tableBodyLine = "";
                    for (let index in table) {
                        let word = table[index];
                        let row = `<tr><td>${word.id}</td><td>${word.engword}</td><td>${word.ukrword}</td><td>${word.points}</td></tr>`;
                        tableBodyLine = tableBodyLine + row;
                    }
                    resolve(tableBodyLine);
                } else reject(new Error(">>> Error during 'GET' to '/api/getTable'."));
            }
        }
        xhttp.send();
    });

    getTablePromise.then((tableBodyLine) => {
        document.getElementById("thad").innerHTML = "<td>id</td><td>english word</td><td>ukranian word</td><td>points</td>";
        document.getElementById("tbod").innerHTML = tableBodyLine;
    }, (err) => { console.error(err) });
}

document.addEventListener('DOMContentLoaded', getTable, false);
document.addEventListener('DOMContentLoaded', addColabsable, false);

function addColabsable() {
    var coll = document.getElementsByClassName("collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });
    }
}