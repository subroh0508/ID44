import { combineReducers } from "redux";
import * as Actions from "../actions";

const initialState = {
  hostName: '',
  authorization: {
    status: 'init',
    message: null,
  },
};

const signIn = (state = initialState, action) => {
  switch (action.type) {
    case Actions.ON_CHANGED_HOST_NAME:
      return { ...state, hostName: action.value };
    case Actions.ON_CHANGE_AUTHORIZATION_STATUS:
      return { ...state, authorization: { status: action.status, message: action.message } };
    default:
      return state;
  }
};

export default signIn;
