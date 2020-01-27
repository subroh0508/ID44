import path from 'path';

const appDir = path.resolve(__dirname, '../../');

export default {
  module: {
    rules: [
      {
        test: /\.jsx?$/,
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
      'react-native$': 'react-native-web',
    },
  },
};
