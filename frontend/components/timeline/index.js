import * as React from 'react';
import { AppRegistry } from 'react-native';
import { DefaultTheme, Provider as PaperProvider } from 'react-native-paper';
import TimelineComponent from "./TimelineComponent";

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

const Timeline = () => {
  return (
      <PaperProvider theme={ DefaultTheme }>
        <TimelineComponent/>
      </PaperProvider>
  );
};

AppRegistry.registerComponent('Timeline', () => Timeline);
