import React, { PureComponent } from 'react';
import { AppRegistry } from 'react-native';
import { DefaultTheme, Provider as PaperProvider } from 'react-native-paper';
import TimelineComponent from "./TimelineComponent";
import { TimelineFrame } from "./TimelineFrame";
import { fetchOwnAccounts } from "./native/TimelineModule";

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

class Timeline extends PureComponent {
  constructor(props) {
    super(props);
    this.state = { ownAccounts: [] };
  }

  async componentDidMount() {
    this.setState({ ownAccounts: await fetchOwnAccounts() });
  }

  render() {
    const { ownAccounts } = this.state;

    return (
      <PaperProvider theme={ DefaultTheme }>
        <TimelineFrame screenProps={ { ownAccounts } }/>
      </PaperProvider>
    )
  }
}

AppRegistry.registerComponent('Timeline', () => Timeline);
