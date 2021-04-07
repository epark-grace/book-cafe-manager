const path = require("path");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
    mode: "development",
    entry: {
        "book-list": "./src/main/frontend/entry/book-list.js",
        "shelf-editor": "./src/main/frontend/entry/shelf-editor.js",
        "book-creation-form": "./src/main/frontend/entry/book-creation-form.js"
    },
    output: {
        filename: "[name].bundle.js",
        path: path.resolve(__dirname, "src/main/resources/static/js")
    },
    optimization: {
        splitChunks: {
            cacheGroups: {
                defaultVendors: {
                    name: "vendors",
                    chunks: "all",
                    enforce: true
                },
                tailwind: {
                    test: /tailwind\.css$/,
                    name: "tailwind",
                    chunks: "all",
                    enforce: true
                }
            }
        }
    },
    plugins: [
        new MiniCssExtractPlugin({filename: "../css/[name].css"})
    ],
    module: {
        rules: [
            {
                test: /m?\.js$/,
                exclude: /[\\/]node_modules[\\/]/,
                use: "babel-loader"
            },
            {
                test: /\.css$/,
                use: [
                     MiniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        options: {
                            importLoaders: 1
                        }
                    },
                    "postcss-loader"
                ]
            }
        ]
    }
};

