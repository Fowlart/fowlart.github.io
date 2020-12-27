function getTable() {
    if (document.getElementById("power").checked) {

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

    } else {
        hideTable()
    }
}

function hideTable() {
    document.getElementById("tbod").innerHTML = "";
    document.getElementById("thad").innerHTML = "";
}

function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  console.log( 'Id token: '+ googleUser.getAuthResponse().id_token);
}