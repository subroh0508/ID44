import React, { useContext } from 'react';
import { useSelector } from "react-redux";
import {
  ScrollView,
  StyleSheet,
} from 'react-native';
import {
  ListItem,
  ThemeContext,
} from 'react-native-elements';
import HTMLView from "react-native-htmlview";

export const StreamPane = ({ stream }) => {
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
