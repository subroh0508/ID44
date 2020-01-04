import React, { useContext } from 'react';
import { StyleSheet, View } from 'react-native';
import { Avatar, ThemeContext } from 'react-native-elements';
import { createAppContainer } from 'react-navigation';
import { createMaterialTopTabNavigator } from 'react-navigation-tabs';
import { StreamPane } from "./StreamPane";
import { streamKey } from "../actions/timelines";
import { STREAM } from '../native/TimelineNativeActions';

export const HomeTab = createAppContainer(
  createMaterialTopTabNavigator({
    Local: {
      screen: ({ screenProps: { account } }) => (
        <StreamPane streamKey={ streamKey(account, STREAM.LOCAL) }/>
      ),
      navigationOptions: {
        title: 'ローカル',
      },
    },
    Home: {
      screen: ({ screenProps: { account } }) => (
        <StreamPane streamKey={ streamKey(account, STREAM.HOME) }/>
      ),
      navigationOptions: {
        title: 'ホーム',
      },
    },
    Global: {
      screen: ({ screenProps: { account } }) => (
        <StreamPane streamKey={ streamKey(account, STREAM.GLOBAL) }/>
      ),
      navigationOptions: {
        title: '連合',
      },
    },
  })
);
