import path from 'path';
import webpack from 'webpack';
import merge from 'webpack-merge';
import HtmlWebpackPlugin from 'html-webpack-plugin';

import baseConfig from './webpack.renderer.base.babel';

const appDir = path.resolve(__dirname, '../../');
const dist = path.resolve(__dirname, '../build');

export default merge.smart(baseConfig, {
  devtool: 'inline-source-map',
  mode: 'development',
  target: 'electron-renderer',
  entry: path.resolve(appDir, 'desktop/index.js'),
  output: {
    path: dist,
    filename: 'renderer.dev.js'
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: 'index.html',
      filename: `${dist}/index.html`,
    }),
  ],
});
