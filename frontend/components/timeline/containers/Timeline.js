import React, {useContext, useEffect} from 'react';
import { useDispatch, useSelector } from "react-redux";
import {
  View,
  ScrollView,
  StyleSheet,
} from 'react-native';
import {
  Text,
  ListItem,
  ThemeContext,
} from 'react-native-elements';
import { subscribe, unsubscribe } from "../actions/timelines";
import { STREAM } from '../native/TimelineModule';

export const Timeline = () => {
  const { theme } = useContext(ThemeContext);
  const timelines = useSelector(state => state.timelines);
  const dispatch = useDispatch();

  const styles = withStyles(theme);

  useEffect(() => {
    if (!timelines.subscriptions.hasOwnProperty(STREAM.LOCAL)) {
      dispatch(subscribe(STREAM.LOCAL, null));
    }
  });

  return (
    <ScrollView style={ styles.root }>
      {
        (timelines.statuses[STREAM.LOCAL] || []).map((status, i) => (
          <ListItem key={ i }
            leftAvatar={{ source: { uri: status.tooter.avatar } }}
            title={ status.content.toString() }
            subtitle={ status.tooter.username }
            bottomDivider>
          </ListItem>
        ))
      }
    </ScrollView>
  )
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
      width: '100%',
      backgroundColor: colors.background,
    },
  })
);
