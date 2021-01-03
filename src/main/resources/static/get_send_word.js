const buttons = document.getElementsByClassName("button1");

async function getWord() {
    let data;
    let word;
    let userData;
    let response = await fetch('/api/getWord');

    if (response.ok) {
        for (let element of buttons) {
            element.disabled = false;
            element.style.color = "white";
        }
        data = await response.json();
        userData = data[0];
        word = data[1];

        if (userData != undefined || word != undefined) {
            let csvPicker = document.getElementById("CSV_picker");
            if (csvPicker != undefined) csvPicker.parentNode.removeChild(csvPicker);
            document.getElementById("profile_csv_picker_save").style.visibility = "visible";
        }

        document.getElementById("translate").innerHTML = word.ukrword;
        document.getElementById('details').textContent = `${word.ukrword.toLocaleUpperCase()} - ${word.engword.toLocaleUpperCase()}`;
        document.getElementById('myAudio').src = word.sound;

        //   document.getElementById('user').innerHTML = userData.name;
        let progressBar = document.getElementById('myBar');
        progressBar.style.width = Math.ceil(userData.allUserPoints / userData.maxUserPoints * 100) + "%";
        progressBar.innerHTML = Math.ceil(userData.allUserPoints / userData.maxUserPoints * 100) + "%";

    } else {
        for (let element of buttons) {
            element.disabled = true;
            element.style.color = "gray";
        }

    }
}

//Send word
async function sendWord() {
    let input = document.getElementById('WordInput');
    let label = document.getElementById('inputLabel');
    let word = input.value;

    let response = await fetch('/api/checkWord', {
        method: 'POST',
        body: word
    });

    if (response.ok) {
        label.className = 'indicatorGreen';
        setTimeout(() => label.className = 'indicatorWhite', 700);
        input.value = '';
        await getWord();
    } else {
        label.className = 'indicatorRed';
        setTimeout(() => label.className = 'indicatorWhite', 700);
        input.value = '';
        //todo: here can be mistake log
        //  let result = await response.json();
        //  console.log(result.error);
        await getWord();
    }
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    var formdata = new FormData();
    formdata.append("email", profile.getEmail());

    var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    fetch("/api/login", requestOptions)
        .then(getWord)
        .catch(error => console.log('error', error));
}


document.addEventListener('DOMContentLoaded', function() {
    let wordInputField = document.getElementById('WordInput');
    wordInputField.addEventListener('keyup', function(e) {
        if (e.keyCode === 13) {
            sendWord();
        }
    });
}, false);