import { NativeEventEmitter } from 'react-native';
import TimelineModule, {
  EVENT_APPEND_STATUS,
  fetchStatuses as nativeFetchStatuses,
  subscribe as nativeSubscribe,
  unsubscribe as nativeUnsubscribe,
} from '../native/TimelineNativeActions';
import { serializer } from 'ID44-timeline-entity';
import { mapper } from 'ID44-shared';

const prefix = 'timeline';

export const subscribe = (dispatch) => {
  const subscription = addEventListener(dispatch);
  dispatch(setSubscription(subscription));

  return subscription;
};

export const unsubscribe = (subscription) => async (dispatch, _getState) => {
  subscription.remove();

  dispatch(clearSubscription());
};

export const unsubscribeAll = ({ active, inactive }) => async (dispatch, _getState) => {
  const subscriptions = Object.assign(inactive, active);
  Object.keys(subscriptions).forEach(key => {
    subscriptions[key].remove();
  });

  dispatch(clearAllSubscription());
};

const addEventListener = (dispatch) => new NativeEventEmitter(TimelineModule)
  .addListener(EVENT_APPEND_STATUS, data => {
    console.log(data.status.content);
    dispatch(appendStatus(data.streamKey, mapper.unmap(serializer(), data.status)));
  });

export const fetchStatuses = (account, stream, maxId = null) => async (dispatch, _getState) => {
  try {
    const statuses = await nativeFetchStatuses(stream, maxId);
    dispatch(appendStatuses(streamKey(account, stream), statuses.map(status => mapper.unmap(serializer(), status))));
  } catch (e) {

  }
};

export const requestSubscribe = (account, stream, active) => async (dispatch, _getState) => {
  const key = streamKey(account, stream);
  if (active.includes(key)) {
    return
  }

  try {
    await nativeSubscribe(account.hostName, account.id, stream);
    dispatch(addActiveStream(key));
  } catch (e) {

  }
};

export const requestUnsubscribe = (account, stream, active) => async (dispatch, _getState) => {
  const key = streamKey(account, stream);
  if (!active.includes(key)) {
    return
  }

  try {
    await nativeUnsubscribe(account.hostName, account.id, stream);
    dispatch(removeActiveStream(key));
  } catch (e) {

  }
};

export const requestUnsubscribeAll = (account, active) => async (dispatch, _getState) =>
  active.forEach(key => dispatch(requestUnsubscribe(account, streamFromKey(key), active)));

export const ADD_ACTIVE_STREAM = `${prefix}/ADD_ACTIVE_STREAM`;
export const addActiveStream = (streamKey) => ({
  type: ADD_ACTIVE_STREAM,
  value: streamKey,
});

export const REMOVE_ACTIVE_STREAM = `${prefix}/REMOVE_ACTIVE_STREAM`;
export const removeActiveStream = (streamKey) => ({
  type: REMOVE_ACTIVE_STREAM,
  value: streamKey,
});

export const SET_SUBSCRIPTION = `${prefix}/SET_SUBSCRIPTION`;
export const setSubscription = (subscription) => ({
  type: SET_SUBSCRIPTION,
  value: subscription,
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

export const APPEND_STATUSES = `${prefix}/APPEND_STATUSES`;
export const appendStatuses = (streamKey, statuses) => ({
  type: APPEND_STATUSES,
  value: streamKey,
  statuses,
});

export const CLEAR_STREAMS = `${prefix}/CLEAR_STREAMS`;
export const clearStreams = () => ({
  type: CLEAR_STREAMS,
});

export const streamKey = (account, stream) => `${account.hostName}/${account.id}/${stream}`;
const streamFromKey = (streamKey) => streamKey.split('/')[2];
