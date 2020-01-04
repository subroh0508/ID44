import React, { useContext } from 'react';
import { StyleSheet, View } from 'react-native';
import { Avatar, ThemeContext } from 'react-native-elements';
import { createAppContainer } from 'react-navigation';
import { createMaterialTopTabNavigator } from 'react-navigation-tabs';
import { StreamPane } from "./StreamPane";
import { streamKey } from "../actions/timelines";

const createTabNavigator = (account, streams) => createAppContainer(
  createMaterialTopTabNavigator(
    streams.reduce((acc, stream) => {
      acc[streamKey(account, stream)] = {
        screen: () => (
          <StreamPane streamKey={streamKey(account, stream)}/>
        ),
        navigationOptions: {
          title: 'ローカル',
        },
      };

      return acc;
    }, {})
  )
);

export const HomeTab = ({ account, streams, onNavigationStateChange }) => {
  const TabNavigator = createTabNavigator(account, streams);

  return (<TabNavigator onNavigationStateChange={ onNavigationStateChange }/>)
};
