import React, { useContext } from "react";
import { StyleSheet } from "react-native";
import { ThemeContext } from "react-native-elements";
import { TootEditContainer } from '../containers/TootEditContainer';

export const BottomBar = (props) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <TootEditContainer {...{ rootStyle: styles.root, ...props }}/>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      position: 'absolute',
      margin: 8,
      padding: 8,
      bottom: 0,
      borderRadius: 4,
      backgroundColor: '#26324f',
      opacity: 0.95,
    },
  })
);
