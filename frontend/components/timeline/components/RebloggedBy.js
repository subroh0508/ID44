import React, { memo, useContext } from 'react';
import { Icon, Text, ThemeContext } from 'react-native-elements';
import { View, StyleSheet } from 'react-native';
import i18next from 'i18next';

export const RebloggedBy = memo(({ account }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  if (account === null) {
    return null;
  }

  return (
    <View style={ styles.root }>
      <Icon
        type='font-awesome'
        name='retweet'
        size={ 20 }
        containerStyle={ styles.iconContainer }/>
      <Text style={ styles.text }>
        { i18next.t('status.rebloggedBy', { name: account.displayName || account.username }) }
      </Text>
    </View>
  )
}, (prev, next) => prev.account && prev.account.id === next.account && next.account.id);

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
      alignItems: 'center',
      paddingStart: 44,
      paddingTop: 8,
      paddingEnd: 8,
    },
    iconContainer: {
      paddingEnd: 8,
    },
    text: {
      color: colors.secondary,
    },
  })
);
