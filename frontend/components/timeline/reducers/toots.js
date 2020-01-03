import * as Actions from "../actions/toots";

const initialState = {
  text: '',
};

const toots = (state = initialState, action) => {
  switch (action.type) {
    case Actions.SET_TOOT_TEXT:
      return {
        ...state,
        text: action.value,
      };
    default:
      return state;
  }
};

export default toots;
