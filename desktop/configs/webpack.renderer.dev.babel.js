import path from 'path';
import webpack from 'webpack';
import merge from 'webpack-merge';
import { spawn } from 'child_process';

import baseConfig from './webpack.renderer.base.babel';

const dist = path.resolve(__dirname, '../build');

const port = process.env.PORT || 1212;
const publicPath = `http://localhost:${port}/build`;

export default merge.smart(baseConfig, {
  devtool: 'inline-source-map',
  mode: 'development',
  target: 'electron-renderer',
  entry: path.resolve(__dirname, '../renderer/index.js'),
  output: {
    publicPath: publicPath,
    filename: 'renderer.dev.js'
  },
  node: {
    __dirname: false,
    __filename: false,
  },
  devServer: {
    port,
    publicPath,
    compress: true,
    stats: 'errors-only',
    inline: true,
    lazy: false,
    hot: true,
    headers: { 'Access-Control-Allow-Origin': '*' },
    contentBase: dist,
    watchOptions: {
      aggregateTimeout: 300,
      ignored: /node_modules/,
      poll: 100,
    },
    historyApiFallback: {
      verbose: true,
      disableDotRule: false,
    },
    before() {
      if (process.env.START_HOT) {
        console.log('Starting Main Process...');
        spawn('yarn', ['start:main:dev'], {
          shell: true,
          env: process.env,
          stdio: 'inherit',
        })
          .on('close', code => process.exit(code))
          .on('error', spawnError => console.error(spawnError));
      }
    },
  },
});
