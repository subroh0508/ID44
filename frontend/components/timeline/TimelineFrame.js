import React, { Component } from 'react';
import {
  Button,
  StyleSheet,
  View,
} from 'react-native';
import {
  Icon, ListItem,
} from 'react-native-elements';
import { createAppContainer } from "react-navigation";
import { createDrawerNavigator } from 'react-navigation-drawer';
import TimelineComponent from "./TimelineComponent";

const styles = StyleSheet.create({
  icon: {
    width: 24,
    height: 24,
  },
});

export const TimelineFrame = createAppContainer(
  createDrawerNavigator({
    Home: {
      screen: TimelineComponent,
    },
  }, {
    contentComponent: ({ screenProps }) => (
      <View>
        {
          screenProps.ownAccounts.map((account, i) => (
            <ListItem key={ i }
              leftAvatar={ { source: { uri: account.avatar } } }
              title={ account.displayName }
              subtitle={ account.screen }
              bottomDivider>
            </ListItem>
          ))
        }
      </View>
    )
  }),
);
