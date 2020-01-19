const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const src  = path.resolve(__dirname, 'src');
const assets = path.resolve(__dirname, 'public');
const dist = path.resolve(__dirname, 'build');

module.exports = {
  mode: 'development',
  entry: `${__dirname}/index.js`,
  output: {
    path: dist,
    filename: 'bundle.js'
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'babel-loader'
      }
    ]
  },
  resolve: {
    extensions: ['.js', '.jsx'],
    alias: {
      'react-native$': 'react-native-web'
    }
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: `${assets}/index.html`,
      filename: 'index.html',
    }),
  ],
};
