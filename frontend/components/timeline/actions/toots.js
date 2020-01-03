const prefix = 'toots';

export const submitToot = (text) => async (dispatch, _getState) => {
  console.log('submit: ', text);

  dispatch(clearTootText());
};

export const SET_TOOT_TEXT = `${prefix}/SET_TOOT_TEXT`;
export const setTootText = (text) => ({
  type: SET_TOOT_TEXT,
  value: text,
});

export const clearTootText = setTootText.bind(null, "");
