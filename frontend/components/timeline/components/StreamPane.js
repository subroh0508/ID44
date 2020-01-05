import React, { memo, useContext } from 'react';
import { useSelector } from 'react-redux';
import { createSelector } from 'reselect';
import { StyleSheet, FlatList } from 'react-native';
import { ListItem, ThemeContext } from 'react-native-elements';
import FastImage from 'react-native-fast-image';
import { Status } from './Status';
import { Actions } from './Actions';

const getStreams  = (streamKey) => createSelector(
  [
    state => state.timelines.focusTab,
    state => state.timelines.streams[streamKey] || [],
  ],
  (focusTab, streams) => ({ focusTab, streams }),
);

export const StreamPane = ({ streamKey }) => {
  const { streams } = useSelector(
    getStreams(streamKey),
    (next, prev) =>
      next.focusTab !== streamKey || next.streams.length === prev.streams.length,
  );

  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <FlatList
      style={ styles.root }
      data={ streams }
      keyExtractor={ status => status.id }
      renderItem={ renderItem }/>
  );
};

const renderItem = ({ item }) => (
  <ListItem
    leftAvatar={ <TooterAvatar tooter={ item.tooter }/>}
    title={ <Status status={ item }/> }
    subtitle={
      <Actions
        reblogCount={ item.reblogCount }
        favouriteCount={ item.favouriteCount }/>
    }
    bottomDivider>
  </ListItem>
);

const TooterAvatar = memo(
  ({ tooter }) => (
    <FastImage
      style={{ width: 36, height: 36, borderRadius: 50 }}
      source={{ uri: tooter.avatar }}/>
  ),
  (prev, next) => prev.tooter.id === next.tooter.id,
);

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
      width: '100%',
      backgroundColor: colors.background,
    },
  })
);
