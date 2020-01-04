import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { subscribe, unsubscribe, streamKey, removeEventListener } from "../actions/timelines";
import { StreamPane } from "../components/StreamPane";

export const Timeline = ({ account, stream }) => {
  const { active, inactive } = useSelector(state => state.timelines);
  const dispatch = useDispatch();
/*
  useEffect(() => {
    dispatch(subscribe(account, stream, active));

    return () => dispatch(unsubscribe(account, stream));
  }, [account]);

  useEffect(() => {
    dispatch(removeEventListener(inactive));
  }, [inactive]);

 */

  return (<StreamPane streamKey={ streamKey(account, stream) }/>)
};
