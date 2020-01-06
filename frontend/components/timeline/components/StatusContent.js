import React, { memo, useContext, useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { Divider, Text, ThemeContext } from 'react-native-elements';
import HTMLView from 'react-native-htmlview';
import { DiffTime } from './DiffTime';
import { TooterName } from './TooterName';
import i18next from 'i18next';

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

const Content = memo(({ status }) => {
  const [show, toggleContent] = useState(false);
  const { theme } = useContext(ThemeContext);

  const styles = withContentStyles(theme);

  if (!status.isWarningContent) {
    return (
      <HTMLView
        value={ status.content }
        stylesheet={{ p: { color: theme.colors.text } }}/>
    );
  }

  return (
    <View>
      <View style={ styles.root }>
        <Text style={ styles.warningText }>
          { status.warningText }
        </Text>
        <Text style={ styles.toggle } onPress={ () => toggleContent(!show) }>
          { i18next.t(`status.${show ? 'showLess' : 'showMore'}`) }
        </Text>
      </View>
      {
        show ? (
          <View>
            <Divider style={ styles.divider }/>
            <HTMLView
              value={ status.content }
              stylesheet={{ p: { color: theme.colors.text } }}/>
          </View>
        ) : null
      }
    </View>
  );
}, (prev, next) => prev.status.id === next.status.id);

const withContentStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
      flex: 1,
      justifyContent: 'space-between',
      alignItems: 'center',
    },
    warningText: {
      flexShrink: 1,
    },
    toggle: {
      width: 60,
      height: 24,
      color: colors.secondary,
      borderColor: colors.placeholder,
      borderWidth: StyleSheet.hairlineWidth,
      borderRadius: 2,
      paddingTop: 5,
      paddingBottom: 5,
      marginStart: 8,
      textAlign: 'center',
      fontSize: 10,
      fontWeight: 'bold',
    },
    divider: {
      backgroundColor: colors.placeholder,
      marginTop: 8,
      marginBottom: 8,
    },
  })
);

