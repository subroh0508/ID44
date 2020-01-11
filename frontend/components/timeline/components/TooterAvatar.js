import React, { memo, useContext } from 'react';
import { View, StyleSheet } from 'react-native';
import { Icon, ThemeContext } from 'react-native-elements';
import FastImage from 'react-native-fast-image';

export const TooterAvatar = memo(
  ({ tooter, visibility }) => {
    const { theme } = useContext(ThemeContext);

    const styles = withStyle(theme);

    return (
      <View style={ styles.root }>
        <FastImage
          style={ styles.avatar }
          source={{ uri: tooter.avatar }}/>
        {
          visibility.toString() === 'PRIVATE' ? (
            <Icon
              type='font-awesome'
              name='lock'
              containerStyle={ styles.overlay }/>
          ) : null
        }
      </View>
    );
  },
  (prev, next) => prev.tooter.id === next.tooter.id,
);

const withStyle = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
    },
    avatar: {
      width: 40,
      height: 40,
      borderRadius: 50,
    },
    overlay: {
      position: 'absolute',
      width: 16,
      height: 16,
      top: 28,
      left: 28,
      right: 0,
    },
  })
);
