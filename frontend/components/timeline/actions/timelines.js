import { NativeEventEmitter } from 'react-native';
import TimelineModule, {
  EVENT_APPEND_STATUS,
  STREAM,
  subscribe as nativeSubscribe,
  unsubscribe as nativeUnsubscribe,
} from '../native/TimelineModule';

const prefix = 'timeline';

export const subscribe = (account, stream, subscriptions) => async (dispatch, _getState) => {
  const key = streamKey(account, stream);
  if (subscriptions.hasOwnProperty(key)) {
    const subscription = subscriptions[key];

    subscription.remove();

    dispatch(setReadyForSubscription(key, addEventListener(key, dispatch)));

    return;
  }

  try {
    await nativeSubscribe(account.hostName, account.id, stream);

    dispatch(setReadyForSubscription(key, addEventListener(key, dispatch)));
  } catch (e) {

  }
};

export const unsubscribe = (account, stream, subscriptions) => async (dispatch, _getState) => {
  const key = streamKey(account, stream);
  if (subscriptions.hasOwnProperty(key)) {
    subscriptions[key].remove();
  }

  try {
    await nativeUnsubscribe(account.hostName, account.id, stream);
  } catch (e) {

  }

  dispatch(removeSubscription(key));
};

export const unsubscribeAll = (subscriptions) => async (dispatch, _getState) => {
  Object.keys(subscriptions).forEach(key => {
    subscriptions[key].remove();
  });

  dispatch(clearSubscription());
};

const addEventListener = (streamKey, dispatch) => new NativeEventEmitter(TimelineModule)
  .addListener(EVENT_APPEND_STATUS, status => {
    console.log(status.content);
    dispatch(appendStatus(streamKey, status));
  });

export const SET_READY_FOR_SUBSCRIPTION = `${prefix}/SET_READY_FOR_SUBSCRIPTION`;
export const setReadyForSubscription = (streamKey, subscription) => ({
  type: SET_READY_FOR_SUBSCRIPTION,
  value: streamKey,
  subscription,
});

export const REMOVE_SUBSCRIPTION = `${prefix}/REMOVE_SUBSCRIPTION`;
export const removeSubscription = (streamKey) => ({
  type: REMOVE_SUBSCRIPTION,
  value: streamKey,
});

export const CLEAR_SUBSCRIPTION = `${prefix}/CLEAR_SUBSCRIPTION`;
export const clearSubscription = () => ({
  type: CLEAR_SUBSCRIPTION,
});

export const APPEND_STATUS = `${prefix}/APPEND_STATUS`;
export const appendStatus = (streamKey, status) => ({
  type: APPEND_STATUS,
  value: streamKey,
  status,
});

export const CLEAR_STATUS = `${prefix}/CLEAR_STATUS`;
export const clearStatus = () => ({
  type: CLEAR_STATUS,
});

export const streamKey = (account, stream) => account ? `${account.screen}/${stream}` : null;
