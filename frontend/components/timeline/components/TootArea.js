import React, { useContext } from 'react';
import { StyleSheet, View } from "react-native";
import { Avatar, Input, Icon, Text, ThemeContext } from "react-native-elements";

export const TootArea = ({
  rootStyle,
  account, tootText, onProgress, openOptions,
  onClickAvatar, onChangeText, onClickSubmit, onToggleOptions,
}) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ rootStyle }>
      <View style={{ flexDirection: 'row' }}>
        <Avatar rounded
          containerStyle={ styles.avatar }
          source={{ uri: account && account.avatar }}
          onPress={ onClickAvatar }/>
        <Input
          placeholder='今なにしてる？'
          multiline={ true }
          disabled={ onProgress }
          containerStyle={ styles.tootAreaContainer }
          inputContainerStyle={ styles.tootAreaInputContainer }
          rightIcon={{
            type: 'font-awesome',
            name: 'paper-plane',
            underlayColor: 'transparent',
            onPress: onClickSubmit,
            disabled: tootText.length === 0 || onProgress,
          }}
          value={ tootText }
          onChangeText={ onChangeText }/>
        <Icon type='font-awesome'
          name={ openOptions ? 'chevron-up' : 'chevron-down' }
          underlayColor='transparent'
          containerStyle={ styles.dropdown }
          onPress={ onToggleOptions.bind(null, !openOptions) }/>
      </View>
      <Text style={ styles.counter }>
        { 500 - tootText.length }
      </Text>
    </View>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    avatar: {
      marginTop: 16,
      marginStart: 2,
      marginRight: 2,
    },
    tootAreaContainer: {
      flexShrink: 1,
    },
    tootAreaInputContainer: {
      borderColor: colors.secondary,
      borderWidth: 1,
      borderRadius: 4,
      marginTop: 8,
      paddingStart: 8,
      paddingEnd: 8,
    },
    dropdown: {
      marginTop: 24,
      width: 24,
    },
    counter: {
      textAlign: 'right',
      marginEnd: 36,
      color: colors.secondary,
    },
  })
);
