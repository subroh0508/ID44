import React, { memo, useContext } from 'react';
import {Icon, Text, ThemeContext} from "react-native-elements";
import {View, StyleSheet, Dimensions} from "react-native";

export const Actions = memo(({ reblogCount, favouriteCount }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <Reply count={ 0 }/>
      <Reblog count={ reblogCount }/>
      <Favourite count={ favouriteCount }/>
    </View>
  )
});

const ActionIcon = memo(({ name, count }) => (
  <View style={{ flexDirection: 'row' }}>
    <Icon
      type='font-awesome'
      name={ name }
      size={ 20 }/>
    <CountText count={ count }/>
  </View>
), (prev, next) => prev.count === next.count);

const CountText = ({ count }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <Text style={ styles.count }>
      { count === 0 ? '' : count }
    </Text>
  );
};

const Reply = ({ count }) => (<ActionIcon name='reply' count={ count }/>);
const Reblog = ({ count }) => (<ActionIcon name='retweet' count={ count }/>);
const Favourite = ({ count }) => (<ActionIcon name='star' count={ count }/>);

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
      paddingTop: 4,
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
