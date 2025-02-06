document.addEventListener("DOMContentLoaded", function () {
    fetchBuses();
    const busForm = document.getElementById("busForm");

    if (busForm) {
        busForm.addEventListener("submit", function (event) {
            event.preventDefault();
            addBus();
        });
    }
});

// Fetch all buses and display them in a table
function fetchBuses() {
    fetch("/buses")
        .then(response => response.json())
        .then(data => {
            const busList = document.getElementById("busList");
            busList.innerHTML = "";
            data.forEach(bus => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${bus.busNumber}</td>
                    <td>${bus.routes}</td>
                    <td>${bus.capacity}</td>
                `;
                busList.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching buses:", error));
}

// Add a new bus
function addBus() {
    const busNumber = document.getElementById("busNumber").value;
    const routes = document.getElementById("routes").value;
    const capacity = document.getElementById("capacity").value;

    const busData = { busNumber, routes, capacity };

    fetch("/buses", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(busData)
    })
        .then(response => response.json())
        .then(() => {
            alert("Bus added successfully!");
            fetchBuses();
            document.getElementById("busForm").reset();
        })
        .catch(error => console.error("Error adding bus:", error));
}

// Search commuters by name
function searchCommuters() {
    const name = document.getElementById("searchName").value;
    fetch(`/commuters/search?name=${name}`)
        .then(response => response.json())
        .then(data => {
            const commuterList = document.getElementById("commuterList");
            commuterList.innerHTML = "";
            data.forEach(commuter => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${commuter.name}</td>
                    <td>${commuter.ticketNumber}</td>
                `;
                commuterList.appendChild(row);
            });
        })
        .catch(error => console.error("Error searching commuters:", error));
}
