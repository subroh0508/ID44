import { STATUS_VISIBILITY } from '../native/TimelineNativeActions';
import * as Actions from "../actions/toots";

const initialState = {
  text: '',
  options: {
    visibility: STATUS_VISIBILITY.PUBLIC,
    contentWarning: false,
    warningText: '',
  },
  state: {
    onProgress: false,
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
    case Actions.SET_OPTIONS_PROPERTY:
      return {
        ...state,
        options: {
          ...state.options,
          [action.property]: action.value,
        },
      };
    default:
      return state;
  }
};

export default toots;
