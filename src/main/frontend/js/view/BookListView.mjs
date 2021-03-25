import {CssAction} from "../util/Commons.mjs";

export default class BookListView {
    constructor(bookList) {
        this.bookList = bookList;
    }

    bindClickButtonEventHandler(controller) {
        this.bookList.addEventListener('click', async (event) => {
            const button = event.target.closest('button');
            if (!button) return;

            const feature = button.dataset.feature;
            const tr = button.closest('tr');
            const title = tr.cells[1].textContent;
            let answer;

            if (feature === 'reset') {
                answer = confirm(`[${title}] 입력된 정보를 되돌립니다.`);
            } else if (feature === 'delete') {
                answer = confirm(`[${title}] 도서를 삭제합니다.`);
            } else if (feature === 'update') {
                answer = confirm(`[${title}] 도서를 수정합니다.`);
            }

            if (!answer) return;

            const id = tr.dataset.id;
            const handler = `handle${feature[0].toUpperCase()}${feature.slice(1)}BookInfo`;
            const message = await controller[handler](id);
            alert(message);
        });
    }

    bindHoverEventHandler() {
        function hoverEvent(event) {
            const tr = event.target.closest('tr');
            if (!tr) return;

            const div = tr.cells[10].firstElementChild;
            CssAction.toggleVisibility(div);
        }

        this.bookList.addEventListener('mouseover', hoverEvent);
        this.bookList.addEventListener('mouseout', hoverEvent);
    }

    getModifiedBookInfo(id) {
        const tr = document.querySelector(`tr[data-id="${id}"`);
        return {
            title: tr.cells[1].textContent,
            categoryId: tr.cells[2].firstElementChild.value,
            author: tr.cells[3].textContent,
            publisher: tr.cells[4].textContent,
            volume: tr.cells[5].textContent,
            shelfName: tr.cells[6].textContent,
            rowNumber: tr.cells[7].textContent,
            finished: tr.cells[8].firstElementChild.value,
            forAdult: tr.cells[9].firstElementChild.value
        };
    }

    renderBookInfoForm(data) {
        const tr = document.querySelector(`tr[data-id="${data.id}"`);

        if (data.title === undefined) {
            setTimeout(() => tr.remove(), 1000, tr);
        } else {
            tr.cells[1].textContent = data.title;
            if (data.categoryId != null) {
                tr.cells[2].firstElementChild.value = data.categoryId;
            } else {
                tr.cells[2].firstElementChild.value = '';
            }
            tr.cells[3].textContent = data.author;
            tr.cells[4].textContent = data.publisher;
            tr.cells[5].textContent = data.volume;
            tr.cells[6].textContent = data.shelfName;
            tr.cells[7].textContent = data.rowNumber;
            tr.cells[8].firstElementChild.value = data.finished;
            tr.cells[9].firstElementChild.value = data.forAdult;
        }
    }
}