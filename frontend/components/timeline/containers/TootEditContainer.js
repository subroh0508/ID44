import React from 'react';
import { useDispatch, useSelector } from "react-redux";
import { TootArea } from "../components/TootArea";
import { setTootText, submitToot } from "../actions/toots";

export const TootEditContainer = (props) => {
  const { text, visibility } = useSelector(state => state.toots);
  const dispatch = useDispatch();

  return (
    <TootArea {...{
      ...props,
      tootText: text,
      onChangeText: s => dispatch(setTootText(s)),
      onClickSubmit: () => dispatch(submitToot(text, visibility)),
    }}/>
  );
};
