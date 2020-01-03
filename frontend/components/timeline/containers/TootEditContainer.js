import React from 'react';
import { useDispatch, useSelector } from "react-redux";
import { View } from 'react-native';
import { TootArea } from "../components/TootArea";
import { TootOptions } from "../components/TootOptions";
import { setTootText, submitToot, setTootVisibility, toggleContentWarning, setWarningText } from "../actions/toots";
import { WarningTextArea } from "../components/WarningTextArea";

export const TootEditContainer = ({ account, onClickAvatar }) => {
  const { text, state: { onProgress }, options } = useSelector(state => state.toots);
  const dispatch = useDispatch();

  const { visibility, contentWarning, warningText } = options;

  return (
    <View>
      <WarningTextArea
        isVisible={ contentWarning }
        onProgress={ onProgress }
        warningText={ warningText }
        onChangeWarningText={ text => dispatch(setWarningText(text)) }/>
      <TootArea
        account={ account }
        tootText={ text }
        onProgress={ onProgress }
        onClickAvatar={ onClickAvatar }
        onChangeText={ s => dispatch(setTootText(s)) }
        onClickSubmit={ () => dispatch(submitToot(text, visibility)) }/>
      <TootOptions
        remainTextCount={ 500 - text.length }
        options={ options }
        setTootVisibility={ visibility => dispatch(setTootVisibility(visibility)) }
        toggleContentWarning={ cw => dispatch(toggleContentWarning(cw)) }/>
    </View>
  );
};
