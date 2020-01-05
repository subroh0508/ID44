import React, { useContext } from 'react';
import { StyleSheet, View } from 'react-native';
import { Avatar, ThemeContext } from 'react-native-elements';
import { createAppContainer } from 'react-navigation';
import { createMaterialTopTabNavigator } from 'react-navigation-tabs';
import { StreamPane } from "./StreamPane";
import { streamKey } from "../actions/timelines";
import { STREAM } from '../native/TimelineNativeActions';
import i18next from "i18next";

const title = (stream) => {
  switch (stream) {
    case STREAM.LOCAL:
      return i18next.t("tabsBar.localTimeline");
    case STREAM.GLOBAL:
      return i18next.t("tabsBar.federatedTimeline");
    case STREAM.HOME:
      return i18next.t("tabsBar.home");
    default:
      return 'その他';
  }
};

const createTabNavigator = (account, streams) => createAppContainer(
  createMaterialTopTabNavigator(
    streams.reduce((acc, stream) => {
      acc[streamKey(account, stream)] = {
        screen: () => (
          <StreamPane streamKey={ streamKey(account, stream) }/>
        ),
        navigationOptions: {
          title: title(stream),
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
