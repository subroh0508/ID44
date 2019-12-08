import React, { Component } from 'react';
import {
  View,
  Image,
  Text,
  StyleSheet,
  NativeEventEmitter,
  NativeModules,
} from 'react-native';
import {
  TextInput,
  Button,
  withTheme
} from 'react-native-paper';

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
