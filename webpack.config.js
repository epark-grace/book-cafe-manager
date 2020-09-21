const path = require("path");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
    mode: "development",
    entry: {
        "book-list": path.resolve(__dirname, "src/main/frontend/entry/book-list.js")
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
                test: /m?\.js$/,
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
    ]
};

