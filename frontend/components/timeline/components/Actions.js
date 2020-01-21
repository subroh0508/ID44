import React, { memo, useContext } from 'react';
import { ThemeContext } from 'react-native-elements';
import { View, StyleSheet } from 'react-native';
import { Reply, Reblog, Favourite } from './Icons';

export const Actions = memo(({
  visibility, repliesCount,
  reblogged, reblogCount, toggleReblog,
  favourited, favouriteCount, toggleFavourite,
}) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <Reply count={ repliesCount }/>
      <Reblog
        reblogged={ reblogged }
        count={ reblogCount }
        visibility={ visibility }
        onPress={ toggleReblog }/>
      <Favourite
        favourited={ favourited }
        count={ favouriteCount }
        onPress={ toggleFavourite }/>
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
