import React, { useContext } from "react";
import { Header, Avatar, ThemeContext } from "react-native-elements";
import { StyleSheet } from "react-native";

export const HomeHeader = ({ account }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <Header
      placement='left'
      //statusBarProps={{ translucent: true }}
      leftComponent={
        <Avatar rounded
          //containerStyle={ styles.avatar }
          source={{ uri: account && account.avatar }}/>
      }
      centerComponent={{ text: account && account.hostName }}/>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    avatar: {
      marginTop: 16,
      marginStart: 2,
      marginRight: 2,
    },
  })
);
