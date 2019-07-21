import React, { Component } from 'react';
import { TextInput } from 'react-native-paper';

export default class AuthComponent extends Component {
  state = {
    text: '',
  };

  render() {
    return (
      <TextInput
        label='Host Name'
        mode='outlined'
        value={ this.state.text }
        onChangeText={ text => this.setState({ text }) }
      />
    )
  }
}
