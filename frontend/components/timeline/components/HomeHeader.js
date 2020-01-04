import React, { useContext } from "react";
import { StyleSheet } from "react-native";
import { Header, Avatar, Text, ThemeContext } from "react-native-elements";

export const HomeHeader = ({ account, onClickHeaderAvatar }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <Header
      placement='left'
      leftComponent={
        <Avatar rounded
          containerStyle={ styles.avatar }
          source={{ uri: account && account.avatar }}
          onPress={ onClickHeaderAvatar }/>
      }
      centerComponent={
        <Text style={ styles.title }>
          { account && account.hostName }
        </Text>
      }/>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    avatar: {
      marginStart: 8,
      marginEnd: 8,
    },
    title: {
      fontSize: 20,
      fontWeight: 'bold',
    }
  })
);
