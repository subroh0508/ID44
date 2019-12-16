import React, { Component } from 'react';
import {
  View,
  StyleSheet,
  NativeEventEmitter,
  NativeModules,
} from 'react-native';
import {
  Text,
  withTheme,
} from 'react-native-elements';

import TimelineModule, {
  EVENT_APPEND_STATUS,
  STREAM,
  subscribe,
  unsubscribe,
} from './native/TimelineModule';

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      backgroundColor: colors.background,
    },
  })
);

class TimelineComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { content: 'init' };
  }

  async componentDidMount() {
    new NativeEventEmitter(TimelineModule).addListener(
      EVENT_APPEND_STATUS,
      (status) => {
        this.setState({ content: status.content })
      },
    );

    try {
      await subscribe(STREAM.LOCAL);
    } catch (e) {
      console.log('error', e);
    }
  }

  render() {
    const styles = withStyles(this.props.theme);

    return (
      <View style={ styles.root }>
        <Text>{ this.state.content }</Text>
      </View>
    )
  }
}

export default withTheme(TimelineComponent);
