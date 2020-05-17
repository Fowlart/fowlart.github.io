var score;

async function getWord() {
    let data;
    let word;
    let userData;
    let response = await fetch('/api/getWord');

    if (response.ok) {
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
        score = userData.allUserPoints;
        console.log(`score in getWord(): ${score}`);
        let progressBar = document.getElementById('myBar');
        progressBar.style.width = Math.ceil(userData.allUserPoints / userData.maxUserPoints * 100) + "%";
        progressBar.innerHTML = Math.ceil(userData.allUserPoints / userData.maxUserPoints * 100) + "%";

    } else {
        let result = await response.json();
        console.log(result.error);
    }
}

// Display whether user was correct or not

function checkIfWordWasCorrect(a, b) {
    let elem = document.getElementById('WordInput');
    if (a != b) {
        elem.classList.toggle('green');
        setTimeout(() => elem.classList.toggle('green'), 700);
    } else {
        elem.classList.toggle('red');
        setTimeout(() => elem.classList.toggle('red'), 700);
    }
}

//Send word

async function sendWord() {
    let word = document.getElementById("WordInput").value;
    let response = await fetch('/api/checkWord', {
        method: 'POST',
        body: word
    });

    if (response.ok) {
        document.getElementById("WordInput").value = '';
        let scoreForCheck = score;
        await getWord();
        setTimeout(checkIfWordWasCorrect(scoreForCheck, score), 700);
    } else {
        let result = await response.json();
        console.log(result.error);

        let elem = document.getElementById('WordInput');
        elem.classList.toggle('red');
        setTimeout(() => elem.classList.toggle('red'), 700);
    }
}

let wordInputField = document.getElementById('WordInput');
wordInputField.addEventListener('keyup', function (e) {
    if (e.keyCode === 13) {
        sendWord();
    }
});