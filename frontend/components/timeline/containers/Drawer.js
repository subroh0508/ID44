import React, { useEffect } from 'react';
import { useSelector, useDispatch } from "react-redux";
import { createSelector } from 'reselect';
import { fetchOwnAccounts, onClickSwitchAccount, openAuthentication } from "../actions/ownAccounts";
import { OwnAccountsDrawer } from "../components/OwnAccountsDrawer";
import { clearStreams, requestUnsubscribeAll, unsubscribe } from "../actions/timelines";

const selectOwnAccounts = createSelector(
  state => state.ownAccounts,
  ({ accounts, selectedAccount }) => (
    selectedAccount ? [
      selectedAccount,
      ...accounts.filter(account => account.id !== selectedAccount.id),
    ] : []
  ),
);

export const Drawer = ({ navigation }) => {
  const active = useSelector(state => state.timelines.active);
  const subscription = useSelector(state => state.timelines.subscription);
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
        dispatch(requestUnsubscribeAll(accounts[0], active));
        dispatch(onClickSwitchAccount(account));
        navigation.closeDrawer();
      }}
      onClickedAddAccount={ () => {
        dispatch(requestUnsubscribeAll(accounts[0], active));
        dispatch(unsubscribe(subscription));
        dispatch(openAuthentication());
        navigation.closeDrawer();
      }}
    />
  );
};
