import React, { Component } from 'react';
import {
  StyleSheet,
  View,
} from 'react-native';
import {
  Icon, ListItem, Button,
} from 'react-native-elements';
import { createAppContainer } from "react-navigation";
import { createDrawerNavigator } from 'react-navigation-drawer';
import TimelineComponent from "./TimelineComponent";
import { openAuthentication, switchAccount } from "./native/TimelineModule";

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
              bottomDivider
              onPress={ e => switchAccount(account.hostName, account.id) }>
            </ListItem>
          ))
        }
        <Button title='アカウント追加'
          type='outline'
          onPress={ e => openAuthentication() }/>
      </View>
    )
  }),
);
