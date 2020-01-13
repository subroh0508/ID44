import React, { memo, useContext } from 'react';
import { Icon, Text, ThemeContext } from 'react-native-elements';
import { View, StyleSheet } from 'react-native';

export const Reply = ({ count }) => (<ActionIcon name='reply' count={ count }/>);
export const Reblog = ({ count }) => (<ActionIcon name='retweet' count={ count }/>);
export const Favourite = ({ count }) => (<ActionIcon name='star' count={ count }/>);
export const VisibilityIcon = ({ visibility }) => (<ActionIcon name={ getVisibilityIconName(visibility) }/>);
export const ReblogAction = ({ visibility, count }) =>
  (visibility.toString() === 'PUBLIC' || visibility.toString() === 'UNLISTED') ? (
    <Reblog count={ count }/>
  ) : (<VisibilityIcon visibility={ visibility }/>);

const getVisibilityIconName = visibility => {
  switch (visibility.toString()) {
    case 'PUBLIC':
      return 'globe';
    case 'UNLISTED':
      return 'unlock';
    case 'PRIVATE':
      return 'lock';
    default:
      return 'envelope';
  }
};

const ActionIcon = memo(({ name, count = null }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <Icon
        type='font-awesome'
        name={ name }
        size={ 20 }/>
      <CountText count={ count }/>
    </View>
  );
}, (prev, next) => prev.count === next.count);

const CountText = ({ count }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <Text style={ styles.count }>
      { count === 0 ? '' : count }
    </Text>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
    },
    count: {
      minWidth: 48,
      paddingTop: 2,
      paddingStart: 4,
      paddingEnd: 4,
      paddingBottom: 2,
      fontSize: 12,
      color: colors.secondary,
    },
  })
);
