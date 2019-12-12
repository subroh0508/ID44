import i18next from "i18next";
import { startOauth2Flow, showToast, openTimeline } from '../native/SignInModule';

const prefix = 'sign_in';

const STATUS_START = 'start';
const STATUS_ERROR = 'error';
const STATUS_FINISH = 'finish';

export const ON_CHANGED_HOST_NAME = `${prefix}/ON_CHANGED_HOST_NAME`;
export const onChangedHostName = host => ({
  type: ON_CHANGED_HOST_NAME,
  value: host,
});

export const onClickedAuthorize = host => async (dispatch, _getState) => {
  dispatch(toggleAuthorizationStatus(STATUS_START));

  try {
    await startOauth2Flow(host);

    openTimeline();
    dispatch(toggleAuthorizationStatus(STATUS_FINISH));
  } catch (e) {
    let message = e.message || '';
    if (message.startsWith('ACCESS_DENIED')) {
      message = i18next.t('signIn.exceptions.accessDenied');
    } else if (message.startsWith('AUTHORIZE_FAILED')) {
      message = i18next.t('signIn.exceptions.authorizeFailed');
    } else if (message.startsWith('BROWSER_APP_NOT_FOUND')) {
      message = i18next.t('signIn.exceptions.browserAppNotFound');
    } else if (message.startsWith('UNKNOWN')) {
      message = i18next.t('signIn.exceptions.unknown');
    }

    showToast(message);
    dispatch(toggleAuthorizationStatus(STATUS_ERROR, message))
  }
};

export const TOGGLE_AUTHORIZATION_STATUS = `${prefix}/TOGGLE_AUTHORIZATION_STATUS`;
export const toggleAuthorizationStatus = (status, message = null) => ({
  type: TOGGLE_AUTHORIZATION_STATUS,
  status, message,
});
