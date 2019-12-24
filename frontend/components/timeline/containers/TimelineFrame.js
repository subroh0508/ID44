import React, { useEffect } from 'react';
import { useSelector, useDispatch } from "react-redux";
import { createAppContainer } from "react-navigation";
import { createDrawerNavigator } from 'react-navigation-drawer';
import { Timeline } from "../containers/Timeline";
import { OwnAccountsDrawer } from "../components/OwnAccountsDrawer";
import { fetchOwnAccounts, onClickSwitchAccount, openAuthentication } from "../actions/ownAccounts";
import { clearStatus, unsubscribeAll } from "../actions/timelines";

const Home = () => {
  const selectedAccount = useSelector(state => state.ownAccounts.selectedAccount);

  return (<Timeline account={ selectedAccount }/>)
};

const Drawer = () => {
  const subscriptions = useSelector(state => state.timelines.subscriptions);
  const accounts = useSelector(state => state.ownAccounts.accounts);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchOwnAccounts());
  }, []);

  return (
    <OwnAccountsDrawer
      accounts={ accounts }
      onClickedSwitchAccount={ (account) => {
        dispatch(clearStatus());
        dispatch(onClickSwitchAccount(account));
      }}
      onClickedAddAccount={ () => {
        dispatch(unsubscribeAll(subscriptions));
        dispatch(openAuthentication());
      }}
    />
  );
};

export const TimelineFrame = createAppContainer(
  createDrawerNavigator({
    Home: {
      screen: Home,
    },
  }, {
    contentComponent: Drawer,
  }),
);
