import React, { useEffect } from 'react';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import thunk from "redux-thunk";
import logger from 'redux-logger';
import { AppRegistry } from 'react-native';
import { ThemeProvider } from 'react-native-elements';
import { createTheme, colors } from "../assets/themes";
import initI18n from "../initI18n";

export default (name, component, reducers, nativeModule) => {
  const store = createStore(
    reducers,
    applyMiddleware(
      nativeModule ? thunk.withExtraArgument(nativeModule) : thunk,
      logger,
    ),
  );

  AppRegistry.registerComponent(name, () => () => {
    useEffect(() => {
      initI18n(() => console.log('loaded'));
    }, []);

    return (
      <Provider store={ store }>
        <ThemeProvider theme={ createTheme(colors.mizuki) }>
          { React.createElement(component) }
        </ThemeProvider>
      </Provider>
    );
  });
};
