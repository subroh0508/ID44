import React, { useContext } from 'react';
import { Image, StyleSheet } from 'react-native';
import { ThemeContext } from "react-native-elements";

import wallpaper from '../../../assets/mizuki.native.png';

export const BackgroundImage = () => {
  const theme = useContext(ThemeContext);
  const styles = withStyles(theme);

  return (
    <Image
      style={ styles.root }
      source={ wallpaper }
    />
  )
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
      width: '100%',
      position: 'absolute',
      resizeMode: 'cover',
    },
  })
);

