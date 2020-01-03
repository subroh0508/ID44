import {
  submitStatus as nativeSubmitStatus,
} from '../native/TimelineNativeActions';

const prefix = 'toots';

export const submitToot = (text, visibility) => async (dispatch, _getState) => {
  try {
    dispatch(startProgress());
    await nativeSubmitStatus(text, null, visibility);
  } catch (e) {
    dispatch(stopProgress());
  }

  dispatch(clearTootText());
  dispatch(stopProgress());
};

export const SET_TOOT_TEXT = `${prefix}/SET_TOOT_TEXT`;
export const setTootText = (text) => ({
  type: SET_TOOT_TEXT,
  value: text,
});

export const clearTootText = setTootText.bind(null, "");

export const TOGGLE_PROGRESS = `${prefix}/TOGGLE_PROGRESS`;
const startProgress = () => ({
  type: TOGGLE_PROGRESS,
  value: true,
});

const stopProgress = () => ({
  type: TOGGLE_PROGRESS,
  value: false,
});

export const TOGGLE_OPTIONS = `${prefix}/TOGGLE_OPTIONS`;
export const toggleOptions = (open) => ({
  type: TOGGLE_OPTIONS,
  value: open,
});
