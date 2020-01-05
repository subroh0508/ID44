import React, { memo, useContext } from 'react';
import { View, StyleSheet } from 'react-native';
import { Text, Icon, ThemeContext } from 'react-native-elements';

export const TooterName = memo(({ tooter }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <Text
      style={ styles.displayName }
      numberOfLines={ 1 }
      ellipsizeMode='tail'>
      { tooter.displayName || tooter.username }
      <Text
        style={ styles.username }
        numberOfLines={ 1 }
        ellipsizeMode='tail'>
        { `  @${tooter.username}` }
      </Text>
    </Text>
  );
}, (prev, next) => prev.tooter.id === next.tooter.id);

const withStyles = ({ colors }) => (
  StyleSheet.create({
    displayName: {
      flexShrink: 1,
      fontWeight: 'bold',
    },
    username: {
      fontWeight: 'normal',
      color: colors.secondary,
    },
  })
);
