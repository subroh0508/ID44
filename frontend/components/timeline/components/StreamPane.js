import React, { useContext } from 'react';
import { useSelector } from "react-redux";
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

const Status = ({ theme, styles, status }) => (
  <View style={ styles.status }>
    <View style={ styles.tooter }>
      <Text
        style={ styles.displayName }
        numberOfLines={ 1 }
      >
        { status.tooter.displayName || status.tooter.username }
      </Text>
      <Text
        style={ styles.username }
        numberOfLines={ 1 }
        ellipsizeMode='tail'
      >
        { `@${status.tooter.username}` }
      </Text>
    </View>
    <HTMLView value={ status.content }
      stylesheet={{ p: { color: theme.colors.primary } }}/>
  </View>
);

const Actions = ({ theme, styles, status }) => (
  <View style={ styles.actions }>
    <Icon
      type='material'
      name='reply'
      color='#dcdcdc'
      size={ 20 }/>
    {
      <Text style={ styles.counter }>
        0
      </Text>
    }
    <Icon
      type='material'
      name='repeat'
      color='#dcdcdc'
      size={ 20 }/>
    {
      <Text style={ styles.counter }>
        { status.reblogCount === 0 ? '' : status.reblogCount }
      </Text>
    }
    <Icon
      type='material'
      name='star'
      color='#dcdcdc'
      size={ 20 }/>
    {
      <Text style={ styles.counter }>
        { status.favouriteCount === 0 ? '' : status.favouriteCount }
      </Text>
    }
  </View>
);

export const StreamPane = ({ streamKey }) => {
  const { theme } = useContext(ThemeContext);
  const streams = useSelector(state => state.timelines.streams[streamKey] || []);

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
      flexDirection: 'row',
      paddingBottom: 4,
    },
    displayName: {
      fontWeight: 'bold',
    },
    username: {
      paddingStart: 4,
      color: colors.placeholder,
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
      color: '#dcdcdc',
    },
  })
);
