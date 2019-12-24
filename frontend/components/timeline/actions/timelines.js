import { NativeEventEmitter } from 'react-native';
import TimelineModule, {
  EVENT_APPEND_STATUS,
  STREAM,
  subscribe as nativeSubscribe,
  unsubscribe as nativeUnsubscribe,
} from '../native/TimelineModule';

const prefix = 'timeline';

export const subscribe = (stream, subscriptions) => async (dispatch, _getState) => {
  console.log(subscriptions);
  if (subscriptions.hasOwnProperty(stream)) {
    const subscription = subscriptions[stream];

    subscription.remove();

    dispatch(setReadyForSubscription(stream, addEventListener(stream, dispatch)));

    return;
  }

  try {
    await nativeSubscribe(stream);

    dispatch(setReadyForSubscription(stream, addEventListener(stream, dispatch)));
  } catch (e) {

  }
};

export const unsubscribe = (stream, subscriptions) => async (dispatch, _getState) => {
  if (subscriptions.hasOwnProperty(stream)) {
    subscriptions[stream].remove();
  }

  dispatch(removeSubscription(stream));
};

export const unsubscribeAll = (subscriptions) => async (dispatch, _getState) => {
  Object.keys(subscriptions).forEach(key => {
    subscriptions[key].remove();
  });

  dispatch(clearSubscription());
};

const addEventListener = (stream, dispatch) => new NativeEventEmitter(TimelineModule)
  .addListener(EVENT_APPEND_STATUS, status => {
    console.log(status.content);
    dispatch(appendStatus(stream, status));
  });

export const SET_READY_FOR_SUBSCRIPTION = `${prefix}/SET_READY_FOR_SUBSCRIPTION`;
export const setReadyForSubscription = (stream, subscription) => ({
  type: SET_READY_FOR_SUBSCRIPTION,
  value: stream,
  subscription,
});

export const REMOVE_SUBSCRIPTION = `${prefix}/REMOVE_SUBSCRIPTION`;
export const removeSubscription = (stream) => ({
  type: REMOVE_SUBSCRIPTION,
  value: stream,
});

export const CLEAR_SUBSCRIPTION = `${prefix}/CLEAR_SUBSCRIPTION`;
export const clearSubscription = () => ({
  type: CLEAR_SUBSCRIPTION,
});

export const APPEND_STATUS = `${prefix}/APPEND_STATUS`;
export const appendStatus = (stream, status) => ({
  type: APPEND_STATUS,
  value: stream,
  status,
});

export const CLEAR_STATUS = `${prefix}/CLEAR_STATUS`;
export const clearStatus = () => ({
  type: CLEAR_STATUS,
});
