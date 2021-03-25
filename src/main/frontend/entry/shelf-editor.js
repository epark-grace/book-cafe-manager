import ShelfEditorController from "../js/controller/ShelfEditorController.mjs";
import ShelfEditorModel from "../js/model/ShelfEditorModel.mjs";
import ShelfEditorView from "../js/view/ShelfEditorView.mjs";

new ShelfEditorController(
    new ShelfEditorModel(),
    new ShelfEditorView(document.getElementById('before'),
        document.getElementById('after'),
        document.getElementById('save'))
);