// Empty JS for your own code to be here

const base_url = 'http://127.0.0.1:8080';

function get(path) {
    return fetch(base_url + path, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        mode: 'cors'
    });
}

function post(path, data) {
    return fetch(base_url + path, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        mode: 'cors',
        body: JSON.stringify(data)
    });
}

function render(title, contents) {
    const titleElement = document.querySelector('#data-title');
    if (titleElement === null) {
        console.error('Cannot find data title area');
    } else {
        titleElement.innerText = title;
    }
    const bodyElement = document.querySelector('#data-body');
    if (bodyElement === null) {
        console.error('Cannot find data body area')
    } else {
        bodyElement.querySelectorAll('*').forEach(node => node.remove());
        for (let i = 0; i < contents.length; ++i) {
            const numberCell = document.createElement('td');
            numberCell.innerText = i + 1;
            const contentCell = document.createElement('td');
            contentCell.innerText = contents[i].content;
            const row = document.createElement('tr');
            row.appendChild(numberCell);
            row.appendChild(contentCell);
            bodyElement.appendChild(row);
        }
    }
}

function message(message) {
    console.info(`message: ${message}`);
    document.querySelector('#message-region').innerText = message;
    document.querySelector('#error-region').innerHTML = '';
}

function error(message) {
    console.info(`error: ${message}`);
    document.querySelector('#message-region').innerText = '';
    document.querySelector('#error-region').innerHTML = message;
}

get('/results/batch/4d330ea2-9875-4290-a1ee-2e75f09addea').then(response => {
    response.json().then(data => {
        render('索引结果', data.results);
        message(`共 ${data.results.length} 条结果`);
    })
})
