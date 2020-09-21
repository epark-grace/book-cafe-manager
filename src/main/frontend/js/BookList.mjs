export default class BookList {
    constructor(element) {
        this.element = element;
        this.element.addEventListener('click', this.clickEventHandler.bind(this));
        this.element.addEventListener('mouseover', this.hoverEventHandler);
        this.element.addEventListener('mouseout', this.hoverEventHandler);
    }

    update() {

    };

    delete() {

    };

    submit() {

    };

    cancel() {

    };

    clickEventHandler(event) {
        const button = event.target.closest('button');
        if (!button) return;

        const feature = button.dataset.feature;
        this[feature]();
    }

    hoverEventHandler(event) {
        const tr = event.target.closest('tr');
        if (!tr) return;

        const td = tr.lastElementChild;
        if (td.classList.contains('invisible')) {
            td.classList.replace('invisible', 'visible');
        } else {
            td.classList.replace('visible', 'invisible');
        }
    }
}