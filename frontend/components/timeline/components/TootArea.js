import React, { useContext } from 'react';
import { StyleSheet, View } from "react-native";
import { Avatar, Input, ThemeContext } from "react-native-elements";

export const TootArea = ({
  account, tootText, onProgress,
  onClickAvatar, onChangeText, onClickSubmit,
}) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  const disabledSubmit = tootText.length === 0 || onProgress;

  return (
    <View style={ styles.root }>
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
          disabled: disabledSubmit,
          iconStyle: disabledSubmit ? styles.disabledTootSubmitIcon : styles.tootSubmitIcon,
        }}
        value={ tootText }
        onChangeText={ onChangeText }/>
    </View>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
    },
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
    tootSubmitIcon: {
      color: colors.primary,
    },
    disabledTootSubmitIcon: {
      color: colors.placeholder,
    },
    tootOptionsRoot: {
      flexDirection: 'row',
      justifyContent: 'space-between',
      paddingTop: 8,
      paddingStart: 48,
      paddingEnd: 16,
    },
    tootOptionsIcon: {
      marginEnd: 16,
    },
    tootOptionsText: {
      marginEnd: 8,
      fontSize: 12,
      color: colors.secondary,
    },
    counter: {
      color: colors.secondary,
    },
  })
);
