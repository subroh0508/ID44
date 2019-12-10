import { combineReducers } from "redux";
import { ON_CHANGED_HOST_NAME } from "../actions";

const initialState = {
  hostName: '',
};

const signIn = (state = initialState, action) => {
  console.log(action);
  switch (action.type) {
    case ON_CHANGED_HOST_NAME:
      return { ...state, hostName: action.value };
    default:
      return state;
  }
};

export default signIn;
