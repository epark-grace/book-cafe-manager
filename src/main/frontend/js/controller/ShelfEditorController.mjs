export default class ShelfEditorController {
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.view.bindClickEventHandler(this);
    }

    async handleUpdateShelfName(existingShelfName, newShelfName) {
        return await this.model.updateShelfName(existingShelfName, newShelfName);
    }
}