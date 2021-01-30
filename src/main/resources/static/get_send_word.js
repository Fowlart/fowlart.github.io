const buttons = document.querySelectorAll(".button1");
disableControls();

function disableControls() {
    for (let element of buttons) {
        element.disabled = true;
        element.style.color = "gray";
    }
}

function enableControls() {
    for (let element of buttons) {
        element.disabled = false;
        element.style.color = "white";
    }
}

async function getWord() {
    let data;
    let word;
    let userData;
    let response = await fetch('/api/getWord');

    if (response.ok) {
        enableControls();
        data = await response.json();
        userData = data[0];
        word = data[1];

        if (userData != undefined || word != undefined) {
            let csvPicker = document.getElementById("login-container");
            if (csvPicker != undefined) csvPicker.parentNode.removeChild(csvPicker);
        }

        document.getElementById("translate").innerHTML = word.ukrword;
        document.getElementById('details').textContent = word.engword.toLocaleUpperCase();
        document.getElementById('myAudio').src = word.sound;

        //   document.getElementById('user').innerHTML = userData.name;
        let progressBar = document.getElementById('myBar');
        progressBar.style.width = Math.ceil(userData.allUserPoints / userData.maxUserPoints * 100) + "%";
        progressBar.innerHTML = Math.ceil(userData.allUserPoints / userData.maxUserPoints * 100) + "%";

    } else {
        disableControls();
    }
}

//Send word
async function sendWord() {
    let input = document.getElementById('WordInput');
    let bar = document.querySelector('#myBar');
    let word = input.value;

    let response = await fetch('/api/checkWord', {
        method: 'POST',
        body: word
    });

    if (response.ok) {
        bar.className = 'indicatorGreen';
        setTimeout(() => bar.className = 'indicatorBlack', 500);
        input.value = '';
        await getWord();
    } else {
        bar.className = 'indicatorRed';
        setTimeout(() => bar.className = 'indicatorBlack', 500);
        input.value = '';
        //todo: here can be mistake log
        //  let result = await response.json();
        //  console.log(result.error);
        await getWord();
    }
}

function checkLogin() {
    var eMail = getCookie("email");
    var idToken = getCookie("idToken");
    console.info("used email: " + eMail);

    // tempstub
    if (eMail != null) {
        var formdata = new FormData();
        formdata.append("email", eMail);
        formdata.append("idToken", idToken);

        var requestOptions = {
            method: 'POST',
            body: formdata,
            redirect: 'follow'
        };

        fetch("/api/login", requestOptions)
            .then(getWord)
            .catch(error => console.log('error', error));
    } else {
        disableControls();
    }
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    var eMail = profile.getEmail();
    console.log(`logged in as ${eMail}`);
    var idToken = googleUser.getAuthResponse().id_token;
    setCookie("email", eMail, 1);
    setCookie("idToken", idToken, 1);
    checkLogin();
}

//work with cookie
function setCookie(cname, cvalue, hrs) {
    var d = new Date();
    d.setTime(d.getTime() + (hrs * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}