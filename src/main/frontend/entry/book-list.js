import BookListController from "../js/controller/BookListController";
import BookListModel from "../js/model/BookListModel";
import BookListView from "../js/view/BookListView";

new BookListController(new BookListModel(), new BookListView(document.getElementById('book-LIST')));