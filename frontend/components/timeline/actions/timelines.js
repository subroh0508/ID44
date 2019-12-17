import { NativeEventEmitter } from 'react-native';
import TimelineModule, {
  EVENT_APPEND_STATUS,
  STREAM,
  subscribe as nativeSubscribe,
  unsubscribe as nativeUnsubscribe,
} from '../native/TimelineModule';

const prefix = 'timeline';

export const subscribe = (stream, subscription) => async (dispatch, _getState) => {
  if (subscription === null) {
    try {
      await nativeSubscribe(stream);

      dispatch(setReadyForSubscription(stream, addEventListener(stream, dispatch)));
    } catch (e) {

    }

    return
  }

  subscription.remove();

  dispatch(setReadyForSubscription(stream, addEventListener(stream, dispatch)));
};

export const unsubscribe = (stream, subscription) => async (dispatch, _getState) => {
  if (subscription !== null) {
    subscription.remove();
  }

  dispatch(removeSubscription(stream));
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

export const APPEND_STATUS = `${prefix}/APPEND_STATUS`;
export const appendStatus = (stream, status) => ({
  type: APPEND_STATUS,
  value: stream,
  status,
});
