import React, { memo, useContext } from 'react';
import { View, StyleSheet } from 'react-native';
import { ThemeContext } from 'react-native-elements';
import HTMLView from 'react-native-htmlview';
import { DiffTime } from './DiffTime';
import { TooterName } from './TooterName';

export const StatusContent = ({ status }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <View style={ styles.tooter }>
        <TooterName tooter={ status.tooter }/>
        <DiffTime time={ status.createdAt }/>
      </View>
      <Content status={ status }/>
    </View>
  )
};

const Content = memo(({ status }) => {
  const { theme } = useContext(ThemeContext);

  return (
    <HTMLView
      value={ status.content }
      stylesheet={{ p: { color: theme.colors.text } }}/>
  );
}, (prev, next) => prev.status.id === next.status.id);

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      paddingBottom: 12,
    },
    tooter: {
      flex: 1,
      flexDirection: 'row',
      justifyContent: 'space-between',
      paddingBottom: 4,
    },
  })
);

