import {
  fetchOwnAccount as nativeFetchOwnAccount,
  fetchOwnAccounts as nativeFetchOwnAccounts,
  switchAccount,
  openAuthentication as nativeOpenAuthentication,
} from "../native/TimelineNativeActions";
import { serializer } from 'ID44-account-entity';
import { mapper } from 'ID44-shared';

const prefix = 'own_accounts';

export const fetchOwnAccounts = () => async (dispatch, _getState) => {
  const ownAccount = await nativeFetchOwnAccount();
  const ownAccounts = await nativeFetchOwnAccounts();

  const account = mapper.unmap(serializer(), ownAccount);
  dispatch(setOwnAccounts(ownAccounts.map(a => mapper.unmap(serializer(), a))));
  dispatch(selectAccount(mapper.unmap(serializer(), ownAccount)));
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
