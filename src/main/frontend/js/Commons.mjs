export class Ajax {
    static request(method, url, data) {
        return new Promise(resolve => {
            const xhr = new XMLHttpRequest();
            xhr.addEventListener('load', () => resolve(xhr.responseText));
            xhr.open(method, url);
            if (method === 'GET') {
                xhr.send();
            } else {
                xhr.send(data);
            }
        });
    }
}