import React, { useEffect } from 'react';
import { useSelector, useDispatch } from "react-redux";
import { createAppContainer } from "react-navigation";
import { createDrawerNavigator } from 'react-navigation-drawer';
import { Timeline } from "../containers/Timeline";
import { OwnAccountsDrawer } from "../components/OwnAccountsDrawer";
import { fetchOwnAccounts, onClickSwitchAccount, openAuthentication } from "../actions/ownAccounts";
import { clearStreams, unsubscribeAll } from "../actions/timelines";

const Home = () => {
  const selectedAccount = useSelector(state => state.ownAccounts.selectedAccount);

  return (<Timeline account={ selectedAccount }/>)
};

const Drawer = () => {
  const timelines = useSelector(state => state.timelines);
  const accounts = useSelector(state => state.ownAccounts.accounts);
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
      }}
      onClickedAddAccount={ () => {
        dispatch(unsubscribeAll(timelines));
        dispatch(openAuthentication());
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
