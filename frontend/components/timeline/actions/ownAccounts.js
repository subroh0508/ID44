import {
  fetchOwnAccounts as nativeFetchOwnAccounts,
  switchAccount,
  openAuthentication as nativeOpenAuthentication,
} from "../native/TimelineModule";

const prefix = 'own_accounts';

export const fetchOwnAccounts = () => async (dispatch, _getState) => {
  const ownAccounts = await nativeFetchOwnAccounts();

  dispatch(setOwnAccounts(ownAccounts));
  dispatch(selectAccount(ownAccounts[0]));
};

export const onClickSwitchAccount = (account) => (dispatch, _getState) => {
  switchAccount(account.hostName, account.id);

  dispatch(selectAccount(account));
};

export const openAuthentication = () => (dispatch, _getState) => {
  nativeOpenAuthentication();

  dispatch(selectAccount(null));
};

export const SET_OWN_ACCOUNTS = `${prefix}/SET_OWN_ACCOUNTS`;
export const setOwnAccounts = (ownAccounts) => ({
  type: SET_OWN_ACCOUNTS,
  value: ownAccounts,
});

export const SELECT_ACCOUNT = `${prefix}/SELECT_ACCOUNT`;
export const selectAccount = (account) => ({
  type: SELECT_ACCOUNT,
  value: account,
});
