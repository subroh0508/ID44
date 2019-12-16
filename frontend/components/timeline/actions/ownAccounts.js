import {
  fetchOwnAccounts as fetchCachedOwnAccounts,
  switchAccount,
  openAuthentication,
} from "../native/TimelineModule";

const prefix = 'own_accounts';

export const fetchOwnAccounts = () => async (dispatch, _getState) => {
  const ownAccounts = await fetchCachedOwnAccounts();

  dispatch(setOwnAccounts(ownAccounts));
};

export const SET_OWN_ACCOUNTS = `${prefix}/SET_OWN_ACCOUNTS`;
export const setOwnAccounts = (ownAccounts) => ({
  type: SET_OWN_ACCOUNTS,
  value: ownAccounts,
});

export const onClickSwitchAccount = (hostName, id) => switchAccount(hostName, id);
export const onClickOpenAuthentication = openAuthentication;
