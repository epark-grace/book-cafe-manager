module.exports = {
    purge: [],
    theme: {
        backgroundColor: theme => ({
            'selected': '#BFDBFE'
        }),
        extend: {
            gridTemplateRows: {
                'booklist': '1fr minmax(0, max-content) 1fr'
            }
        }
    },
    variants: {},
    plugins: [],
}
