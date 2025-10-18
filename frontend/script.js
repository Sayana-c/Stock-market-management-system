const API_URL = "/api/stocks";

document.addEventListener("DOMContentLoaded", loadStocks);

function loadStocks() {
  fetch(API_URL)
    .then(res => res.json())
    .then(data => {
      const tbody = document.querySelector("#stockTable tbody");
      tbody.innerHTML = "";
      data.forEach(stock => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td>${stock.id ?? ''}</td>
          <td>${stock.symbol ?? ''}</td>
          <td>${stock.companyName ?? ''}</td>
          <td>${stock.price ?? ''}</td>
          <td>
            <button class="btn-del" data-id="${stock.id}">Delete</button>
          </td>
        `;
        tbody.appendChild(tr);
      });

      // attach delete handlers
      document.querySelectorAll(".btn-del").forEach(btn =>
        btn.addEventListener("click", () => deleteStock(btn.dataset.id))
      );
    })
    .catch(err => console.error("Error loading stocks:", err));
}

document.getElementById("stockForm").addEventListener("submit", e => {
  e.preventDefault();
  const stock = {
    symbol: document.getElementById("symbol").value.trim(),
    companyName: document.getElementById("company").value.trim(),
    price: parseFloat(document.getElementById("price").value)
  };
  fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(stock)
  })
  .then(res => res.json())
  .then(() => {
    document.getElementById("stockForm").reset();
    loadStocks();
  })
  .catch(err => console.error("Error saving stock:", err));
});

function deleteStock(id) {
  fetch(`${API_URL}/${id}`, { method: "DELETE" })
    .then(res => {
      if (res.ok) loadStocks();
      else console.warn("Delete failed", res.status);
    });
}
