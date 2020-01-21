import React, { memo, useContext } from 'react';
import { Icon, Text, ThemeContext } from 'react-native-elements';
import { View, StyleSheet } from 'react-native';

export const Reply = ({ count }) => (<ActionIconWithCount name='reply' count={ count }/>);
export const Reblog = memo(({ visibility, count, reblogged, onPress }) => {
  const { theme: { colors } } = useContext(ThemeContext);

  return visibility.toString() === 'PUBLIC' ?
    (<ActionIconWithCount
      name='retweet'
      count={ count }
      iconStyle={{ color: reblogged ? colors.reblogged : colors.secondary }}
      onPress={ onPress }/>) :
    (<ActionIconWithCount
      name={ getVisibilityIconName(visibility) }/>)
});
export const Favourite = memo(({ count, favourited, onPress }) => {
  const { theme: { colors } } = useContext(ThemeContext);

  return (
    <ActionIconWithCount
      name='star'
      count={ count }
      iconStyle={{ color: favourited ? colors.favourited : colors.secondary }}
      onPress={ onPress }/>
  );
});

export const VisibilityIcon = ({
  visibility,
  onPress = () => {},
  iconStyle = {}, containerStyle = {},
}) =>
  (<ActionIcon
    name={ getVisibilityIconName(visibility) }
    onPress={ onPress }
    iconStyle={ iconStyle }
    containerStyle={ containerStyle }/>);
export const Camera = ({
  onPress = () => {},
  iconStyle = {}, containerStyle = {},
}) =>
  (<ActionIcon
    name='camera'
    onPress={ onPress }
    iconStyle={ iconStyle }
    containerStyle={ containerStyle }/>);

const getVisibilityIconName = visibility => {
  switch (visibility.toString()) {
    case 'PUBLIC':
      return 'globe';
    case 'UNLISTED':
      return 'unlock';
    case 'PRIVATE':
      return 'lock';
    default:
      return 'envelope';
  }
};

const ActionIcon = memo(({
  name,
  onPress = () => {},
  iconStyle = {}, containerStyle = {},
}) => (
  <Icon
    type='font-awesome'
    name={ name }
    iconStyle={ iconStyle }
    containerStyle={ containerStyle }
    size={ 20 }
    underlayColor='transparent'
    onPress={ onPress }/>
), (prev, next) => prev.name === next.name);

const ActionIconWithCount = memo(({
  name, count = null,
  onPress = () => {},
  iconStyle = {}, containerStyle = {},
}) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      <Icon
        type='font-awesome'
        name={ name }
        iconStyle={ iconStyle }
        containerStyle={ containerStyle }
        size={ 20 }
        underlayColor='transparent'
        onPress={ onPress }/>
      <CountText count={ count }/>
    </View>
  );
});

const CountText = ({ count }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <Text style={ styles.count }>
      { count === 0 ? '' : count }
    </Text>
  );
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
    },
    count: {
      minWidth: 48,
      paddingTop: 2,
      paddingStart: 4,
      paddingEnd: 4,
      paddingBottom: 2,
      fontSize: 12,
      color: colors.secondary,
    },
  })
);
