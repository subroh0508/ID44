import * as React from 'react';
import { AppRegistry } from 'react-native';
import { Provider as PaperProvider } from 'react-native-paper';
import AuthComponent from './AuthComponent';

const Auth = () => {
  return (
    <PaperProvider>
      <AuthComponent/>
    </PaperProvider>
  );
};

AppRegistry.registerComponent('Auth', () => Auth);
