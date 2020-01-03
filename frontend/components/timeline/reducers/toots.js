import { STATUS_VISIBILITY } from '../native/TimelineNativeActions';
import * as Actions from "../actions/toots";

const initialState = {
  text: '',
  visibility: STATUS_VISIBILITY.PUBLIC,
  state: {
    onProgress: false,
    openOptions: false,
  },
};

const toots = (state = initialState, action) => {
  switch (action.type) {
    case Actions.SET_TOOT_TEXT:
      return {
        ...state,
        text: action.value,
      };
    case Actions.TOGGLE_PROGRESS:
      return {
        ...state,
        state: {
          ...state.state,
          onProgress: action.value,
        },
      };
    case Actions.TOGGLE_OPTIONS:
      return {
        ...state,
        state: {
          ...state.state,
          openOptions: action.value,
        },
      };
    default:
      return state;
  }
};

export default toots;
