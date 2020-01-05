import React, { memo, useContext } from 'react';
import { StyleSheet } from 'react-native';
import { ThemeContext } from 'react-native-elements';
import FastImage from 'react-native-fast-image';

export const TooterAvatar = memo(
  ({ tooter }) => {
    const { theme } = useContext(ThemeContext);

    const styles = withStyle(theme);

    return (
      <FastImage
        style={ styles.root }
        source={{ uri: tooter.avatar }}/>
    );
  },
  (prev, next) => prev.tooter.id === next.tooter.id,
);

const withStyle = ({ colors }) => (
  StyleSheet.create({
    root: {
      width: 36,
      height: 36,
      borderRadius: 50,
    },
  })
);
