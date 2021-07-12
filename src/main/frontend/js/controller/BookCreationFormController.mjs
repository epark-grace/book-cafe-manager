export default class BookCreationFormController {
    constructor(bookCreationForm, autoCompleteSearch) {
        this.bookCreationForm = bookCreationForm;
        this.autoCompleteSearch = autoCompleteSearch;
        this.bookCreationForm.view.bindEventHandler(this);
        this.autoCompleteSearch.view.bindEventHandler(this);
    }

    async handleSeojiInfoButtonClick(isbn, index) {
        const seojiInfo = await this.bookCreationForm.model.getSeojiInfo(isbn);
        this.bookCreationForm.model.addBookInfo(seojiInfo, index);
        this.bookCreationForm.view.renderBookCreationForm(seojiInfo, index);
    }

    async handleAutoCompleteSearch(title, parent) {
        const searchResult = await this.autoCompleteSearch.model.autoCompleteSearchByTitle(title);
        this.autoCompleteSearch.view.renderSearchResult(searchResult, parent);
    }

    handleSearchResultClear() {
        this.autoCompleteSearch.model.clearSearchResult();
    }

    handleTitleInputFocusIn() {
        this.autoCompleteSearch.view.visible();
    }

    handleTitleInputFocusOut() {
        this.autoCompleteSearch.view.reset();
        this.autoCompleteSearch.view.invisible();
    }

    handleSearchResultInput(trElement) {
        if (this.autoCompleteSearch.view.isSelected()) {
            const searchResult = this.autoCompleteSearch.model.getAutoCompleteSearchResultByIndex(this.autoCompleteSearch.view.getSelectedLiIndex());
            const trElementIndex = this.bookCreationForm.view.getTrElementIndex(trElement);

            this.bookCreationForm.model.addBookInfo(searchResult, trElementIndex);
            this.bookCreationForm.view.renderBookCreationForm(searchResult, trElementIndex);

            this.autoCompleteSearch.view.reset();
            this.autoCompleteSearch.view.invisible();
        }
    }
    
    handleSearchResultSelect(key) {
        this.autoCompleteSearch.view.setSelectedLiElement(key);
    }
}