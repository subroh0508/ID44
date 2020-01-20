import React, { useContext } from 'react';
import { View, StyleSheet, Platform } from 'react-native';
import { ThemeContext } from 'react-native-elements';

export const BackgroundImage = () => {
  const theme = useContext(ThemeContext);
  const styles = withStyles(theme);

  const TopLeftHexagonal = () => (
    <View style={ styles.hexagonalTopLeft }>
      <View style={ styles.trapezoidLeft }/>
      <View style={ styles.trapezoidRight }/>
    </View>
  );

  const InnerTopLeftHexagonal = () => (
    <View style={ styles.hexagonalInnerTopLeft }>
      <View style={ styles.innerTrapezoidLeft }/>
      <View style={ styles.innerTrapezoidRight }/>
    </View>
  );

  const BottomLeftHexagonal = () => (
    <View style={ styles.hexagonalBottomLeft }>
      <View style={ styles.trapezoidLeft }/>
      <View style={ styles.trapezoidRight }/>
    </View>
  );

  const InnerBottomLeftHexagonal = () => (
    <View style={ styles.hexagonalInnerBottomLeft }>
      <View style={ styles.innerTrapezoidLeft }/>
      <View style={ styles.innerTrapezoidRight }/>
    </View>
  );

  const TopRightHexagonal = () => (
    <View style={ styles.hexagonalTopRight }>
      <View style={ styles.trapezoidLeft }/>
      <View style={ styles.trapezoidRight }/>
    </View>
  );

  const InnerTopRightHexagonal = () => (
    <View style={ styles.hexagonalInnerTopRight }>
      <View style={ styles.innerTrapezoidLeft }/>
      <View style={ styles.innerTrapezoidRight }/>
    </View>
  );

  const BottomRightHexagonal = () => (
    <View style={ styles.hexagonalBottomRight }>
      <View style={ styles.trapezoidLeft }/>
      <View style={ styles.trapezoidRight }/>
    </View>
  );

  const InnerBottomRightHexagonal = () => (
    <View style={ styles.hexagonalInnerBottomRight }>
      <View style={ styles.innerTrapezoidLeft }/>
      <View style={ styles.innerTrapezoidRight }/>
    </View>
  );

  return (
    <View style={ styles.root }>
      <View style={ styles.inner }>
        <View style={ styles.borderStart }/>
        <View style={ styles.borderCenter }/>
        <View style={ styles.borderEnd }/>

        <TopLeftHexagonal/>
        <InnerTopLeftHexagonal/>
        <BottomLeftHexagonal/>
        <InnerBottomLeftHexagonal/>

        <TopRightHexagonal/>
        <InnerTopRightHexagonal/>
        <BottomRightHexagonal/>
        <InnerBottomRightHexagonal/>

      </View>

    </View>
  )
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
      width: '100%',
      position: 'absolute',
      backgroundColor: '#27324F',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
    },
    inner: {
      height: '100%',
      width: '60%',
      minWidth: 400,
      maxWidth: 720,
      backgroundColor: '#5A78BA',
    },
    borderCenter: {
      position: 'absolute',
      height: '100%',
      width: 24,
      backgroundColor: '#C9C6E6',
      alignSelf: 'center',
    },
    borderStart: {
      position: 'absolute',
      height: '100%',
      width: 24,
      backgroundColor: '#C9C6E6',
      left: -12,
    },
    borderEnd: {
      position: 'absolute',
      height: '100%',
      width: 24,
      backgroundColor: '#C9C6E6',
      right: -12,
    },
    hexagonalTopLeft: {
      flexDirection: 'row',
      position: 'absolute',
      width: 120,
      height: 120,
      top: 12,
      left: -120,
    },
    hexagonalInnerTopLeft: {
      flexDirection: 'row',
      position: 'absolute',
      width: 86,
      height: 86,
      top: 30,
      left: -100,
    },
    hexagonalBottomLeft: {
      flexDirection: 'row',
      position: 'absolute',
      width: 120,
      height: 120,
      top: 192,
      left: -120,
    },
    hexagonalInnerBottomLeft: {
      flexDirection: 'row',
      position: 'absolute',
      width: 86,
      height: 86,
      top: 210,
      left: -100,
    },
    hexagonalTopRight: {
      flexDirection: 'row',
      position: 'absolute',
      width: 120,
      height: 120,
      top: 12,
      right: 0,
    },
    hexagonalInnerTopRight: {
      flexDirection: 'row',
      position: 'absolute',
      width: 86,
      height: 86,
      top: 30,
      right: 14,
    },
    hexagonalBottomRight: {
      flexDirection: 'row',
      position: 'absolute',
      width: 120,
      height: 120,
      top: 192,
      right: 0,
    },
    hexagonalInnerBottomRight: {
      flexDirection: 'row',
      position: 'absolute',
      width: 86,
      height: 86,
      top: 210,
      right: 14,
    },
    innerTrapezoidLeft: {
      width: 0,
      height: 86,
      borderBottomWidth: 24,
      borderBottomColor: 'transparent',
      borderRightWidth: 100,
      borderRightColor: colors.specified,
      borderTopWidth: 24,
      borderTopColor: 'transparent',
      borderStyle: 'solid'
    },
    innerTrapezoidRight: {
      width: 0,
      height: 86,
      borderBottomWidth: 24,
      borderBottomColor: 'transparent',
      borderLeftWidth: 100,
      borderLeftColor: '#79DBED',
      borderTopWidth: 24,
      borderTopColor: 'transparent',
      borderStyle: 'solid'
    },
    trapezoidLeft: {
      width: 0,
      height: 120,
      borderBottomWidth: 24,
      borderBottomColor: 'transparent',
      borderRightWidth: 120,
      borderRightColor: '#C9C6E6',
      borderTopWidth: 30,
      borderTopColor: 'transparent',
      borderStyle: 'solid'
    },
    trapezoidRight: {
      width: 0,
      height: 120,
      borderBottomWidth: 30,
      borderBottomColor: 'transparent',
      borderLeftWidth: 120,
      borderLeftColor: '#C9C6E6',
      borderTopWidth: 30,
      borderTopColor: 'transparent',
      borderStyle: 'solid'
    },
  })
);

