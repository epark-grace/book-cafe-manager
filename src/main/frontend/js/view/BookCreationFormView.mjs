export default class BookCreationFormView {
    constructor(bookCreationForm) {
        this.bookCreationForm = bookCreationForm;
    }

    bindClickButtonEventHandler(controller) {
        this.bookCreationForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const form = event.target;
            const isbn = form.firstElementChild.value;
            form.dataset.isbn = isbn;
            controller.handleGetSeojiInfo(isbn);
        });
    }

    renderBookCreationForm(data) {
        const input = document.querySelector(`form[data-isbn="${data.isbn}"]`);
        const tr = input.parentElement.parentElement;
        tr.cells[2].firstElementChild.value = data.title;
        tr.cells[4].firstElementChild.value = data.author;
        tr.cells[5].firstElementChild.value = data.publisher;
        tr.cells[6].firstElementChild.value = data.volume;
    }
}