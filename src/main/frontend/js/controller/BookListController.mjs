export default class BookListController {
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.view.bindClickButtonEventHandler(this);
        this.view.bindHoverEventHandler();
    }

    async handleResetBookInfo(id) {
        const bookInfo = await this.model.resetBookInfo(id);
        this.view.renderBookInfoForm(bookInfo);
    }

    async handleUpdateBookInfo(id) {
        const modifiedBookInfo = this.view.getModifiedBookInfo(id);
        const result = await this.model.updateBookInfo(modifiedBookInfo);
        this.view.renderBookInfoForm(result.bookInfo);
        return result.count;
    };

    async handleDeleteBookInfo(id) {
        const result = await this.model.deleteBookInfo(id);
        this.view.renderBookInfoForm(result.bookInfo);
        return result.count;
    };
}