import React, { useEffect } from 'react';
import { useSelector, useDispatch } from "react-redux";
import { createAppContainer } from "react-navigation";
import { createDrawerNavigator } from 'react-navigation-drawer';
import { Timeline } from "../containers/Timeline";
import { OwnAccountsDrawer } from "../components/OwnAccountsDrawer";
import { fetchOwnAccounts } from "../actions/ownAccounts";

const DrawerNavigator = createAppContainer(
  createDrawerNavigator({
    Home: {
      screen: Timeline,
    },
  }, {
    contentComponent: OwnAccountsDrawer,
  }),
);

export const TimelineFrame = () => {
  const ownAccounts = useSelector(state => state.ownAccounts);
  const dispatch = useDispatch();

  useEffect(() => {
    if (ownAccounts.length === 0) {
      dispatch(fetchOwnAccounts());
    }
  });

  return (<DrawerNavigator screenProps={ { ownAccounts } }/>)
};
