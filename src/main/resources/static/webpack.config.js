var packageJSON = require('./package.json');
var path = require('path');
var webpack = require('webpack');
module.exports = {
    devtool: 'source-map',
    entry: {
        index: './index.js',
    },
    output: {
        path: path.join(__dirname, 'generated'),
        filename: "[name].js"
    },
    resolve: { extensions: ['.js', '.jsx'] },
    plugins: [
        new webpack.LoaderOptionsPlugin({
            debug: true
        }),
        new webpack.DefinePlugin({
            "process.env": {
                NODE_ENV: JSON.stringify("development")
            }
        })
    ],
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                use:
                    [
                        'style-loader',
                        'css-loader',
                    ]
            }
        ]
    },
    devServer: {
        noInfo: false,
        quiet: false,
        lazy: false,
        watchOptions: {
            poll: true
        }
    }
}