// Empty JS for your own code to be here

// const base_url = 'http://127.0.0.1:8080';
const base_url = 'https://api.tree-diagram.site/kwic';

let request = null;
let currentContents = null;
let currentTitle = null;

function get(path) {
    return fetch(base_url + path, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include'
    });
}

function post(path, data) {
    return fetch(base_url + path, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        mode: 'cors',
        body: JSON.stringify(data),
        credentials: 'include'
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
    currentContents = contents;
    currentTitle = title;
}

function message(message) {
    console.info(message);
    document.querySelector('#message-region').innerText = message;
    document.querySelector('#error-region').innerHTML = '';
}

function error(message) {
    console.error(message);
    document.querySelector('#message-region').innerText = '';
    document.querySelector('#error-region').innerHTML = message;
}

function save(filename, type, content) {
    const element = document.createElement('a');
    element.setAttribute('href', `data:${type};charset=utf-8,${encodeURIComponent(content)}`);
    element.setAttribute('download', filename);
    element.style.display = 'none';
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
}

function importFromFile(file) {
    const reader = new FileReader();
    reader.addEventListener('load', event => {
        const content = event.target.result;
        message('导入文件成功');
        message('正在上传...');
        post('/corpus/cache', {
            items: [
                {
                    content: content
                }
            ]
        }).then(response => {
            if (!response.ok) {
                error('上传错误');
                response.json().then(data => {
                    console.error(data);
                });
            } else {
                get('/corpus/cache').then(response => {
                    response.json().then(data => {
                        // console.log(data);
                        render('原文', data.items);
                        message(`输入：文件 ${file.name}`);
                        document.querySelector('#start-index-button').disabled = false;
                        request = () => {
                            return post('/indexing/cache');
                        };
                    })
                })
            }
        });
    });
    message('正在导入文件...');
    reader.readAsText(file);
}

function importFromBatch(batchId) {
    message('正在获取原文...');
    get(`/corpus/batch/${batchId}`).then(response => {
        response.json().then(data => {
            const items = data.items;
            if (items.length === 0) {
                error('该批次存储的原文为空；是否输入错误的 Batch ID？');
            } else {
                render('原文', items);
                message(`输入：数据库中批次 ${batchId}`);
                document.querySelector('#start-index-button').disabled = false;
                request = () => {
                    return post(`/indexing/corpus/${batchId}`);
                }
            }
        })
    }
    )
}

function startIndexing() {
    message('正在进行索引...');
    request().then(response => {
        message('正在获取索引结果...');
        get('/results/cache').then(response => {
            response.json().then(data => {
                render('索引结果', data.results);
                message('索引完成');
            })
        })
    })
}

function exportPlainText() {
    let plainContents = [];
    currentContents.forEach(content => {
        plainContents.push(content.content);
    });
    save(`${currentTitle}-${new Date().toISOString()}.txt`, 'text/plain', plainContents.join('\n'));
}

function exportCsv() {
    let items = [
        `"#","${currentTitle}"`
    ];
    for (let i = 0; i < currentContents.length; ++i) {
        items.push(`"${i}","${currentContents[i].content}"`);
    }
    save(`${currentTitle}-${new Date().toISOString()}.csv`, 'text/csv', items.join('\n'));
}

function exportDatabase() {
    message('正在保存至数据库...');
    post('/results/cache/persistence').then(response => {
        if (!response.ok) {
            error('保存失败');
            console.error(response);
        } else {
            response.json().then(data => {
                message(`保存成功，结果集 Batch ID 为 ${data.batchId}`);
            })
        }
    });
}

document.querySelector('#file-picker').addEventListener('change', event => {
    importFromFile(event.target.files[0]);
});

document.querySelector('#database-picker').addEventListener('click', event => {
    console.log('Clicked')
    const batchId = prompt('请输入数据库中对应批次的 Batch ID');
    if (batchId) {
        importFromBatch(batchId);
    }
});

document.querySelector('#export-text-button').addEventListener('click', exportPlainText);

document.querySelector('#export-csv-button').addEventListener('click', exportCsv);

document.querySelector('#export-database-button').addEventListener('click', exportDatabase);

document.querySelector('#start-index-button').disabled = true;

document.querySelector('#start-index-button').addEventListener('click', startIndexing);
