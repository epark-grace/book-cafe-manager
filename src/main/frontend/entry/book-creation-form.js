import BookCreationFormController from "../js/controller/BookCreationFormController.mjs";
import BookCreationFormModel from "../js/model/BookCreationFormModel.mjs";
import BookCreationFormView from "../js/view/BookCreationFormView.mjs";

new BookCreationFormController(new BookCreationFormModel(), new BookCreationFormView(document.getElementById("book-creation-form")));