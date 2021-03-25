import {Ajax} from "../util/Commons.mjs";

export default class ShelfEditorModel {
    async updateShelfName(existingShelfName, newShelfName) {
        const data = {newShelfName: newShelfName};
        return await Ajax.request('/books?shelf-name=' + existingShelfName, 'PATCH', data);
    }
    
}