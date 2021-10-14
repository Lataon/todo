let todayDate = new Date();
//columns
const DESCR = 'Task';
const DATE = 'Date';
const FILE = 'File';

//buttons
let ADD = function () {
    this._name = 'Add';
    this.action = function () {
        showAddTaskForm();
    }
};
let FIX = function () {
    this._name = 'Fix';
    this.action = function () {
        if (isTasksNotSelected()) return;
        submitForm('fix')
    }
};
let REMOVE = function () {
    this._name = 'Remove';
    this.action = function () {
        if (isTasksNotSelected()) return;
        submitForm('remove');
    }
};
let REPAIR = function () {
    this._name = 'Repair';
    this.action = function () {
        if (isTasksNotSelected()) return;
        submitForm('repair');
    }
};
let DELETE = function () {
    this._name = 'Delete';
    this.action = function () {
        if (isTasksNotSelected()) return;
        submitForm('delete');
    }
};
let CLEAR = function () {
    this._name = 'Clear Bin';
    this.action = function () {
        document.tasks.filter.value = filter;
        submitForm('clear');
    }
};

//filters
const TODAY = function () {
    this._name = 'Today ' + getTodayDate();
    this._buttons = [ADD, FIX, REMOVE];
    this._columns = [DESCR, FILE];
    this._hasAdd = true;
    this.generateDateInput = function () {
        let currentDate = new Date();
        let addDate = document.getElementById("addDate");
        addDate.style.display = "none";
        document.tasks.date.valueAsDate = currentDate;
    }
};

const TOMORROW = function () {
    this._name = 'Tomorrow ' + getTomorrowDate();
    this._buttons = [ADD, FIX, REMOVE];
    this._columns = [DESCR, FILE];
    this._hasAdd = true;
    this.generateDateInput = function () {
        let currentDate = new Date();
        currentDate.setDate(currentDate.getDate() + 1);
        let addDate = document.getElementById("addDate");
        addDate.style.display = "none";
        document.tasks.date.valueAsDate = currentDate;
    }
};
const SOMEDAY = function () {
    this._name = 'Someday';
    this._buttons = [ADD, FIX, REMOVE];
    this._columns = [DESCR, DATE, FILE];
    this._hasAdd = true;
    this.generateDateInput = function (addDateDiv) {
        let currentDate = new Date();
        currentDate.setDate(currentDate.getDate() + 2);
        let addDate = document.getElementById("addDate");
        addDate.style.display = "block";
        document.tasks.date.valueAsDate = currentDate;
    }
};
const FIXED = function () {
    this._name = 'Fixed';
    this._buttons = [REMOVE];
    this._columns = [DESCR, DATE];
    this._hasAdd = false;
    this.generateDateInput = function (addDateDiv) {
        return false;
    }
};
const BIN = function () {
    this._name = 'Recycle Bin';
    this._buttons = [REPAIR, DELETE, CLEAR];
    this._columns = [DESCR, DATE];
    this._hasAdd = false;
    this.generateDateInput = function (addDateDiv) {
        return false;
    }
};

function getTodayDate() {
    return todayDate.getDate() + '.' + (todayDate.getMonth() + 1);
}

function getTomorrowDate() {
    return (todayDate.getDate() + 1) + '.' + (todayDate.getMonth() + 1);
}

function isTasksNotSelected() {
    let input_checkeds = document.querySelectorAll('input:checked');
    let flag = false;
    if (input_checkeds.length === 0) {
        alert('No tasks selected');
        flag = true;
    }
    return flag;
}

function submitForm(operation) {
    document.tasks.operation.value = operation;
    document.tasks.filter.value = filter;
    document.tasks.submit();
}

function showAddTaskForm() {
    showOrHideBlocks('block', 'none', 'none', 'none');
}