import i18next from "i18next";
import { startOauth2Flow, showErrorMessage, openTimeline } from '../native/SignInActions';
import { exceptions } from 'ID44-signin';

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
  dispatch(onChangeAuthorizationStatus(STATUS_START));

  try {
    await startOauth2Flow(host);

    openTimeline();
    dispatch(onChangeAuthorizationStatus(STATUS_FINISH));
  } catch (e) {
    const message = i18next.t(exceptions.parseKey(e, "unknown"));

    showErrorMessage(message);
    dispatch(onChangeAuthorizationStatus(STATUS_ERROR, message))
  }
};

export const ON_CHANGE_AUTHORIZATION_STATUS = `${prefix}/ON_CHANGE_AUTHORIZATION_STATUS`;
export const onChangeAuthorizationStatus = (status, message = null) => ({
  type: ON_CHANGE_AUTHORIZATION_STATUS,
  status, message,
});
