import {Ajax, CssAction, Message} from "./Commons.mjs";

export default class BookList {
    constructor(element) {
        this.element = element;
        this.element.addEventListener('click', this.clickEventHandler.bind(this));
        this.element.addEventListener('mouseover', this.hoverEventHandler);
        this.element.addEventListener('mouseout', this.hoverEventHandler);
    }

    reset(tr) {
        const title = tr.cells[1].textContent;
        const id = tr.dataset.id;
        const answer = confirm(`[${title}] 입력된 정보를 되돌립니다.`);
        if (!answer) return;

        Ajax.request('GET', `/api/books/${id}`).then((response) => {
            this.renderBookInfo(tr, JSON.parse(response));
        });
    }

    update(tr) {
        const title = tr.cells[1].textContent;
        const id = tr.dataset.id;
        const answer = confirm(`[${title}] 도서를 수정하시겠습니까?`);

        if (!answer) return;

        const modifiedBookInfo = {
            id: id,
            title: title,
            categoryId: tr.cells[2].firstElementChild.value,
            author: tr.cells[3].textContent,
            publisher: tr.cells[4].textContent,
            volume: tr.cells[5].textContent,
            shelfName: tr.cells[6].textContent,
            rowNumber: tr.cells[7].textContent,
            finished: tr.cells[8].firstElementChild.value,
            forAdult: tr.cells[9].firstElementChild.value
        };

        Ajax.request('PUT', `/api/books/${id}`, JSON.stringify(modifiedBookInfo)).then((response) => {
            response = JSON.parse(response);
            this.renderBookInfo(tr, response.book);
            const messageWrapper = tr.cells[11].firstElementChild;
            if (response.count === 1) {
                Message.fadeOutMessage('수정되었습니다.', messageWrapper);
            } else {
                Message.fadeOutMessage('수정할 수 없습니다.', messageWrapper);
            }
        })
    };

    delete(tr) {
        const title = tr.cells[1].textContent;
        const answer = confirm(`[${title}] 도서를 삭제하시겠습니까?`);
        if (!answer) return;

        const id = tr.dataset.id;
        Ajax.request('DELETE', `/api/books/${id}`).then((response) => {
            const messageWrapper = tr.cells[11].firstElementChild;
            if (response === '1') {
                Message.fadeOutMessage('삭제되었습니다.', messageWrapper);
                setTimeout(() => tr.remove(), 1000, tr);
            } else {
                Message.fadeOutMessage('삭제할 수 없습니다.', messageWrapper);
            }
        });
    };

    renderBookInfo(tr, bookInfo) {
        tr.cells[1].textContent = bookInfo.title;
        tr.cells[2].firstElementChild.value = bookInfo.categoryId;
        tr.cells[3].textContent = bookInfo.author;
        tr.cells[4].textContent = bookInfo.publisher;
        tr.cells[5].textContent = bookInfo.volume;
        tr.cells[6].textContent = bookInfo.shelfName;
        tr.cells[7].textContent = bookInfo.rowNumber;
        tr.cells[8].firstElementChild.value = bookInfo.finished;
        tr.cells[9].firstElementChild.value = bookInfo.forAdult;
    }

    clickEventHandler(event) {
        const button = event.target.closest('button');
        if (!button) return;

        const feature = button.dataset.feature;
        this[feature](button.closest('tr'));
    }

    hoverEventHandler(event) {
        const tr = event.target.closest('tr');
        if (!tr) return;

        const div = tr.cells[10].firstElementChild;
        CssAction.toggleVisibility(div);
    }
}