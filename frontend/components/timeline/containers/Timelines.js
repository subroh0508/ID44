import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import {
  subscribe, unsubscribe,
  requestSubscribeAll, requestUnsubscribeAll,
  fetchStatuses,
  setFocusTab, setFocusStream,
} from "../actions/timelines";
import { HomeTab } from "../components/HomeTab";
import { STREAM } from '../native/TimelineNativeActions';

const streams = [STREAM.LOCAL, STREAM.HOME, STREAM.GLOBAL];

export const Timelines = ({ account }) => account ? (<InternalTimelines account={ account }/>) : null;

const InternalTimelines = ({ account }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    const subscription = subscribe(dispatch);
    dispatch(setFocusStream(account, streams[0]));
    streams.forEach(stream => dispatch(fetchStatuses(account, stream)));
    dispatch(requestSubscribeAll(account, streams));

    return () => {
      dispatch(requestUnsubscribeAll(account, streams));
      dispatch(unsubscribe(subscription));
    }
  }, [account]);

  return (
    <HomeTab
      account={ account }
      streams={ streams }
      onNavigationStateChange={ (_new, _prev, action) => dispatch(setFocusTab(action.routeName)) }/>
  );
};
