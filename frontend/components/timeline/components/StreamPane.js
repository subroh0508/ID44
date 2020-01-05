import React, { memo, useContext } from 'react';
import { shallowEqual, useSelector } from 'react-redux';
import { createSelector } from 'reselect';
import { FlatList, StyleSheet } from 'react-native';
import { ThemeContext } from 'react-native-elements';
import { Status } from '../containers/Status';

const getStreams  = (streamKey) => createSelector(
  [
    state => state.timelines.focusTab,
    state => state.timelines.streams[streamKey] || [],
  ],
  (focusTab, streams) => ({
    focusTab,
    streams: focusTab === streamKey ? streams : [],
  }),
);

export const StreamPane = ({ streamKey }) => {
  const { streams } = useSelector(
    getStreams(streamKey),
    (next, prev) => next.focusTab !== streamKey || shallowEqual(next, prev),
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

const renderItem = ({ item }) => (<Status status={ item }/>);

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
      width: '100%',
      backgroundColor: colors.background,
    },
  })
);
