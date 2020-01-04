import React from "react";
import { useSelector } from "react-redux";
import { View } from "react-native";
import { Timeline } from "./Timeline";
import { BottomBar } from "../components/BottomBar";

export const HomeScreen = ({ navigation }) => {
  const selectedAccount = useSelector(state => state.ownAccounts.selectedAccount);

  return (
    <View>
      <Timeline
        account={ selectedAccount }/>
      <BottomBar
        account={ selectedAccount }
        onClickAvatar={ () => navigation.openDrawer() }/>
    </View>
  );
};
