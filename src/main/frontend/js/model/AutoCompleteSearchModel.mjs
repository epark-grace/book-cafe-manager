import {Ajax} from "../util/Commons.mjs";

export default class AutoCompleteSearchModel {

    constructor() {
        this.autoCompleteSearchResult = [];
    }

    async autoCompleteSearchByTitle(title) {
        const response = await Ajax.request(`/api/books?criteria=title&keyword=${title}`);
        this.autoCompleteSearchResult = response.data;
        return response.data;
    }

    getAutoCompleteSearchResultByIndex(index) {
        return this.autoCompleteSearchResult[index];
    }

    clearSearchResult() {
        this.autoCompleteSearchResult = [];
    }
}