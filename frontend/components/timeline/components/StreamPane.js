import React, { useContext } from 'react';
import { useSelector, shallowEqual } from "react-redux";
import { createSelector } from "reselect";
import {
  ScrollView,
  View,
  StyleSheet,
} from 'react-native';
import {
  ListItem,
  Text,
  Icon,
  ThemeContext,
} from 'react-native-elements';
import HTMLView from "react-native-htmlview";
import { datetimes } from 'ID44-shared';
import i18next from "i18next";

const Status = ({ theme, styles, status }) => (
  <View style={ styles.status }>
    <View style={ styles.tooter }>
      <Text
        style={ styles.displayName }
        numberOfLines={ 1 }
        ellipsizeMode='tail'>
        { status.tooter.displayName || status.tooter.username }
        <Text
          style={ styles.username }
          numberOfLines={ 1 }
          ellipsizeMode='tail'>
          { `  @${status.tooter.username}` }
        </Text>
      </Text>
      <Text
        style={ styles.diffTime }
        numberOfLines={ 1 }>
        { datetimes.toDiffTime(status.createdAt, (key, option) => i18next.t(key, option)) }
      </Text>
    </View>
    <HTMLView value={ status.content }
      stylesheet={{ p: { color: theme.colors.text } }}/>
  </View>
);

const Actions = ({ theme, styles, status }) => (
  <View style={ styles.actions }>
    <Icon
      type='font-awesome'
      name='reply'
      size={ 20 }/>
    {
      <Text style={ styles.counter }>
        0
      </Text>
    }
    <Icon
      type='font-awesome'
      name='retweet'
      size={ 20 }/>
    {
      <Text style={ styles.counter }>
        { status.reblogCount === 0 ? '' : status.reblogCount }
      </Text>
    }
    <Icon
      type='font-awesome'
      name='star'
      size={ 20 }/>
    {
      <Text style={ styles.counter }>
        { status.favouriteCount === 0 ? '' : status.favouriteCount }
      </Text>
    }
  </View>
);

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
    ({ focusTab }, _) => focusTab !== streamKey,
  );

  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <ScrollView style={ styles.root }>
      {
        streams.map((status, i) => (
          <ListItem key={ i }
            leftAvatar={{ source: { uri: status.tooter.avatar } }}
            title={
              <Status {...{ theme, styles, status }}/>
            }
            subtitle={
              <Actions {...{ theme, styles, status }}/>
            }
            bottomDivider>
          </ListItem>
        ))
      }
    </ScrollView>
  );
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
    diffTime: {
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
