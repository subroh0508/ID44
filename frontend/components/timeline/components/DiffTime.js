import React, { memo, useContext } from 'react';
import { Text, ThemeContext } from "react-native-elements";
import { View, StyleSheet } from "react-native";
import i18next from "i18next";
import { datetimes } from 'ID44-shared';

const Component = ({ diffTime }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <Text
      style={ styles.root }
      numberOfLines={ 1 }>
      { diffTime }
    </Text>
  );
};

function toDiffTime(time) {
  return datetimes.toDiffTime(time, (key, option) => i18next.t(key, option))
}

export const DiffTime = memo(
  ({ time }) => (<Component diffTime={ toDiffTime(time) }/>),
  (prev, next) => toDiffTime(prev.time) === toDiffTime(next.time),
);

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      color: colors.secondary,
    },
  })
);
