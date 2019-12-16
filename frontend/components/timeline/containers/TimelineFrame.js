import React, { useEffect } from 'react';
import { useSelector, useDispatch } from "react-redux";
import { createAppContainer } from "react-navigation";
import { createDrawerNavigator } from 'react-navigation-drawer';
import TimelineComponent from "../components/Timeline";
import { OwnAccountsDrawer } from "../components/OwnAccountsDrawer";
import { fetchOwnAccounts } from "../actions/ownAccounts";

const DrawerNavigator = createAppContainer(
  createDrawerNavigator({
    Home: {
      screen: TimelineComponent,
    },
  }, {
    contentComponent: OwnAccountsDrawer,
  }),
);

export const TimelineFrame = () => {
  const ownAccounts = useSelector(state => state.ownAccounts);
  const dispatch = useDispatch();

  useEffect(() => { dispatch(fetchOwnAccounts()); });

  return (<DrawerNavigator screenProps={ { ownAccounts } }/>)
};
