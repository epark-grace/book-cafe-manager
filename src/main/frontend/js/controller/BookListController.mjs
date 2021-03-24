export default class BookListController {
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.view.bindClickButtonEventHandler(this);
        this.view.bindHoverEventHandler();
    }

    async handleResetBookInfo(id) {
        const response = await this.model.resetBookInfo(id);
        this.view.renderBookInfoForm(response.data);
    }

    async handleUpdateBookInfo(id) {
        const modifiedBookInfo = this.view.getModifiedBookInfo(id);
        const response = await this.model.updateBookInfo(id, modifiedBookInfo);
        this.view.renderBookInfoForm(response.data);
        return response.message;
    };

    async handleDeleteBookInfo(id) {
        const response = await this.model.deleteBookInfo(id);
        const data = { id: response.data };
        this.view.renderBookInfoForm(data);
        return response.message;
    };
}