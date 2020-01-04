import React from 'react';
import { createAppContainer } from 'react-navigation';
import { createDrawerNavigator } from 'react-navigation-drawer';
import { Drawer } from './Drawer';
import { HomeScreen } from './HomeScreen';

export const MainContainer = createAppContainer(
  createDrawerNavigator({
    Home: {
      screen: HomeScreen,
    },
  }, {
    contentComponent: Drawer,
  }),
);
