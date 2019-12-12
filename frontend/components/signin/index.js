import React, { Component } from 'react';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import thunk from "redux-thunk";
import { AppRegistry } from 'react-native';
import { DefaultTheme, Provider as PaperProvider } from 'react-native-paper';
import SignIn from './containers/SignIn';
import signIn from './reducers';
import initI18n from "../../initI18n";
import i18next from "i18next";

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

const store = createStore(signIn, applyMiddleware(thunk));

class SignInComponent extends Component {
  constructor(props) {
    super(props);

    initI18n(() => { console.log('loaded'); });
  }

  render() {
    return (
      <Provider store={ store }>
        <PaperProvider theme={ theme }>
          <SignIn/>
        </PaperProvider>
      </Provider>
    );
  }
}

AppRegistry.registerComponent('SignIn', () => SignInComponent);
