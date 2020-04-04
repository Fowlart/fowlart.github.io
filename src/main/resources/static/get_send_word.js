var score;

function getWord() {
    let data;
    let word;
    let userData;
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/api/getWord", false);
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            data = JSON.parse(this.response); // will send object
            console.log(data);
            userData = data["0"];
            word = data["1"];

            if (userData != undefined || word != undefined) {
                let csvPicker = document.getElementById("CSV_picker");
                if (csvPicker != undefined) csvPicker.parentNode.removeChild(csvPicker);
                document.getElementById("profile_csv_picker_save").style.visibility = "visible";
            }

            document.getElementById("translate").innerHTML = word.ukrword;
            document.getElementById('details').textContent = `${word.ukrword.toLocaleUpperCase()} - ${word.engword.toLocaleUpperCase()}`;
            document.getElementById('myAudio').src = word.sound;

            //   document.getElementById('user').innerHTML = userData.name;
            score = userData.allUserPoints;
            console.log(`score in getWord(): ${score}`);
            let progressBar = document.getElementById('myBar');
            progressBar.style.width = Math.ceil(userData.allUserPoints / userData.maxUserPoints * 100) + "%";
            progressBar.innerHTML = Math.ceil(userData.allUserPoints / userData.maxUserPoints * 100) + "%";

        }
    }
    xhttp.send();
}

// Display whether user was correct or not
function red() {
    let wordInput = document.getElementById("WordInput");
    wordInput.style.borderStyle = 'solid';
    wordInput.style.borderColor = 'red';
    wordInput.style.borderWidth = '2px';
    wordInput.style.boxShadow = '0px 0px 5px red';

}

function green() {
    let wordInput = document.getElementById("WordInput");
    wordInput.style.borderStyle = 'solid';
    wordInput.style.borderColor = 'green';
    wordInput.style.borderWidth = '2px';
    wordInput.style.boxShadow = '0px 0px 5px green';
}

function black() {
    let wordInput = document.getElementById("WordInput");
    wordInput.style.borderStyle = 'solid';
    wordInput.style.borderColor = 'black';
    wordInput.style.borderWidth = '1px';
    wordInput.style.boxShadow = 'none';
}

function checkIfWordWasCorrect(a, b) {
    if (a != b) {
        green();
        setTimeout(black, 700);
    } else {
        red();
        setTimeout(black, 700);
    }
}

//Send word
function sendWord() {
    let word = document.getElementById("WordInput").value;
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/api/checkWord", false);
    xhttp.onreadystatechange = function () {
        console.log(`Response status: ${this.status}`);
        if (this.readyState == 4 && this.status == 200) {
            // everithing OK
            document.getElementById("WordInput").value = '';
            document.getElementById('TranslateWarn').innerHTML = 'enter translation: ';
            document.getElementById('TranslateWarn').style.color = "Black";
            let scoreForCheck = score;
            getWord();
            setTimeout(checkIfWordWasCorrect(scoreForCheck, score), 700);

        } else if (this.readyState == 4 && this.status == 400) {
            // empty line was sent
            document.getElementById('TranslateWarn').style.color = "Red";
            document.getElementById('TranslateWarn').innerHTML = 'Please provide a word: ';
        }
    }
    xhttp.send(word);
}

let wordInputField = document.getElementById('WordInput');
wordInputField.addEventListener('keyup', function (e) {
    if (e.keyCode === 13) {
        sendWord();
    }
});