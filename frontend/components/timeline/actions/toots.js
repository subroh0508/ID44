import {
  submitStatus as nativeSubmitStatus,
} from '../native/TimelineNativeActions';

const prefix = 'toots';

export const submitToot = (text, { visibility, contentWarning, warningText }) => async (dispatch, _getState) => {
  try {
    dispatch(startProgress());
    await nativeSubmitStatus(text, contentWarning ? warningText : null, visibility.toString());
  } catch (e) {
    dispatch(stopProgress());
  }

  dispatch(clearTootText());
  dispatch(resetOptionsProperties());
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

export const SET_OPTIONS_PROPERTY = `${prefix}/SET_OPTIONS_PROPERTY`;
const setOptionsProperty = (property, value) => ({
  type: SET_OPTIONS_PROPERTY,
  property, value,
});

export const setTootVisibility = setOptionsProperty.bind(null, 'visibility');
export const toggleContentWarning = (value) => (dispatch, _getState) => {
  if (!value) {
    dispatch(clearWarningText());
  }

  dispatch(setOptionsProperty('contentWarning', value));
};
export const setWarningText = setOptionsProperty.bind(null, 'warningText');

const clearWarningText = setWarningText.bind(null, '');

export const RESET_OPTIONS_PROPERTIES = `${prefix}/RESET_OPTIONS_PROPERTIES`;
const resetOptionsProperties = () => ({
  type: RESET_OPTIONS_PROPERTIES,
});
