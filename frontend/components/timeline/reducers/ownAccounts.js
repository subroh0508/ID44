import * as Actions from "../actions/ownAccounts";

const initialState = [];

const ownAccounts = (state = initialState, action) => {
  switch (action.type) {
    case Actions.SET_OWN_ACCOUNTS:
      return action.value;
    default:
      return state;
  }
};

export default ownAccounts;
