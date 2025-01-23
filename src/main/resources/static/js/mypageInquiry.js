// 상세보기 토글
function toggleDetails(id) {
    let detailedRow = document.getElementById("detailed-row-" + id);
    let btnStatus = document.getElementById("btn-status-" + id);
    // let detailedSection = document.getElementById("detailed-section-" + id);
    if (detailedRow.style.display === "none") {
        detailedRow.style.display = "table-row";
        console.log(btnStatus);
        btnStatus.textContent = "▲";
        console.log(btnStatus);
        // detailedSection.style.display = "block";
    } else {
        detailedRow.style.display = "none";
        btnStatus.textContent = "▼";
        // detailedSection.style.display = "none";
    }
}
