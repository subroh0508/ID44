import { NativeEventEmitter } from 'react-native';
import TimelineModule, {
  EVENT_APPEND_STATUS,
  subscribe as nativeSubscribe,
  unsubscribe as nativeUnsubscribe,
} from '../native/TimelineModule';
import { unmapStatus } from '../../../kotlin_build/packages/ID44-entity/kotlin/ID44-entity';

const prefix = 'timeline';

export const subscribe = (account, stream, active) => async (dispatch, _getState) => {
  const key = streamKey(account, stream);

  if (key === null) {
    return;
  }

  if (active.hasOwnProperty(key)) {
    active[key].remove();
    dispatch(activateSubscription(key, addEventListener(key, dispatch)));

    return;
  }

  try {
    await nativeSubscribe(account.hostName, account.id, stream);

    dispatch(activateSubscription(key, addEventListener(key, dispatch)));
  } catch (e) {

  }
};

export const unsubscribe = (account, stream) => async (dispatch, _getState) => {
  const key = streamKey(account, stream);
  if (key === null) {
    return;
  }

  try {
    await nativeUnsubscribe(account.hostName, account.id, stream);
  } catch (e) {

  }

  dispatch(inactivateSubscription(key));
};

export const unsubscribeAll = ({ active, inactive }) => async (dispatch, _getState) => {
  const subscriptions = Object.assign(inactive, active);
  Object.keys(subscriptions).forEach(key => {
    subscriptions[key].remove();
  });

  dispatch(clearAllSubscription());
};

const addEventListener = (streamKey, dispatch) => new NativeEventEmitter(TimelineModule)
  .addListener(EVENT_APPEND_STATUS, status => {
    console.log(status.content);
    dispatch(appendStatus(streamKey, unmapStatus(status)));
  });

export const removeEventListener = (inactive) => async (dispatch, _getState) => {
  const inactiveKeys = Object.keys(inactive);
  if (inactiveKeys.length === 0) {
    return;
  }

  inactiveKeys.forEach(inactiveKey => {
    inactive[inactiveKey] && inactive[inactiveKey].remove();
  });
  dispatch(clearInactiveSubscription());
};

export const ACTIVATE_SUBSCRIPTION = `${prefix}/ACTIVATE_SUBSCRIPTION`;
export const activateSubscription = (streamKey, subscription) => ({
  type: ACTIVATE_SUBSCRIPTION,
  value: streamKey,
  subscription,
});

export const INACTIVATE_SUBSCRIPTION = `${prefix}/INACTIVATE_SUBSCRIPTION`;
export const inactivateSubscription = (streamKey) => ({
  type: INACTIVATE_SUBSCRIPTION,
  value: streamKey,
});

export const CLEAR_INACTIVE_SUBSCRIPTION = `${prefix}/CLEAR_INACTIVE_SUBSCRIPTION`;
export const clearInactiveSubscription = () => ({
  type: CLEAR_INACTIVE_SUBSCRIPTION,
});

export const CLEAR_ALL_SUBSCRIPTION = `${prefix}/CLEAR_ALL_SUBSCRIPTION`;
export const clearAllSubscription = () => ({
  type: CLEAR_ALL_SUBSCRIPTION,
});

export const APPEND_STATUS = `${prefix}/APPEND_STATUS`;
export const appendStatus = (streamKey, status) => ({
  type: APPEND_STATUS,
  value: streamKey,
  status,
});

export const CLEAR_STREAMS = `${prefix}/CLEAR_STREAMS`;
export const clearStreams = () => ({
  type: CLEAR_STREAMS,
});

export const streamKey = (account, stream) => account ? `${account.screen}/${stream}` : null;
