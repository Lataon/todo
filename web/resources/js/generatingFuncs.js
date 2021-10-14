function generateUpperButtons() {
    initFilterMap();
    const upperButtons = document.getElementById('upperButtons');
    upperButtons.innerHTML = '';
    FILTER_MAP.forEach((v, k) => {
        let li = document.createElement('li');
        let a = document.createElement('a');
        a.setAttribute('href', '#');
        a.onclick = function () {
            filter = k;
            generatePageContent();
        };
        a.innerHTML = new v()._name;
        a.innerHTML += `&nbsp;&nbsp;`;
        li.appendChild(a);
        upperButtons.appendChild(li);
    });
}

function generateSectionTitle() {
    let filterClass = FILTER_MAP.get(filter);
    document.getElementById('sectionTitle').innerHTML = new filterClass()._name;
}

function generateTasksTableHead() {
    let head = document.getElementById("tableHead");
    let th = document.createElement('th');
    let input = document.createElement('input');
    input.setAttribute('type', 'checkbox');
    input.setAttribute('id', 'checkAll');
    input.onclick = function () {
        selectAllTasks();
    };
    th.appendChild(input);
    head.appendChild(th);
    let classFilter = FILTER_MAP.get(filter)
    let columns = new classFilter()._columns;
    for (let column of columns) {
        let th = document.createElement('th');
        th.innerHTML = column;
        head.appendChild(th);
    }
}

async function generateTasksTable() {
    const tasksBody = document.getElementById("tableBody");
    tasksBody.innerText = '';
    const head = document.getElementById("tableHead");
    head.innerText = '';
    const ul = document.getElementById('lowerButtons');
    ul.innerText = '';

    const url = `tasks?filter=${filter}`;
        const response = await fetch(url).then(res => {
            if (res.redirected) {
                window.location.href = res.url;
            }
            else return res
        }).catch(e => alert(e));

        if (response.ok) {
            let classFilter = FILTER_MAP.get(filter);
            let objFilter = new classFilter();

            const tasks = await response.json();
            if (tasks.length === 0) {
                showOrHideBlocks('none', 'block', 'block', 'none');
                if (objFilter._hasAdd) {
                    createButton(ul, ADD);
                }
                return false;
            }
            generateTasksTableHead();
            for (let task of tasks) {
                const tr = document.createElement('tr');
                const input = document.createElement('input');
                input.setAttribute('class', 'simpleCheckbox');
                input.setAttribute('type', 'checkbox');
                input.setAttribute('name', 'idTask');
                input.setAttribute('value', task.id);
                let td1 = document.createElement('td');
                td1.appendChild(input);
                tr.appendChild(td1);


                let columns = objFilter._columns;
                for (let column of columns) {
                    let td2 = document.createElement('td');
                    switch (column) {
                        case DESCR :
                            td2.innerHTML = task.description;
                            break;
                        case DATE :
                            td2.innerHTML = task.date;
                            break;
                        case FILE : {
                            createFileColumn(task, td2);
                        }
                    }
                    tr.appendChild(td2);
                }
                tasksBody.appendChild(tr);
            }
            generateUnderButtons();
        } else {
            createErrorMessage(response);
        }
}

function generateAddTaskFormPart() {
    let classFilter = FILTER_MAP.get(filter);
    let objFilter = new classFilter();
    objFilter.generateDateInput();
}

function generateUnderButtons() {
    let classFilter = FILTER_MAP.get(filter);
    let buttons = getUnderButtons(new classFilter());
    let ul = document.getElementById('lowerButtons');
    for (let button of buttons) {
        createButton(ul, button);
    }
}

function selectAllTasks() {
    let checkAll = document.getElementById('checkAll');
    let checkboxes = document.querySelectorAll('.simpleCheckbox');
    checkAll.addEventListener('change', function (e) {
        let isChecked = this.checked;
        for (let i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = isChecked;
        }
    });
}