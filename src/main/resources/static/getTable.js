document.addEventListener('DOMContentLoaded', getTable, false);
document.addEventListener('DOMContentLoaded', addShowTableFunc, false);
document.addEventListener('DOMContentLoaded', bindDeleteFunction, false);
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
                        let row = `<tr><td><div id='${word.id}aMark' class="deletable">${word.engword}</div></td><td><div id='${word.id}bMark' class="deletable">${word.ukrword}</div></td><td><div id='${word.id}cMark' class="deletable">${word.points}</div></td></tr>`;
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
            if (element.className == "deletable") {
                alert(`element was marked for deleting`);
                let id = element.id;
                id = id.replace('aMark', '').replace('bMark', '').replace('cMark', '');
                document.getElementById(id + "aMark").className = "deletable-marked";
                document.getElementById(id + "bMark").className = "deletable-marked";
                document.getElementById(id + "cMark").className = "deletable-marked";
                idsItemForDelete.push(id);
            } else {
                alert(`element was unmarked for deleting`);
                let id = element.id;
                id = id.replace('aMark', '').replace('bMark', '').replace('cMark', '');
                document.getElementById(id + "aMark").className = "deletable";
                document.getElementById(id + "bMark").className = "deletable";
                document.getElementById(id + "cMark").className = "deletable";
                idsItemForDelete.pop(id);
            }
        }, false);
    }
}

function deleteWords() {

    var formdata = new FormData();
    formdata.append("idsItemForDelete", idsItemForDelete.toString());

    var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    fetch("/deleteWords", requestOptions)
        .then(response => response.text())
        .then(result => reload(result))
        .catch(error => console.log('error', error));
}

function reload(result) {
    let table = document.getElementById("myTable");
    table.style.display = "none";
    getTable();
}

function bindDeleteFunction() {
    document.getElementById("deleteButton").addEventListener('click', deleteWords);
}