const openModalButtons = document.querySelectorAll('[data-modal-target]')
const closeModalButtons = document.querySelectorAll('[data-close-button]')
const overlay = document.getElementById('overlay')
const modalBody = document.getElementById("wordsTable")
var result
var table

openModalButtons.forEach(button => {
    button.addEventListener('click', () => {
        const modal = document.querySelector(button.dataset.modalTarget)
        openModal(modal)
    })
})

overlay.addEventListener('click', () => {
    const modals = document.querySelectorAll('.modal.active')
    modals.forEach(modal => {
        closeModal(modal)
    })
})

closeModalButtons.forEach(button => {
    button.addEventListener('click', () => {
        const modal = button.closest('.modal')
        closeModal(modal)
    })
})

function openModal(modal) {
    if (modal == null) return
    modal.classList.add('active')
    overlay.classList.add('active')
}

function closeModal(modal) {
    if (modal == null) return
    modal.classList.remove('active')
    overlay.classList.remove('active')
}


function loadTable() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/api/getTable", false);
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            table = '<table><tr><th>ID</th><th>ENG</th><th>UKR</th><th>RATIO</th></tr>';
            result = JSON.parse(this.response); // will send object
            console.log(result);
            for (obj of result) {
                table = table + '<tr>';
                for (prop in obj) {
                    table = table + `<th>${obj[prop]}</th>`;
                }
                table = table = table + '</tr></table>';
            }
            //   document.getElementById('wordsTable').innerHTML = table;
        }
    };

    xhttp.send();
}