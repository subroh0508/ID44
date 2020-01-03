import React, { useContext } from 'react';
import { StyleSheet, View } from "react-native";
import { Avatar, Input, Icon, Text, ThemeContext } from "react-native-elements";
import { STATUS_VISIBILITY } from '../native/TimelineNativeActions';

const getVisibilityIconType = visibility => {
  switch (visibility) {
    case STATUS_VISIBILITY.PUBLIC:
      return 'globe';
    case STATUS_VISIBILITY.UNLISTED:
      return 'lock-open';
    case STATUS_VISIBILITY.PRIVATE:
      return 'lock';
    default:
      return 'envelope';
  }
};

export const TootOptions = ({ remainTextCount, options }) => {
  const { theme } = useContext(ThemeContext);
  const { visibility, warning } = options;

  const styles = withStyles(theme, warning);

  return (
    <View style={ styles.root }>
      <View style={{ flexDirection: 'row', alignItems: 'center' }}>
        <Icon type='font-awesome'
          name='camera'
          underlayColor='transparent'
          size={ 20 }
          containerStyle={ styles.optionsIcon }/>
        <Icon type='font-awesome'
          name={ getVisibilityIconType(visibility) }
          underlayColor='transparent'
          size={ 20 }
          containerStyle={ styles.optionsIcon }/>
        <Text style={ styles.optionsText }>CW</Text>
      </View>

      <Text style={ styles.counter }>
        { remainTextCount }
      </Text>
    </View>
  )
};

const withStyles = ({ colors }, warning) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
      justifyContent: 'space-between',
      paddingTop: 8,
      paddingStart: 48,
      paddingEnd: 16,
    },
    optionsIcon: {
      marginEnd: 16,
    },
    optionsText: {
      marginEnd: 8,
      fontSize: 12,
      color: warning ? colors.primary : colors.secondary,
    },
    counter: {
      color: colors.secondary,
    },
  })
);
