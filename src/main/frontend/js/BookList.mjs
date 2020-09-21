import {Ajax} from "./Commons.mjs";

export default class BookList {
    constructor(element) {
        this.element = element;
        this.element.addEventListener('click', this.clickEventHandler.bind(this));
        this.element.addEventListener('mouseover', this.hoverEventHandler);
        this.element.addEventListener('mouseout', this.hoverEventHandler);
    }

    update() {

    };

    delete() {

    };

    submit() {

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

        const td = tr.lastElementChild;
        if (td.classList.contains('invisible')) {
            td.classList.replace('invisible', 'visible');
        } else {
            td.classList.replace('visible', 'invisible');
        }
    }
}