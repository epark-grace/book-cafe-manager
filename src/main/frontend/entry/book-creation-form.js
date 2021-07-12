import BookCreationFormController from "../js/controller/BookCreationFormController.mjs";
import BookCreationFormModel from "../js/model/BookCreationFormModel.mjs";
import BookCreationFormView from "../js/view/BookCreationFormView.mjs";
import AutoCompleteSearchModel from "../js/model/AutoCompleteSearchModel.mjs";
import AutoCompleteSearchView from "../js/view/AutoCompleteSearchView.mjs";

const bookCreationForm = {
    model: new BookCreationFormModel(),
    view: new BookCreationFormView(document.getElementById("book-creation-form"))
};

const autoCompleteSearch = {
    model: new AutoCompleteSearchModel(),
    view: new AutoCompleteSearchView(document.createElement('ul'))
};

new BookCreationFormController(bookCreationForm, autoCompleteSearch);