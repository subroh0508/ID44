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
import HTMLView from "react-native-htmlview";

export const Timeline = ({ account }) => {
  const subscriptions = useSelector(state => state.timelines.subscriptions);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(subscribe(STREAM.LOCAL, subscriptions));

    return () => dispatch(unsubscribe(STREAM.LOCAL, subscriptions));
  }, [account]);

  return (<TimelineStream stream={ STREAM.LOCAL }/>)
};

const TimelineStream = ({ stream }) => {
  const { theme } = useContext(ThemeContext);
  const statuses = useSelector(state => state.timelines.statuses[stream] || []);

  const styles = withStyles(theme);

  return (
    <ScrollView style={ styles.root }>
      {
        statuses.map((status, i) => (
          <ListItem key={ i }
            leftAvatar={{ source: { uri: status.tooter.avatar } }}
            title={
              <HTMLView value={ status.content }
                stylesheet={{ p: { color: theme.colors.primary } }}/>
            }
            subtitle={ status.tooter.username }
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
  })
);
