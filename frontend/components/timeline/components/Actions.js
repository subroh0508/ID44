import React, { memo, useContext } from 'react';
import { Icon, Text, ThemeContext } from 'react-native-elements';
import { View, StyleSheet } from 'react-native';
import { Reply, ReblogAction, Favourite } from './Icons';

export const Actions = memo(({ visibility, repliesCount, reblogCount, favouriteCount }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <Reply count={ repliesCount || '' }/>
      <ReblogAction
        count={ reblogCount }
        visibility={ visibility }/>
      <Favourite count={ favouriteCount }/>
    </View>
  )
});

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
      paddingTop: 4,
    },
  })
);
