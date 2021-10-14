function createFileColumn(task, td2) {
    let ul = document.createElement('ul');
    let fileName = "";
    if (task.filename === "null") {
        fileName = "No file ";
        let li = document.createElement('li');
        li.innerText = fileName;
        td2.appendChild(li);
    } else {
        fileName = task.filename;
        let li = document.createElement('li');
        li.innerText = fileName;
        td2.appendChild(li);
        let li1 = document.createElement('li');
        let download = document.createElement('a');
        download.innerText = "download";
        let hrefLoad = "/" + window.location.pathname.split("/")[1] + `/download?idTask=${task.id}`;
        download.setAttribute("href", hrefLoad);
        download.setAttribute("class", 'btn btn-outline-secondary');
        let li2 = document.createElement('li');
        let del = document.createElement('a');
        del.innerText = "delete";
        let hrefDel = "/" + window.location.pathname.split("/")[1] + `/delete?idTask=${task.id}`;
        del.setAttribute("href", hrefDel);
        del.setAttribute("class", 'btn btn-outline-secondary');
        li1.appendChild(download);
        li2.appendChild(del);
        ul.appendChild(li);
        ul.appendChild(li1);
        ul.appendChild(li2);
    }
    let li3 = document.createElement('li');
    let upload = document.createElement('a');
    upload.innerText = "upload";
    upload.setAttribute("href", "#");
    upload.setAttribute("class", 'btn btn-outline-secondary');
    upload.onclick = function () {
        showOrHideBlocks('none', 'block', 'none', 'block');
        uploadFile(task.id);
    };
    li3.appendChild(upload);
    ul.appendChild(li3);
    td2.appendChild(ul);
}

async function createErrorMessage(response) {
    const RE_1 = /<b>.+<\/b>/;
    const RE_2 = /<\/p>/;

    let main = document.getElementById("main");
    main.innerText = '';

    let errorBlock = document.getElementById('error-block');
    errorBlock.innerText = '';

    let errorAnswer = await response.text();

    let messageHeader = document.createElement('span');
    messageHeader.innerText = 'Sorry, critical error on the server: "';
    errorBlock.appendChild(messageHeader);
        let messageBody = document.createElement('span');
        let message = errorAnswer.split("<p>")[2].replace(RE_1, '').replace(RE_2, '').trim();
        message += '".';
        messageBody.innerText = message;

        let messageFooter = document.createElement("span");
        let spanHeader = document.createElement('span');
        spanHeader.innerText = ' You can ';
        let a = document.createElement('a');
        a.innerText = 'logout';
        a.setAttribute("href", '#');
        a.onclick = function () {
            logoutFun();
        };
        let spanFooter = document.createElement('span');
        spanFooter.innerText = ' or try again in several minutes...';
        messageFooter.appendChild(spanHeader);
        messageFooter.appendChild(a);
        messageFooter.appendChild(spanFooter);

        errorBlock.appendChild(messageBody);
        errorBlock.appendChild(messageFooter);

}

function createButton(ul, buttonClass) {
    let li = document.createElement('li');
    let a = document.createElement('a');
    let obj = new buttonClass();
    a.innerText = obj._name;
    a.setAttribute("href", "#");
    a.onclick = function () {
        obj.action();
    };
    li.appendChild(a);
    ul.appendChild(li);
}

function getUnderButtons(filter) {
    return filter._buttons;
}

function uploadFile(idTask) {
    let form = document.getElementById("upload-form");
    form.action = "/" + window.location.pathname.split("/")[1] + `/upload?idTask=${idTask}`;
    document.upload.filter.value = filter;
    let uploadButton = document.getElementById('uploadButton');

    uploadButton.onclick = function () {
        if (document.upload.fileToUpload.value === '') {
            alert("No file was selected");
            return false;
        }
        const fileSizeLimit = 4;
        if (!isFileSizeInRange(fileSizeLimit)) {
            alert('File size must be less them ' + fileSizeLimit + 'mb');
            return false;
        }
        document.upload.submit();
    }
}

function isFileSizeInRange(sizeInMb) {
    let file = document.getElementById("addFileInputId").files[0];
    return file.size <= (1024 * 1024 * sizeInMb);
}