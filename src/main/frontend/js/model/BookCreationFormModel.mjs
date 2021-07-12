import {Ajax} from "../util/Commons.mjs";

export default class BookCreationFormModel {

    constructor() {
        this.bookInfo = [];
    }

    async getSeojiInfo(isbn) {
        const response = await Ajax.request(`/api/seoji-info/${isbn}`);
        this.addBookInfo(response.data);
        return response.data;
    }

    addBookInfo(formData, index) {
        this.bookInfo[index] = formData;
    }
}