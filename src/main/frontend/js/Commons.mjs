export class Ajax {
    static request(method, url, data) {
        return new Promise(resolve => {
            const xhr = new XMLHttpRequest();
            xhr.addEventListener('load', () => resolve(xhr.responseText));
            xhr.open(method, url);
            if (method === 'GET') {
                xhr.send();
            } else {
                xhr.setRequestHeader('Content-Type', 'application/json');
                xhr.send(data);
            }
        });
    }
}

export class CssAction {
    static toggleOpacity(element) {
        element.classList.toggle('opacity-100');
        element.classList.toggle('opacity-0');
    }

    static toggleVisibility(element) {
        element.classList.toggle('invisible');
        element.classList.toggle('visible');
    }
}