const path = require("path");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

class MiniCssExtractPluginCleanup {
    constructor(deleteWhere) {
        this.shouldDelete = new RegExp(deleteWhere)
    }
    apply(compiler) {
        compiler.hooks.emit.tapAsync("MiniCssExtractPluginCleanup", (compilation, callback) => {
            Object.keys(compilation.assets).forEach((asset) => {
                if (this.shouldDelete.test(asset)) {
                    delete compilation.assets[asset]
                }
            });
            callback();
        })
    }
}

module.exports = {
    mode: "development",
    entry: {
        "book-list": path.resolve(__dirname, "src/main/frontend/entry/book-list.js"),
        common: path.resolve(__dirname, "src/main/frontend/entry/common.js")
    },
    output: {
        filename: "[name].bundle.js",
        path: path.resolve(__dirname, "src/main/webapp/dist/js")
    },
    optimization: {
        splitChunks: {
            cacheGroups: {
                vendors: {
                    test: /[\\/]node_modules[\\/]/,
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
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
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
            },
            {
                test: /\.handlebars$/,
                exclude: /node_modules/,
                use: "handlebars-loader"
            }
        ]
    },
    plugins: [
        new MiniCssExtractPlugin({filename: "../css/[name].css"}),
        new MiniCssExtractPluginCleanup(/tailwind\.bundle\.js$/)
    ]
};

