import React, { useEffect } from 'react';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import thunk from "redux-thunk";
import logger from 'redux-logger';
import { AppRegistry } from 'react-native';
import { ThemeProvider } from 'react-native-elements';
import { createTheme, colors } from "../../assets/themes";
import { SignIn } from './containers/SignIn';
import signIn from './reducers';
import initI18n from "../../initI18n";

const store = createStore(signIn, applyMiddleware(thunk, logger));

AppRegistry.registerComponent('SignIn', () => () => {
  useEffect(() => {
    initI18n(() => { console.log('loaded'); });
  }, []);

  return (
    <Provider store={ store }>
      <ThemeProvider theme={ createTheme(colors.mizuki) }>
        <SignIn/>
      </ThemeProvider>
    </Provider>
  );
});
