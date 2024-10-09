
function fetchDeliveries() {
    fetch("/api/properties")
        .then(response => response.json())
        .then(data => {
            deliveryTableBody.innerHTML = "";

            data.forEach(delivery => {
                const row = document.createElement("tr");


                const idCell = document.createElement("td");
                idCell.textContent = delivery.id;
                row.appendChild(idCell);

                const addressCell = document.createElement("td");
                addressCell.textContent = delivery.address;
                row.appendChild(addressCell);

                const priceCell = document.createElement("td");
                priceCell.textContent = delivery.price;
                row.appendChild(priceCell);

                const sizeCell = document.createElement("td");
                sizeCell.textContent = delivery.size;
                row.appendChild(sizeCell);

                const descriptionCell = document.createElement("td");
                descriptionCell.textContent = delivery.description;
                row.appendChild(descriptionCell);


                deliveryTableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching deliveries:", error));
}



window.onload = function() {
    fetchDeliveries();
};


document.getElementById("deliveryForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const delivery = {
        address: document.getElementById("address").value,
        price: document.getElementById("price").value,
        size: document.getElementById("size").value,
        description: document.getElementById("description").value
    };

    const id = document.getElementById("id").value;

    if (id) {

        fetch(`/api/properties/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(delivery)
        })
        .then(response => {
            if (response.ok) {
                alert(`Delivery with ID: ${id} updated successfully!`);
                document.getElementById("deliveryForm").reset();
                fetchDeliveries();
            } else {
                alert(`Failed to update delivery with ID: ${id}`);
            }
        })
        .catch(error => console.error("Error updating delivery:", error));
    } else {

        fetch("/api/properties", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(delivery)
        })
        .then(response => response.json())
        .then(data => {
            alert(`Delivery added with ID: ${data.id}`);
            document.getElementById("deliveryForm").reset();
            fetchDeliveries();
        })
        .catch(error => console.error("Error adding delivery:", error));
    }
});


document.getElementById("deleteDeliveryBtn").addEventListener("click", function() {
    const id = document.getElementById("id").value;

    if (id) {
        fetch(`/api/properties/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                alert(`Delivery with ID: ${id} deleted successfully!`);
                document.getElementById("deliveryForm").reset();
                fetchDeliveries();
            } else {
                alert(`Failed to delete delivery with ID: ${id}`);
            }
        })
        .catch(error => console.error("Error deleting delivery:", error));
    } else {
        alert("Please provide a valid ID to delete a delivery.");
    }
});
