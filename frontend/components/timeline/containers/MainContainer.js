import React from 'react';
import { useSelector } from 'react-redux';
import { createAppContainer } from 'react-navigation';
import { createDrawerNavigator } from 'react-navigation-drawer';
import { Drawer } from './Drawer';
import { HomeScreen } from '../components/HomeScreen';

const Home = ({ navigation }) => {
  const selectedAccount = useSelector(state => state.ownAccounts.selectedAccount);

  return (
    <HomeScreen
      account={ selectedAccount }
      onClickHeaderAvatar={ () => navigation.openDrawer() }/>
  );
};

export const MainContainer = createAppContainer(
  createDrawerNavigator({
    Home: {
      screen: Home,
    },
  }, {
    contentComponent: Drawer,
  }),
);
