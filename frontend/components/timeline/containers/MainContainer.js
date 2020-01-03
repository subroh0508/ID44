import React, { useEffect } from 'react';
import { useSelector, useDispatch } from "react-redux";
import { createSelector } from 'reselect';
import { View } from 'react-native';
import { createAppContainer } from "react-navigation";
import { createDrawerNavigator } from 'react-navigation-drawer';
import { Timeline } from "../containers/Timeline";
import { BottomBar } from "../components/BottomBar";
import { OwnAccountsDrawer } from "../components/OwnAccountsDrawer";
import { fetchOwnAccounts, onClickSwitchAccount, openAuthentication } from "../actions/ownAccounts";
import { clearStreams, unsubscribeAll } from "../actions/timelines";

const Home = ({ navigation }) => {
  const selectedAccount = useSelector(state => state.ownAccounts.selectedAccount);

  return (
    <View style={{ flex: 1 }}>
      <Timeline style={{ flex: 1 }}
        account={ selectedAccount }/>
      <BottomBar style={{ flex: 1 }}
        account={ selectedAccount }
        onClickAvatar={ () => navigation.openDrawer() }/>
    </View>
  );
};

const selectOwnAccounts = createSelector(
  state => state.ownAccounts,
  ({ accounts, selectedAccount }) => (
    selectedAccount ? [
      selectedAccount,
      ...accounts.filter(account => account.id !== selectedAccount.id),
    ] : []
  ),
);

const Drawer = ({ navigation }) => {
  const timelines = useSelector(state => state.timelines);
  const accounts = useSelector(selectOwnAccounts);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchOwnAccounts());
  }, []);

  return (
    <OwnAccountsDrawer
      accounts={ accounts }
      onClickedSwitchAccount={ (account) => {
        dispatch(clearStreams());
        dispatch(onClickSwitchAccount(account));
        navigation.closeDrawer();
      }}
      onClickedAddAccount={ () => {
        dispatch(unsubscribeAll(timelines));
        dispatch(openAuthentication());
        navigation.closeDrawer();
      }}
    />
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
