import { combineReducers } from "redux";
import {ON_CHANGED_HOST_NAME, TOGGLE_AUTHORIZATION_STATUS} from "../actions";

const initialState = {
  hostName: '',
  authorization: {
    status: 'init',
    message: null,
  },
};

const signIn = (state = initialState, action) => {
  console.log(action);
  switch (action.type) {
    case ON_CHANGED_HOST_NAME:
      return { ...state, hostName: action.value };
    case TOGGLE_AUTHORIZATION_STATUS:
      return { ...state, authorization: { status: action.status, message: action.message } };
    default:
      return state;
  }
};

export default signIn;
