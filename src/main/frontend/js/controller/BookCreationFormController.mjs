export default class BookCreationFormController {
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.view.bindClickButtonEventHandler(this);
    }

    async handleGetSeojiInfo(isbn) {
        const response = await this.model.getSeojiInfo(isbn);
        this.view.renderBookCreationForm(response.data);
    }
}