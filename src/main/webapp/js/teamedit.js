



document.addEventListener("DOMContentLoaded",() => {
    redBook();
});


function readBook() {
    const teamNr = getQueryParam("nr")
    fetch("./resource/team/read?nr" + teamNr)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showTeam(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function showBook(data) {
    document.getElementById("teamNr").value = data.teamNr;
    document.getElementById("teamName").value = data.teamName;
    document.getElementById("Amount of Trophies").value = data.AmountofTrophies;
    document.getElementById("Founding Date").value = data.FoundingDate;
}