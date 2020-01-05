import React, { memo, useContext } from 'react';
import { useSelector, shallowEqual } from 'react-redux';
import { createSelector } from 'reselect';
import {
  ScrollView,
  View,
  StyleSheet,
} from 'react-native';
import {
  ListItem,
  Text,
  Icon,
  ThemeContext, Avatar,
} from 'react-native-elements';
import HTMLView from 'react-native-htmlview';
import { DiffTime } from './DiffTime';
import { TooterName } from './TooterName';
import { Actions } from './Actions';
import { datetimes } from 'ID44-shared';
import i18next from 'i18next';

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
    <ScrollView style={ styles.root }>
      {
        streams.map(status => (
          <ListItem key={ status.id }
            leftAvatar={ <TooterAvatar tooter={ status.tooter }/>}
            title={ <Status status={ status }/> }
            subtitle={
              <Actions
                reblogCount={ status.reblogCount }
                favouriteCount={ status.favouriteCount }/>
            }
            bottomDivider>
          </ListItem>
        ))
      }
    </ScrollView>
  );
};

const TooterAvatar = memo(
  ({ tooter }) => (<Avatar rounded source={{ uri: tooter.avatar }}/>),
  (prev, next) => prev.tooter.id === next.tooter.id,
);

const Content = memo(({ status }) => {
  const { theme } = useContext(ThemeContext);

  return (
    <HTMLView
      value={ status.content }
      stylesheet={{ p: { color: theme.colors.text } }}/>
  );
}, (prev, next) => prev.status.id === next.status.id);

const Status = ({ status }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.status }>
      <View style={ styles.tooter }>
        <TooterName tooter={ status.tooter }/>
        <DiffTime time={ status.createdAt }/>
      </View>
      <Content status={ status }/>
    </View>
  )
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
      width: '100%',
      backgroundColor: colors.background,
    },
    status: {
      paddingBottom: 12,
    },
    tooter: {
      flex: 1,
      flexDirection: 'row',
      justifyContent: 'space-between',
      paddingBottom: 4,
    },
    displayName: {
      flexShrink: 1,
      fontWeight: 'bold',
    },
    username: {
      fontWeight: 'normal',
      color: colors.secondary,
    },
    actions: {
      flexDirection: 'row',
      paddingTop: 4,
    },
    counter: {
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
