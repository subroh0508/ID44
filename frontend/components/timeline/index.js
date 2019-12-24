import React from 'react';
import { AppRegistry } from 'react-native';
import { ThemeProvider } from "react-native-elements";
import { MainContainer } from "./containers/MainContainer";
import { createTheme, colors } from "../../assets/themes";
import { applyMiddleware, createStore } from "redux";
import reducers from "./reducers";
import thunk from "redux-thunk";
import { Provider } from "react-redux";

const store = createStore(reducers, applyMiddleware(thunk));

AppRegistry.registerComponent('Timeline', () => () => (
  <Provider store={ store }>
    <ThemeProvider theme={ createTheme(colors.mizuki) }>
      <MainContainer/>
    </ThemeProvider>
  </Provider>
));
