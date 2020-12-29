document.addEventListener('DOMContentLoaded', getTable, false);
document.addEventListener('DOMContentLoaded', addShowTableFunc, false);
var idsItemForDelete = [];

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
                        let row = `<tr><td><div id='${word.id}' class="deletable">${word.engword}</div></td><td>${word.ukrword}</td><td>${word.points}</td></tr>`;
                        tableBodyLine = tableBodyLine + row;
                    }
                    resolve(tableBodyLine);
                } else reject(new Error(">>> Error during 'GET' to '/api/getTable'."));
            }
        }
        xhttp.send();
    });

    getTablePromise.then((tableBodyLine) => {
        document.getElementById("thad").innerHTML = "<td>english word</td><td>ukranian word</td><td>points</td>";
        document.getElementById("tbod").innerHTML = tableBodyLine;
    }, (err) => { console.error(err) });
}

function addShowTableFunc() {
    var table = document.getElementById("myTable");
    var showButton = document.getElementById("showButton");
    showButton.addEventListener("click", function() {
        if (table.style.display === "block") {
            table.style.display = "none";
        } else {
            table.style.display = "block";
            deletesEnabled();
        }
    });
}

function deletesEnabled() {
    let elementArray = document.getElementsByClassName("deletable")
    for (let element of elementArray) {
        element.addEventListener('click', function() {
            alert(`element with id ${element.id} was marked for deleting`);
            idsItemForDelete.push(element.id);
        }, false);
    }
}