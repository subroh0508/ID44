import React, { useContext } from "react";
import { View, StyleSheet, Dimensions } from "react-native";
import { Avatar, ThemeContext } from "react-native-elements";
import { TootEditor } from '../containers/TootEditor';

export const BottomBar = ({ account, onClickAvatar }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <Avatar rounded
        containerStyle={ styles.avatar }
        source={{ uri: account && account.avatar }}
        onPress={ onClickAvatar }/>
      <TootEditor rootStyle={ styles.tootEditor }/>
    </View>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      position: 'absolute',
      flexDirection: 'row',
      width: Dimensions.get('window').width - 16,
      margin: 8,
      padding: 8,
      bottom: 0,
      borderRadius: 4,
      backgroundColor: '#26324f',
      opacity: 0.95,
    },
    avatar: {
      marginTop: 16,
      marginStart: 2,
      marginRight: 2,
    },
    tootEditor: {
      width: '100%',
      flexShrink: 1,
    },
  })
);
