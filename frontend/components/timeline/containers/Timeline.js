import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import {subscribe, unsubscribe, streamKey, removeEventListener} from "../actions/timelines";
import { STREAM } from '../native/TimelineModule';
import { StreamPane } from "../components/StreamPane";

export const Timeline = ({ account }) => {
  const { active, inactive } = useSelector(state => state.timelines);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(subscribe(account, STREAM.LOCAL, active));

    return () => dispatch(unsubscribe(account, STREAM.LOCAL));
  }, [account]);

  useEffect(() => {
    dispatch(removeEventListener(inactive));
  }, [inactive]);

  return (<StreamPane streamKey={ streamKey(account, STREAM.LOCAL) }/>)
};
