import React, { Component } from 'react';
import { connect } from 'react-redux';
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

import wallpaper from '../../../assets/mizuki.png';
import SignInModule from '../native/SignInModule';
import { onChangedHostName } from '../actions';

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

class SignIn extends Component {
  render() {
    const { hostName } = this.props;

    console.log("hostName:", hostName);
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
            onChangeText={ this.props.onChangedHostName.bind(null) }
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

const mapStateToProps = (state, _ownProps) => ({
  hostName: state.hostName,
});

const mapDispatchToProps = {
  onChangedHostName,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(withTheme(SignIn));
