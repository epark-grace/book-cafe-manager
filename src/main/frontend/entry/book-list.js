import BookListController from "../js/controller/BookListController.mjs";
import BookListModel from "../js/model/BookListModel.mjs";
import BookListView from "../js/view/BookListView.mjs";

new BookListController(new BookListModel(), new BookListView(document.getElementById('book-list')));