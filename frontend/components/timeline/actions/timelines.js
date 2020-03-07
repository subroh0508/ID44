import { NativeEventEmitter } from 'react-native';
import TimelineModule, {
  EVENT_APPEND_STATUS,
  fetchStatuses as nativeFetchStatuses,
  subscribe as nativeSubscribe,
  unsubscribe as nativeUnsubscribe,
  toggleFavourite as nativeToggleFavourite,
  toggleReblog as nativeToggleReblog,
} from '../native/TimelineNativeActions';
import { serializer } from 'ID44-model-status';
import { mapper } from 'ID44-util';

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

export const requestSubscribeAll = (account, streams) => async (dispatch, _getState) => {
  streams.forEach(stream => dispatch(requestSubscribe(account, stream, [])));
  dispatch(setFocusStream(account, streams[0]));
};

export const requestUnsubscribeAll = (account, active) => async (dispatch, _getState) => {
  active.forEach(key => dispatch(requestUnsubscribe(account, streamFromKey(key), active)));
  dispatch(setFocusTab(null));
};

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

export const SET_FOCUS_TAB =`${prefix}/SET_FOCUS_TAB`;
export const setFocusTab = (tab) => ({
  type: SET_FOCUS_TAB,
  value: tab,
});

export const setFocusStream = (account, stream) => setFocusTab(streamKey(account, stream));

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

export const toggleFavourite = (status) => async (dispatch, _getStatus) => {
  try {
    const favourited = mapper.unmap(
      serializer(),
      await nativeToggleFavourite(mapper.stringify(serializer(), status)),
    );

    dispatch(updateStatus(favourited));
  } catch (e) {

  }
};

export const toggleReblog = (status) => async (dispatch, _getStatus) => {
  try {
    const reblogged = mapper.unmap(
      serializer(),
      await nativeToggleReblog(mapper.stringify(serializer(), status)),
    );

    dispatch(updateStatus(reblogged));
  } catch (e) {

  }
};

export const UPDATE_STATUS = `${prefix}/UPDATE_STATUS`;
const updateStatus = (status) => ({
  type: UPDATE_STATUS,
  value: status,
});
