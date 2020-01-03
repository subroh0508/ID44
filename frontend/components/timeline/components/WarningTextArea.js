import React, { useContext } from 'react';
import { StyleSheet, View } from "react-native";
import { Input, ThemeContext } from "react-native-elements";

export const WarningTextArea = ({
  isVisible, onProgress, warningText,
  onChangeWarningText,
}) => {
  if (!isVisible) {
    return null;
  }

  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <Input
      placeholder='ここに警告を書いてください'
      multiline={ false }
      numberOfLines={ 1 }
      disabled={ onProgress }
      containerStyle={ styles.warningTextAreaContainer }
      inputContainerStyle={ styles.warningTextAreaInputContainer }
      value={ warningText }
      onChangeText={ onChangeWarningText }/>
  )
};

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      flexDirection: 'row',
    },
    warningTextAreaContainer: {
      flexShrink: 1,
    },
    warningTextAreaInputContainer: {
      borderColor: colors.secondary,
      marginTop: 8,
    },
  })
);
