export class Ajax {
    static async request(url, method, data) {
        let response;

        if (!method) {
            response = await fetch(url);
        } else {
            const init = {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            };
            response = await fetch(url, init);
        }

        return response.json();
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

export class Message {
    static fadeOutMessage(message, wrapper) {
        wrapper.textContent = message;
        wrapper.className = 'transition-opacity opacity-100 duration-1000';
        CssAction.toggleOpacity(wrapper);

        setTimeout(() => {
            wrapper.textContent = null;
            CssAction.toggleOpacity(wrapper);
        }, 1000, wrapper);
    }
}