export default class ShelfEditorView {
    constructor(beforeInput, afterInput, saveButton) {
        this.beforeInput = beforeInput;
        this.afterInput = afterInput;
        this.saveButton = saveButton;
    }

    bindClickEventHandler(controller) {
        this.saveButton.addEventListener('click', async () => {
            const existingShelfName = this.beforeInput.value;
            const newShelfName = this.afterInput.value;
            await controller.handleUpdateShelfName(existingShelfName, newShelfName);
            alert(`책장번호 [${existingShelfName}]가 [${newShelfName}]로 수정되었습니다.`);
            this.beforeInput.value = null;
            this.afterInput.value = null;
        })
    }
}