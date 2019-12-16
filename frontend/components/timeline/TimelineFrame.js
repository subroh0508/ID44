import React, { Component } from 'react';
import {
  StyleSheet,
  View,
} from 'react-native';
import { createAppContainer } from "react-navigation";
import { createDrawerNavigator } from 'react-navigation-drawer';
import TimelineComponent from "./TimelineComponent";
import { OwnAccountsDrawer } from "./OwnAccountsDrawer";

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
    contentComponent: OwnAccountsDrawer,
  }),
);
