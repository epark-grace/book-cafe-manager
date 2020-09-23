import {Ajax} from "./Commons.mjs";

export default class BookList {
    constructor(element) {
        this.element = element;
        this.element.addEventListener('click', this.clickEventHandler.bind(this));
        this.element.addEventListener('mouseover', this.hoverEventHandler);
        this.element.addEventListener('mouseout', this.hoverEventHandler);
    }

    update(tr) {
        const title = tr.cells[1].innerText;
        const answer = confirm(`[${title}] 도서를 수정하시겠습니까?`);
        if (!answer) return;

        const id = tr.dataset.id;
        const modifiedBookInfo = {
            id: id,
            title: tr.cells[1].innerText,
            categoryId: tr.cells[2].firstElementChild.value,
            author: tr.cells[3].innerText,
            publisher: tr.cells[4].innerText,
            volume: tr.cells[5].innerText,
            shelfName: tr.cells[6].innerText,
            rowNumber: tr.cells[7].innerText,
            finished: tr.cells[8].firstElementChild.value,
            forAdult: tr.cells[9].firstElementChild.value
        };
        Ajax.request('PUT', `/api/books/${id}`, JSON.stringify(modifiedBookInfo)).then((response) => {
            if (response === '1') {
                alert('수정되었습니다.');
            }
        })
    };

    delete(tr) {
        const title = tr.cells[1].innerText;
        const answer = confirm(`[${title}] 도서를 삭제하시겠습니까?`);
        if (!answer) return;

        const id = tr.dataset.id;
        Ajax.request('DELETE', `/api/books/${id}`).then((response) => {
            if (response === '1') {
                alert('삭제되었습니다.');
                tr.remove();
            }
        });
    };

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
        if (div.classList.contains('invisible')) {
            div.classList.replace('invisible', 'visible');
        } else {
            div.classList.replace('visible', 'invisible');
        }
    }
}