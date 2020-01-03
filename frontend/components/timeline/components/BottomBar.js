import React, { useContext } from "react";
import { View, StyleSheet, Dimensions } from "react-native";
import { ThemeContext } from "react-native-elements";
import { TootEditContainer } from '../containers/TootEditContainer';

export const BottomBar = ({ account, openDrawer }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <TootEditContainer account={ account }
        onClickAvatar={ openDrawer }/>
    </View>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      position: 'absolute',
      width: Dimensions.get('window').width - 16,
      margin: 8,
      padding: 8,
      bottom: 0,
      borderRadius: 4,
      backgroundColor: '#26324f',
      opacity: 0.95,
    },
  })
);
