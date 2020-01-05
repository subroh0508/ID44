import React, { useContext } from 'react';
import { StyleSheet } from 'react-native';
import { ThemeContext } from 'react-native-elements';
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

const createTabNavigator = (styles, account, streams) => createAppContainer(
  createMaterialTopTabNavigator(
    streams.reduce((acc, stream) => {
      acc[streamKey(account, stream)] = {
        screen: () => (
          <StreamPane streamKey={ streamKey(account, stream) }/>
        ),
        navigationOptions: {
          title: title(stream),
          tabBarOptions: {
            style: styles.root,
            labelStyle: styles.label,
            indicatorStyle: styles.indicator,
          },
        },
      };

      return acc;
    }, {})
  )
);

export const HomeTab = ({ account, streams, onNavigationStateChange }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  const TabNavigator = createTabNavigator(styles, account, streams);

  return (<TabNavigator onNavigationStateChange={ onNavigationStateChange }/>)
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      backgroundColor: colors.background,
      borderBottomWidth: StyleSheet.hairlineWidth,
      borderColor: colors.secondary,
    },
    label: {
      color: colors.text,
    },
    indicator: {
      backgroundColor: colors.primary,
    },
  })
);
