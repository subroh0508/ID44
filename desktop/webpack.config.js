const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const appDir = path.resolve(__dirname, '../');
const dist = path.resolve(__dirname, 'build');

module.exports = {
  mode: 'development',
  entry: path.resolve(appDir, 'web/index.js'),
  devtool: 'inline-source-map',
  output: {
    path: dist,
    filename: 'bundle.js'
  },
  module: {
    rules: [
      {
        test: /\.js?$/,
        include: [
          path.resolve(appDir, 'desktop/index.js'),
          path.resolve(appDir, 'frontend'),
          /node_modules\/react-native/,
          /node_modules\/react-navigation/,
        ],
        use: {
          loader: 'babel-loader',
          options: {
            cacheDirectory: true,
            presets: [
              '@babel/preset-env',
              '@babel/preset-react',
              'module:metro-react-native-babel-preset',
            ],
            plugins: ['react-native-web'],
          },
        }
      },
      {
        test: /\.(gif|jpe?g|png|svg)$/,
        use: {
          loader: 'url-loader',
          options: {
            name: '[name].[ext]'
          }
        }
      },
      {
        test: /\.ttf$/,
        loader: 'url-loader',
        include: path.resolve(appDir, 'node_modules/react-native-vector-icons'),
      },
    ]
  },
  resolve: {
    extensions: ['.web.js', '.js', '.jsx'],
    alias: {
      'react-native$': 'react-native-web'
    }
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: 'index.html',
      filename: `${dist}/index.html`,
    }),
  ],
};
