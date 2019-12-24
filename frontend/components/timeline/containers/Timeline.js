import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { subscribe, unsubscribe } from "../actions/timelines";
import { STREAM } from '../native/TimelineModule';
import { StreamPane } from "../components/StreamPane";

export const Timeline = ({ account }) => {
  const subscriptions = useSelector(state => state.timelines.subscriptions);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(subscribe(STREAM.LOCAL, subscriptions));

    return () => dispatch(unsubscribe(STREAM.LOCAL, subscriptions));
  }, [account]);

  return (<StreamPane stream={ STREAM.LOCAL }/>)
};
