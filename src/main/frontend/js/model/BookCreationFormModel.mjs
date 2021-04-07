import {Ajax} from "../util/Commons.mjs";

export default class BookCreationFormModel {
    async getSeojiInfo(isbn) {
        return await Ajax.request(`/api/seoji-info/${isbn}`);
    }
}