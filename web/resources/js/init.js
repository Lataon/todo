const FILTER_MAP = new Map();
let filter;

function initFilterMap() {
    FILTER_MAP.set('today', TODAY);
    FILTER_MAP.set('tomorrow', TOMORROW);
    FILTER_MAP.set('someday', SOMEDAY);
    FILTER_MAP.set('fixed', FIXED);
    FILTER_MAP.set('bin', BIN);
}

function start() {
    const urlParams = new URLSearchParams(location.search);
    const userLogin = urlParams.get('login');
    filter = urlParams.get('filter');
    document.getElementById('login').innerHTML = "User: " + userLogin;
    generatePageContent();
    document.tasks.action = "/" + window.location.pathname.split("/")[1] + "/operations";
}

function generatePageContent() {
    generateUpperButtons();
    generateSectionTitle();
    generateTasksTable();
    generateAddTaskFormPart();
    showOrHideBlocks('none', 'block', 'none', 'none');
}