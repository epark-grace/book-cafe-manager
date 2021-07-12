import {debounce} from "../util/Commons.mjs";

export default class BookCreationFormView {
    constructor(bookCreationFormElement) {
        this.bookCreationFormElement = bookCreationFormElement;
    }

    bindEventHandler(controller) {
        this.bindSeojiInfoButtonEventHandler(controller);
        this.bindAutoCompleteSearchEventHandler(controller);
        this.bindKeyDownSearchResultEventHandler(controller);
        this.bindFocusTitleEventHandler(controller);
    }

    bindSeojiInfoButtonEventHandler(controller) {
        this.bookCreationFormElement.addEventListener('submit', async (event) => {
            event.preventDefault();
            const form = event.target;
            const isbn = form.firstElementChild.value;
            const trElement = form.closest('tr');
            const index = this.getTrElementIndex(trElement);
            await controller.handleSeojiInfoButtonClick(isbn, index);
        });
    }

    bindFocusTitleEventHandler(controller) {
        this.bookCreationFormElement.addEventListener('focusin', (event) => {
            if (event.target.dataset.type !== 'title') return;

            const inputElement = event.target;
            if (inputElement.value === '') return;

            controller.handleTitleInputFocusIn();
        });

        this.bookCreationFormElement.addEventListener('focusout', (event) => {
            if (event.target.dataset.type !== 'title') return;
            controller.handleTitleInputFocusOut();
        });
    }

    bindAutoCompleteSearchEventHandler(controller) {
        const autoCompleteSearch = async (event) => {
            if (event.target.dataset.type !== 'title') return;

            const inputElement = event.target;
            const title = inputElement.value;

            if (title === '') {
                controller.handleSearchResultClear();
            } else {
                await controller.handleAutoCompleteSearch(title, inputElement.parentElement);
            }
        }

        this.bookCreationFormElement.addEventListener('input', debounce(autoCompleteSearch), 300);
    }

    bindKeyDownSearchResultEventHandler(controller) {
        this.bookCreationFormElement.addEventListener('keydown', (event) => {
            const inputElement = event.target;
            if (inputElement.dataset.type !== 'title') return;

            if (event.key === 'ArrowDown' || event.key === 'ArrowUp') {
                controller.handleSearchResultSelect(event.key);
            } else if (event.key === 'Enter') {
                controller.handleSearchResultInput(inputElement);
            }
        })
    }

    renderBookCreationForm(formData, index) {
        const td = {
            title: 2,
            categoryId: 3,
            author: 4,
            publisher: 5,
            volume: 6,
            shelfName: 7,
            rowNumber: 8,
            finished: 9,
            forAdult: 10
        };
        const trElement = this.bookCreationFormElement.firstElementChild.children.item(index);

        for (let [key, value] of Object.entries(formData)) {
            if (key === 'id') {
                trElement.dataset.bookId = value;
            } else {
                trElement.cells[td[key]].firstElementChild.value = value;
            }
        }
    }

    getTrElementIndex(element) {
        return [...this.bookCreationFormElement.firstElementChild.children].indexOf(element);
    }
}