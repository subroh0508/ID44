import path from 'path';

const dist = path.resolve(__dirname, '../build');

export default {
  devtool: 'source-map',
  mode: 'production',
  target: 'electron-main',
  entry: path.resolve(__dirname, '../main/index.js'),
  output: {
    path: dist,
    filename: 'main.prod.js',
  },
  node: {
    __dirname: false,
    __filename: false,
  },
};
