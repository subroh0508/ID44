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

export const TootOptions = ({
  remainTextCount, options,
  onClickOpenCamera = () => {}, setTootVisibility, toggleContentWarning,
}) => {
  const { theme } = useContext(ThemeContext);
  const { visibility, contentWarning } = options;

  const styles = withStyles(theme, contentWarning);

  return (
    <View style={ styles.root }>
      <View style={{ flexDirection: 'row', alignItems: 'center' }}>
        <Icon type='font-awesome'
          name='camera'
          underlayColor='transparent'
          size={ 20 }
          containerStyle={ styles.optionsIcon }
          onPress={ onClickOpenCamera }/>
        <Icon type='font-awesome'
          name={ getVisibilityIconType(visibility) }
          underlayColor='transparent'
          size={ 20 }
          containerStyle={ styles.optionsIcon }/>
        <Text
          style={ styles.optionsText }
          onPress={ toggleContentWarning.bind(null, !contentWarning) }>CW</Text>
      </View>
      <Text style={ styles.counter }>
        { remainTextCount }
      </Text>
    </View>
  )
};

const withStyles = ({ colors }, contentWarning) => (
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
      paddingTop: 2,
      paddingBottom: 2,
      paddingEnd: 2,
      color: contentWarning ? colors.primary : colors.secondary,
    },
    counter: {
      color: colors.secondary,
    },
  })
);
