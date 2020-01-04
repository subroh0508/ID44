import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import {
  subscribe, unsubscribe,
  requestSubscribe, requestUnsubscribe,
  fetchStatuses,
  setFocusTab,
} from "../actions/timelines";
import { HomeTab } from "../components/HomeTab";
import { STREAM } from '../native/TimelineNativeActions';

const streams = [STREAM.LOCAL, STREAM.HOME, STREAM.GLOBAL];

export const Timelines = ({ account }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    const subscription = subscribe(dispatch);
    streams.forEach(stream => {
      dispatch(fetchStatuses(account, stream));
      dispatch(requestSubscribe(account, stream, []));
    });

    return () => {
      streams.forEach(stream => dispatch(requestUnsubscribe(account, stream, streams)));
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
