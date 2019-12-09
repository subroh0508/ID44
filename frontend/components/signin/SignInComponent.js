import React, { Component } from 'react';
import {
  View,
  Image,
  Text,
  StyleSheet,
} from 'react-native';
import {
  TextInput,
  Button,
  withTheme
} from 'react-native-paper';

import wallpaper from '../../assets/mizuki.png';
import SignInModule from './native/SignInModule';

const withStyles = ({ colors }) => (
  StyleSheet.create({
    backgroundImage: {
      height: '100%',
      width: '100%',
      position: 'absolute',
      resizeMode: 'cover',
    },
    root: {
      height: '100%',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
    },
    container: {
      height: 200,
      width: '90%',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      backgroundColor: colors.background,
      borderWidth: 1,
      borderRadius: 4,
      borderColor: '#ffffff6a',
      shadowColor: '#000',
      shadowOffset: {
        width: 0,
        height: 2,
      },
      shadowOpacity: 0.25,
      shadowRadius: 3.84,
      elevation: 5,
    },
    title: {
      color: colors.primary,
      paddingTop: 8,
      paddingBottom: 16,
    },
    textInput: {
      width: '90%',
    },
    button: {
      width: '45%',
      paddingTop: 8,
      paddingBottom: 8,
      margin: 16,
    },
  })
);

class SignInComponent extends Component {
  render() {
    const styles = withStyles(this.props.theme);

    return (
      <View style={ styles.root }>
        <Image
          style={ styles.backgroundImage }
          source={ wallpaper }
        />
        <View style={ styles.container }>
          <Text style={ styles.title }>
            Device ID: 44
          </Text>
          <TextInput
            style={ styles.textInput }
            label='Host Name'
            mode='outlined'
            onChangeText={ text => SignInModule.onChangedHostName(text) }
          />
          <Button
            style={ styles.button }
            mode='outlined'
            onPress={ () => SignInModule.onClickAuthorize() }
          >Authorize</Button>
        </View>
      </View>
    )
  }
}

export default withTheme(SignInComponent);
