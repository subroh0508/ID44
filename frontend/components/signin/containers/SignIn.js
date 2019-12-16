import React, { Component } from 'react';
import { connect } from 'react-redux';
import {
  View,
  Image,
  StyleSheet,
} from 'react-native';
import {
  Text,
  Input,
  Button,
  withTheme,
} from 'react-native-elements';

import wallpaper from '../../../assets/mizuki.png';
import { onChangedHostName, onClickedAuthorize } from '../actions';

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
      paddingTop: 8,
      paddingBottom: 16,
    },
    input: {
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
          <Input
            containerStyle={ styles.input }
            placeholder='Host Name'
            onChangeText={ this.props.onChangedHostName.bind(null) }
          />
          <Button
            containerStyle={ styles.button }
            type='clear'
            title='AUTHORIZE'
            onPress={ this.props.onClickedAuthorize.bind(null, hostName) }
          />
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
  onClickedAuthorize,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(withTheme(SignIn));
