import { STATUS_VISIBILITY } from '../native/TimelineNativeActions';
import * as Actions from "../actions/toots";

const initialState = {
  text: '',
  visibility: STATUS_VISIBILITY.PUBLIC,
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
