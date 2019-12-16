import React, { PureComponent } from 'react';
import { AppRegistry } from 'react-native';
import { ThemeProvider } from "react-native-elements";
import { TimelineFrame } from "./TimelineFrame";
import { createTheme, colors } from "../../assets/themes";
import { fetchOwnAccounts } from "./native/TimelineModule";

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
      <ThemeProvider theme={ createTheme(colors.mizuki) }>
        <TimelineFrame screenProps={ { ownAccounts } }/>
      </ThemeProvider>
    )
  }
}

AppRegistry.registerComponent('Timeline', () => Timeline);
