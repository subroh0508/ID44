import * as Actions from "../actions/ownAccounts";

const initialState = {
  accounts: [],
  selectedAccount: null,
};

const ownAccounts = (state = initialState, action) => {
  switch (action.type) {
    case Actions.SET_OWN_ACCOUNTS:
      return {
        ...state,
        accounts: action.value,
      };
    case Actions.SELECT_ACCOUNT:
      return {
        ...state,
        selectedAccount: action.value,
      };
    default:
      return state;
  }
};

export default ownAccounts;
