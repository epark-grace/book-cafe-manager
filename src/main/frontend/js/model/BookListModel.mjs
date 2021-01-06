import {Ajax} from "../util/Commons.mjs";

export default class BookListModel {
    async resetBookInfo(id) {
        return await Ajax.request(`/api/books/${id}`);
    }

    async updateBookInfo(modifiedBookInfo) {
        return await Ajax.request(`/api/books/${modifiedBookInfo.id}`, 'PUT', modifiedBookInfo);
    }

    async deleteBookInfo(id) {
        const result = await Ajax.request(`/api/books/${id}`, 'DELETE');
        result.bookInfo = {id: id};
        return result;
    }
}