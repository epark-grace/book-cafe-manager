import {Ajax} from "../util/Commons.mjs";

export default class BookListModel {
    async resetBookInfo(id) {
        return await Ajax.request(`/api/books/${id}`).json();
    }

    async updateBookInfo(id, modifiedBookInfo) {
        return await Ajax.request(`/api/books/${id}`, 'PUT', modifiedBookInfo).json();
    }

    async deleteBookInfo(id) {
        return await Ajax.request(`/api/books/${id}`, 'DELETE').json();
    }
}