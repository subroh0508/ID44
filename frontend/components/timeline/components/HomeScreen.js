import React, { useContext } from 'react';
import { StyleSheet, View } from 'react-native';
import { HomeHeader } from './HomeHeader';
import { Timelines } from '../containers/Timelines';
import { BottomBar } from './BottomBar';
import { ThemeContext } from 'react-native-elements';

export const HomeScreen = ({ account, onClickHeaderAvatar }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <HomeHeader
        account={ account }
        onClickHeaderAvatar={ onClickHeaderAvatar }/>
      { account ? (<Timelines account={ account }/>) : null }
      <BottomBar/>
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

