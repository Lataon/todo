function cancelUpload() {
    document.getElementById('upload').style.display = 'none';
}

function cancelAdding() {
    showOrHideBlocks('none', 'block', 'none', 'none');
}

function sendAddTaskForm() {
    if (!isAddTaskOperationDataValid()) return;
    submitForm('add');
}

function showOrHideBlocks(styleAddTask, styleTaskTable, styleEmptyTasks, styleUploadFile) {
    document.getElementById('addTask').style.display = styleAddTask;
    document.getElementById('taskTable').style.display = styleTaskTable;
    document.getElementById('emptyTasksMsg').style.display = styleEmptyTasks;
    document.getElementById('upload').style.display = styleUploadFile;
}

function isAddTaskOperationDataValid() {
    const err = document.getElementById("errorText");
    let text = document.tasks.text.value;
    let flag = true;

    if (text === "") {
        err.innerHTML = "You have not entered the text of the task";
        flag = false;
    }

    let inputDate = new Date(document.tasks.date.value);
    let now = new Date();
    now.setDate(now.getDate() - 1);
    if (isNaN(inputDate.getTime()) || (inputDate < now)) {
        err.innerHTML = "You entered the wrong date";
        flag = false;
    }
    return flag;
}

function logoutFun() {
    document.logout.submit();
}