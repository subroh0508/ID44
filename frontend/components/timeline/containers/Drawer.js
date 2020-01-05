import React, { useEffect } from 'react';
import { useSelector, useDispatch } from "react-redux";
import { createSelector } from 'reselect';
import { fetchOwnAccounts, onClickSwitchAccount, openAuthentication } from "../actions/ownAccounts";
import { OwnAccountsDrawer } from "../components/OwnAccountsDrawer";
import { clearStreams, unsubscribeAll } from "../actions/timelines";

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
  //const timelines = useSelector(state => state.timelines);
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
        //dispatch(unsubscribeAll(timelines));
        dispatch(openAuthentication());
        navigation.closeDrawer();
      }}
    />
  );
};
