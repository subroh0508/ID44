import React, {useEffect} from 'react';
import { AppRegistry } from 'react-native';
import { ThemeProvider } from "react-native-elements";
import { MainContainer } from "./containers/MainContainer";
import { createTheme, colors } from "../../assets/themes";
import { applyMiddleware, createStore } from "redux";
import reducers from "./reducers";
import thunk from "redux-thunk";
import logger from 'redux-logger';
import { Provider } from "react-redux";
import initI18n from "../../initI18n";

const store = createStore(reducers, applyMiddleware(thunk/*, logger*/));

AppRegistry.registerComponent('Timeline', () => () => {
  useEffect(() => {
    initI18n(() => {
      console.log('loaded');
    });
  }, []);

  return (
    <Provider store={store}>
      <ThemeProvider theme={createTheme(colors.mizuki)}>
        <MainContainer/>
      </ThemeProvider>
    </Provider>
  );
});
