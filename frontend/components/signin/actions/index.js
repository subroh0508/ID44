import { startOauth2Flow, openTimeline } from '../native/SignInModule';

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
    console.log(e);
    dispatch(toggleAuthorizationStatus(STATUS_ERROR, e.message))
  }
};

export const TOGGLE_AUTHORIZATION_STATUS = `${prefix}/TOGGLE_AUTHORIZATION_STATUS`;
export const toggleAuthorizationStatus = (status, message = null) => ({
  type: TOGGLE_AUTHORIZATION_STATUS,
  status, message,
});
