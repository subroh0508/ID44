import React, { useContext, useState } from 'react';
import { StyleSheet, View } from "react-native";
import { Icon, Text, Overlay, ListItem, ThemeContext } from "react-native-elements";
import { VisibilityIcon, Camera } from './Icons';
import { visibilities } from 'ID44-model-status';
import i18next from 'i18next';

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
              leftIcon={
                <VisibilityIcon
                  visibility={ v }
                  iconStyle={{
                    color: v === visibility ?
                      theme.colors.disabled :
                      theme.colors.primary
                  }}
                  containerStyle={ styles.icon }/>
              }
              title={
                <View>
                  <Text style={ v === visibility ? styles.selectedText : styles.text }>
                    { i18next.t(`privacy.${v.toString().toLowerCase()}.short`) }
                  </Text>
                  <Text style={ v === visibility ? styles.selectedText : styles.text }>
                    { i18next.t(`privacy.${v.toString().toLowerCase()}.long`) }
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
      <View style={ styles.options }>
        <Camera
          containerStyle={ styles.optionsIcon }
          onPress={ onClickOpenCamera }/>
        <VisibilityIcon
          visibility={ visibility }
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
      paddingStart: 16,
      paddingEnd: 16,
    },
    options: {
      flexDirection: 'row',
      alignItems: 'center',
    },
    optionsIcon: {
      width: 24,
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
