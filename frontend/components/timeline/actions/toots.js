import {
  submitStatus as nativeSubmitStatus,
} from '../native/TimelineNativeActions';

const prefix = 'toots';

export const submitToot = (text, visibility) => async (dispatch, _getState) => {
  try {
    await nativeSubmitStatus(text, null, visibility);
  } catch (e) {

  }

  dispatch(clearTootText());
};

export const SET_TOOT_TEXT = `${prefix}/SET_TOOT_TEXT`;
export const setTootText = (text) => ({
  type: SET_TOOT_TEXT,
  value: text,
});

export const clearTootText = setTootText.bind(null, "");
