import React, { useContext } from 'react';
import { StyleSheet, View } from 'react-native';
import { HomeHeader } from '../components/HomeHeader';
import { HomeTab } from './HomeTab';
import { BottomBar } from '../components/BottomBar';
import { ThemeContext } from 'react-native-elements';

export const HomeScreen = ({ account, onClickHeaderAvatar }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <HomeHeader
        account={ account }
        onClickHeaderAvatar={ onClickHeaderAvatar }/>
      <HomeTab screenProps={{ account }}/>
      <BottomBar account={ account }/>
    </View>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
    },
  })
);

