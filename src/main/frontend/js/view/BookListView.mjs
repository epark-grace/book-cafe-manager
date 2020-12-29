import {CssAction} from "../util/Commons";

export default class BookListView {
    constructor(bookList) {
        this.bookList = bookList;
    }

    bindClickButtonEventHandler(controller) {
        this.bookList.addEventListener('click', async (event) => {
            const button = event.target.closest('button');
            if (!button) return;

            const tr = button.closest('tr');
            const title = tr.cells[1].textContent;
            const id = tr.dataset.id;
            const feature = button.dataset.feature;
            let answer;

            if (feature === 'reset') {
                answer = confirm(`[${title}] 입력된 정보를 되돌립니다.`);
            } else if (feature === 'delete') {
                answer = confirm(`[${title}] 도서를 삭제합니다.`);
            } else if (feature === 'update') {
                answer = confirm(`[${title}] 도서를 수정합니다.`);
            }

            if (!answer) return;

            const handler = `handle${feature[0].toUpperCase()}${feature.slice(1)}BookInfo`;
            const count = await controller[handler](id);

            if (feature === 'update') {
                if (count === 1) {
                    alert('수정되었습니다.');
                } else {
                    alert('수정할 수 없습니다.');
                }
            } else if (feature === 'delete') {
                if (count === 1) {
                    alert('삭제되었습니다.');
                } else {
                    alert('삭제할 수 없습니다.');
                }
            }
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
            id: id,
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

    renderBookInfoForm(bookInfo) {
        const tr = document.querySelector(`tr[data-id="${bookInfo.id}"`);

        if (bookInfo.title === undefined) {
            setTimeout(() => tr.remove(), 1000, tr);
        } else {
            tr.cells[1].textContent = bookInfo.title;
            if (bookInfo.categoryId != null) {
                tr.cells[2].firstElementChild.value = bookInfo.categoryId;
            } else {
                tr.cells[2].firstElementChild.value = '';
            }
            tr.cells[3].textContent = bookInfo.author;
            tr.cells[4].textContent = bookInfo.publisher;
            tr.cells[5].textContent = bookInfo.volume;
            tr.cells[6].textContent = bookInfo.shelfName;
            tr.cells[7].textContent = bookInfo.rowNumber;
            tr.cells[8].firstElementChild.value = bookInfo.finished;
            tr.cells[9].firstElementChild.value = bookInfo.forAdult;
        }
    }
}