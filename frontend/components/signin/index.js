import * as React from 'react';
import { AppRegistry } from 'react-native';
import { DefaultTheme, Provider as PaperProvider } from 'react-native-paper';
import SignInComponent from './SignInComponent';

// mizuki
const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: '#00ffff',
    text: '#00ffff',
    disabled: '#afeeee',
    placeholder: '#afeeee',
    background: '#000'
  },
};

/*
//tsumugi
const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: '#ff00ff',
    text: '#ff00ff',
    disabled: '#ee82ee',
    placeholder: '#ee82ee',
    background: '#000'
  },
};
*/

/*
//shiho
const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: '#ffff00',
    text: '#ffff00',
    disabled: '#fffacd',
    placeholder: '#fffacd',
    background: '#000',
  },
};
*/

const SignIn = () => {
  return (
    <PaperProvider theme={ theme }>
      <SignInComponent/>
    </PaperProvider>
  );
};

AppRegistry.registerComponent('SignIn', () => SignIn);
