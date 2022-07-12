document.addEventListener("DOMContentLoaded", () => {
    readTeams();
});

function readTeams() {
    fetch("./resource/team/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showBoookList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function showBookList(data) {
    let tBody = document.getElementById("teamlist");
    data.forEach(team => {
        let row = tBody.insertRow(-1);
       row.insertCell(-1).innerHTML = team.teamName;
        row.insertCell(-1).innerHTML = team.AmountofTrophies;
        row.insertCell(-1).innerHTML = team.FoundingDate;

    });
}