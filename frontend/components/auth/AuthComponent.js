import React, { Component } from 'react';
import { TextInput } from 'react-native-paper';

import AuthenticationContract from './native/AuthenticationContract';

export default class AuthComponent extends Component {
  render() {
    return (
      <TextInput
        label='Host Name'
        mode='outlined'
        value={ AuthenticationContract.hostName() }
        onChangeText={ text => AuthenticationContract.onChangedHostName(text) }
      />
    )
  }
}
