import React, { memo, useContext } from 'react';
import { ThemeContext } from 'react-native-elements';
import { View, StyleSheet } from 'react-native';
import { Reply, Reblog, Favourite } from './Icons';

export const Actions = memo(({ visibility, repliesCount, reblogged, reblogCount,  favourited, favouriteCount }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme, reblogged, favourited);

  return (
    <View style={ styles.root }>
      <Reply count={ repliesCount }/>
      <Reblog
        reblogged={ reblogged }
        count={ reblogCount }
        visibility={ visibility }/>
      <Favourite
        favourited={ favourited }
        count={ favouriteCount }/>
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
