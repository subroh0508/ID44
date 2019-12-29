import React, { useEffect } from 'react';
import { useDispatch, useStore } from "react-redux";
import { subscribe, unsubscribe, streamKey } from "../actions/timelines";
import { STREAM } from '../native/TimelineModule';
import { StreamPane } from "../components/StreamPane";

export const Timeline = ({ account }) => {
  const { getState } = useStore();
  const dispatch = useDispatch();

  useEffect(() => {
    function getSubscriptions() {
      return getState().timelines.subscriptions;
    }

    dispatch(subscribe(account, STREAM.LOCAL, getSubscriptions()));

    return () => dispatch(unsubscribe(account, STREAM.LOCAL, getSubscriptions()));
  }, [account]);

  return (<StreamPane streamKey={ streamKey(account, STREAM.LOCAL) }/>)
};
