import React, { useContext } from 'react';
import { StyleSheet, View } from 'react-native';
import { Avatar, ThemeContext } from 'react-native-elements';
import { createAppContainer } from 'react-navigation';
import { createMaterialTopTabNavigator } from 'react-navigation-tabs';
import { Timeline } from "../containers/Timeline";
import { STREAM } from '../native/TimelineNativeActions';

export const HomeTab = createAppContainer(
  createMaterialTopTabNavigator({
    Local: {
      screen: ({ screenProps: { account } }) => (
        <Timeline account={ account } stream={ STREAM.LOCAL }/>
      ),
      navigationOptions: {
        title: 'ローカル',
      },
    },
    Home: {
      screen: ({ screenProps: { account } }) => (
        <Timeline account={ account } stream={ STREAM.HOME }/>
      ),
      navigationOptions: {
        title: 'ホーム',
      },
    },
    Global: {
      screen: ({ screenProps: { account } }) => (
        <Timeline account={ account } stream={ STREAM.GLOBAL }/>
      ),
      navigationOptions: {
        title: '連合',
      },
    },
  })
);
