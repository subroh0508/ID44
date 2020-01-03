import React, { useContext, useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import {StyleSheet, View} from "react-native";
import {Avatar, Input, Text, ThemeContext} from "react-native-elements";

export const TootArea = ({ rootStyle, account, onClickAvatar }) => {
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
          containerStyle={ styles.tootAreaContainer }
          inputContainerStyle={ styles.tootAreaInputContainer }
          rightIcon={{
            type: 'font-awesome',
            name: 'pencil',
            color: theme.colors.secondary,
            onPress: () => console.log('clicked'),
          }}/>
      </View>
      <Text style={ styles.counter }>
        140
      </Text>
    </View>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    avatar: {
      marginTop: 8,
      marginStart: 2,
      marginRight: 2,
    },
    tootAreaContainer: {
      width: '90%',
    },
    tootAreaInputContainer: {
      borderColor: colors.secondary,
      borderWidth: 1,
      borderRadius: 4,
      paddingStart: 8,
      paddingEnd: 8,
    },
    counter: {
      textAlign: 'right',
      marginEnd: 12,
      color: colors.secondary,
    },
  })
);
