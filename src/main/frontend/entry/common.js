import "../css/tailwind.css"

const sendAjaxRequest = (method, url, data) => new Promise(resolve => {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', () => resolve(xhr.responseText));
    xhr.open(method, url);
    if (method === 'GET') {
        xhr.send();
    } else {
        xhr.send(data);
    }
})