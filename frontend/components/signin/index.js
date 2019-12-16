import React, { Component } from 'react';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import thunk from "redux-thunk";
import { AppRegistry } from 'react-native';
import { ThemeProvider } from 'react-native-elements';
import { createTheme, colors } from "../../assets/themes";
import SignIn from './containers/SignIn';
import signIn from './reducers';
import initI18n from "../../initI18n";

const store = createStore(signIn, applyMiddleware(thunk));

class SignInComponent extends Component {
  constructor(props) {
    super(props);

    initI18n(() => { console.log('loaded'); });
  }

  render() {
    return (
      <Provider store={ store }>
        <ThemeProvider theme={ createTheme(colors.mizuki) }>
          <SignIn/>
        </ThemeProvider>
      </Provider>
    );
  }
}

AppRegistry.registerComponent('SignIn', () => SignInComponent);
