export default class AutoCompleteSearchView {
    constructor(searchResultElement) {
        this.searchResultElement = searchResultElement;
        this.searchResultElement.className = 'absolute invisible';
        this.selectedLi = null;
    }

    bindEventHandler(controller) {
        this.bindHoverSearchResultEventHandler();
        this.bindMouseDownSearchResultEventHandler(controller);
    }

    bindHoverSearchResultEventHandler() {
        this.searchResultElement.addEventListener('mouseover', (event) => {
            if (this.isSelected()) {
                this.removeLiBackground();
            }
            this.selectedLi = event.target;
            this.addLiBackground();
        });

        this.searchResultElement.addEventListener('mouseout', (event) => {
            if (this.isSelected()) {
                this.reset();
            }
        });
    }

    bindMouseDownSearchResultEventHandler(controller) {
        this.searchResultElement.addEventListener('mousedown', (event) => {
            controller.handleSearchResultInput(this.getParentTrElement());
        });
    }

    renderSearchResult(searchResult, parent) {
        this.searchResultElement.innerHTML = searchResult.reduce((pre, book) =>
            pre + `<li data-id="${book.id}">${book.title}/${book.author}/${book.volume}권</li>`
            , '');

        parent.appendChild(this.searchResultElement);

        if (!this.isVisible()) {
            this.visible();
        }
    }

    setSelectedLiElement(key) {
        if (!this.isVisible()) return;

        if (this.isSelected()) {
            this.removeLiBackground();

            if (key === 'ArrowDown') {
                this.selectedLi = this.selectedLi.nextElementSibling;
            } else if (key === 'ArrowUp') {
                this.selectedLi = this.selectedLi.previousElementSibling;
            }
        }

        if (!this.isSelected()) {
            if (key === 'ArrowDown') {
                this.selectedLi = this.searchResultElement.firstElementChild;
            } else if (key === 'ArrowUp') {
                this.selectedLi = this.searchResultElement.lastElementChild;
            }
        }

        this.addLiBackground();
    }

    reset() {
        this.removeLiBackground();
        this.deselectLi();
    }

    getParentTrElement() {
        return this.searchResultElement.parentElement.parentElement;
    }

    getSelectedLiIndex() {
        return [...this.searchResultElement.children].indexOf(this.selectedLi);
    }

    isVisible() {
        return this.searchResultElement.classList.contains('visible');
    }

    isSelected() {
        return !!this.selectedLi;
    }

    deselectLi() {
        this.selectedLi = null;
    }

    visible() {
        this.searchResultElement.classList.remove('invisible');
        this.searchResultElement.classList.add('visible');
    }

    invisible() {
        this.searchResultElement.classList.remove('visible');
        this.searchResultElement.classList.add('invisible');
    }

    removeLiBackground() {
        if (this.selectedLi) {
            this.selectedLi.classList.remove('bg-selected');
        }
    }

    addLiBackground() {
        this.selectedLi.classList.add('bg-selected');
    }
}