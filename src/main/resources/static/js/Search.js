function searchTable(searchText) {
    const table = document.querySelector('.table');
    const rows = table.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName('td');
        let shouldHide = true;

        for (let j = 0; j < cells.length; j++) {
            const cellText = cells[j].textContent.toLowerCase();
            if (cellText.includes(searchText.toLowerCase())) {
                shouldHide = false;
                break;
            }
        }

        if (shouldHide) {
            rows[i].style.display = 'none';
        } else {
            rows[i].style.display = '';
        }
    }
}




