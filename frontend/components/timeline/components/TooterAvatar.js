import React, { memo, useContext } from 'react';
import { View, StyleSheet } from 'react-native';
import { Icon, ThemeContext } from 'react-native-elements';
import FastImage from 'react-native-fast-image';
import { VisibilityIcon } from './Icons';

export const TooterAvatar = memo(({ tooter, visibility, rebloggedBy }) => {
    const { theme } = useContext(ThemeContext);

    const styles = withStyle(theme);

    return (
      <View style={ styles.root }>
        <FastImage
          style={ styles.avatar }
          source={{ uri: tooter.avatar }}/>
        <Visibility
          visibility={ visibility }
          rebloggedBy={ rebloggedBy }/>
        <RebloggedByAvatar rebloggedBy={ rebloggedBy }/>
      </View>
    );
  },
  (prev, next) => prev.tooter.id === next.tooter.id,
);

const Visibility = ({ visibility, rebloggedBy }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyle(theme);

  if (rebloggedBy !== null || visibility.toString() === 'PUBLIC' || visibility.toString() === 'UNLISTED') {
    return null;
  }

  return (
    <VisibilityIcon
      visibility={ visibility }
      containerStyle={ styles.overlay }/>
  );
};

const RebloggedByAvatar = ({ rebloggedBy }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyle(theme);

  if (rebloggedBy === null) {
    return null;
  }

  return (
    <FastImage
      style={ styles.overlayAvatar }
      source={{ uri: rebloggedBy.avatar }}/>
  );
};

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
    overlayAvatar: {
      position: 'absolute',
      width: 24,
      height: 24,
      borderRadius: 50,
      top: 20,
      left: 20,
      right: 0,
    },
  })
);
