import React, { useContext, useState } from 'react';
import { StyleSheet, View } from "react-native";
import { Icon, Text, Overlay, ListItem, ThemeContext } from "react-native-elements";
import { STATUS_VISIBILITY } from '../native/TimelineNativeActions';
import i18next from 'i18next';

const getVisibilityIconName = visibility => {
  switch (visibility) {
    case STATUS_VISIBILITY.PUBLIC:
      return 'globe';
    case STATUS_VISIBILITY.UNLISTED:
      return 'unlock';
    case STATUS_VISIBILITY.PRIVATE:
      return 'lock';
    default:
      return 'envelope';
  }
};

const visibilities = [STATUS_VISIBILITY.PUBLIC, STATUS_VISIBILITY.UNLISTED, STATUS_VISIBILITY.PRIVATE, STATUS_VISIBILITY.DIRECT];

const VisibilitySelector = ({
  isVisibleSelector, visibility,
  setTootVisibility, close
}) => {
  const { theme } = useContext(ThemeContext);
  const styles = withSelectorStyles(theme);

  return (
    <Overlay isVisible={ isVisibleSelector } height='auto'
      onBackdropPress={ close }>
      <View>
        {
          visibilities.map((v, i) => (
            <ListItem key={ i }
              leftIcon={{
                type: 'font-awesome',
                name: getVisibilityIconName(v),
                iconStyle: { color: v === visibility ? theme.colors.disabled : theme.colors.primary },
                containerStyle: styles.icon,
              }}
              title={
                <View>
                  <Text style={ v === visibility ? styles.selectedText : styles.text }>
                    { i18next.t(`privacy.${v.toLowerCase()}.short`) }
                  </Text>
                  <Text style={ v === visibility ? styles.selectedText : styles.text }>
                    { i18next.t(`privacy.${v.toLowerCase()}.long`) }
                  </Text>
                </View>
              }
              onPress={ () => {
                setTootVisibility(v);
                close()
              }}
            />
          ))
        }
      </View>
    </Overlay>
  );
};

export const TootOptions = ({
  remainTextCount, options,
  onClickOpenCamera = () => {}, setTootVisibility, toggleContentWarning,
}) => {
  const [isVisibleSelector, toggleSelector] = useState(false);
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
          name={ getVisibilityIconName(visibility) }
          underlayColor='transparent'
          size={ 20 }
          containerStyle={ styles.optionsIcon }
          onPress={ () => toggleSelector( true) }/>
        <Text
          style={ styles.optionsText }
          onPress={ toggleContentWarning.bind(null, !contentWarning) }>CW</Text>
      </View>
      <Text style={ styles.counter }>
        { remainTextCount }
      </Text>
      <VisibilitySelector
        isVisibleSelector={ isVisibleSelector }
        visibility={ visibility }
        setTootVisibility={ setTootVisibility }
        close={ () => toggleSelector(false) }/>
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
      width: 20,
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

const withSelectorStyles = ({ colors }) => (
  StyleSheet.create({
    icon: {
      width: 24,
    },
    text: {
      color: colors.text,
    },
    selectedText: {
      color: colors.disabled,
    },
  })
);
