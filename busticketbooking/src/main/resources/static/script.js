document.addEventListener("DOMContentLoaded", function () {
    fetchBuses();
    const busForm = document.getElementById("busForm");
    const commuterForm = document.getElementById("commuterForm");

    if (busForm) {
        busForm.addEventListener("submit", function (event) {
            event.preventDefault();
            saveBus();
        });
    }

    if (commuterForm) {
        commuterForm.addEventListener("submit", function (event) {
            event.preventDefault();
            updateCommuter();
        });
    }
});

// Fetch and display buses
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
                    <td>
                        <button onclick="editBus(${bus.id}, '${bus.busNumber}', '${bus.routes}', ${bus.capacity})">Edit</button>
                        <button onclick="deleteBus(${bus.id})">Delete</button>
                    </td>
                `;
                busList.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching buses:", error));
}

// Save or Update Bus
function saveBus() {
    const busId = document.getElementById("busId").value;
    const busNumber = document.getElementById("busNumber").value;
    const routes = document.getElementById("routes").value;
    const capacity = document.getElementById("capacity").value;

    const busData = { busNumber, routes, capacity };

    fetch(busId ? `/buses/${busId}` : "/buses", {
        method: busId ? "PUT" : "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(busData)
    })
        .then(() => {
            alert(busId ? "Bus updated successfully!" : "Bus added successfully!");
            fetchBuses();
            document.getElementById("busForm").reset();
        })
        .catch(error => console.error("Error saving bus:", error));
}

// Edit Bus
function editBus(id, busNumber, routes, capacity) {
    document.getElementById("busId").value = id;
    document.getElementById("busNumber").value = busNumber;
    document.getElementById("routes").value = routes;
    document.getElementById("capacity").value = capacity;
}

// Delete Bus
function deleteBus(id) {
    if (confirm("Are you sure you want to delete this bus?")) {
        fetch(`/buses/${id}`, { method: "DELETE" })
            .then(() => {
                alert("Bus deleted successfully!");
                fetchBuses();
            })
            .catch(error => console.error("Error deleting bus:", error));
    }
}

// Search commuters by name
function searchCommuters() {
    const name = document.getElementById("searchName").value;
    fetch(`/commuters/search?name=${name}`)
        .then(response => response.json())
        .then(data => {
            const commuterList = document.getElementById("commuterList");
            commuterList.innerHTML = "";
            data.forEach(comm => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${comm.name}</td>
                    <td>${comm.ticketNumber}</td>
                    <td>
                        <button onclick="editCommuter(${comm.id}, '${comm.name}', '${comm.ticketNumber}')">Edit</button>
                        <button onclick="deleteCommuter(${comm.id})">Delete</button>
                    </td>
                `;
                commuterList.appendChild(row);
            });
        })
        .catch(error => console.error("Error searching commuters:", error));
}

// Edit Commuter
function editCommuter(id, name, ticket) {
    document.getElementById("commuterId").value = id;
    document.getElementById("commuterName").value = name;
    document.getElementById("commuterTicket").value = ticket;
}

// Update Commuter
function updateCommuter() {
    const id = document.getElementById("commuterId").value;
    const name = document.getElementById("commuterName").value;
    const ticketNumber = document.getElementById("commuterTicket").value;

    fetch(`/commuters/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, ticketNumber })
    })
        .then(() => {
            alert("Commuter updated successfully!");
            searchCommuters();
            document.getElementById("commuterForm").reset();
        })
        .catch(error => console.error("Error updating commuter:", error));
}

// Delete Commuter
function deleteCommuter(id) {
    if (confirm("Are you sure you want to delete this commuter?")) {
        fetch(`/commuters/${id}`, { method: "DELETE" })
            .then(() => {
                alert("Commuter deleted successfully!");
                searchCommuters();
            })
            .catch(error => console.error("Error deleting commuter:", error));
    }
}
