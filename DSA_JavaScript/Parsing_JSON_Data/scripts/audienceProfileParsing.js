import { audienceProfileData } from './audienceProfiles.js';

function createTableForSegment(segment) {
    const table = document.createElement('table');

    Object.entries(segment).forEach(([key, value]) => {
        const row = document.createElement('tr');

        const headerCell = document.createElement('th');
        headerCell.innerText = key;
        row.appendChild(headerCell);

        const valueCell = document.createElement('td');
        if (typeof value === 'object' && value !== null) {
            const nestedTable = createTableForSegment(value);
            valueCell.appendChild(nestedTable);
        } else {
            valueCell.innerText = value;
        }
        row.appendChild(valueCell);

        table.appendChild(row);
    });

    return table;
}

function renderTables(data) {
    const container = document.getElementById('audienceProfileSheet');
    data.forEach(segment => {
        const card = document.createElement('div');
        card.className = 'card';

        const title = document.createElement('div');
        title.className = 'card-title';
        title.innerText = segment.audienceName || segment.companyName;
        card.appendChild(title);

        const table = createTableForSegment(segment);
        table.className = 'card-table';
        card.appendChild(table);

        card.onmouseenter = () => {
            // Expand the hovered card
            card.classList.add('expanded');
        };

        card.onmouseleave = () => {
            // Collapse the card when mouse leaves
            card.classList.remove('expanded');
        };

        container.appendChild(card);
    });
}

renderTables(audienceProfileData);
