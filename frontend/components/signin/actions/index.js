import { onClickAuthorize } from '../native/SignInModule';

const prefix = 'sign_in';

export const ON_CHANGED_HOST_NAME = `${prefix}/ON_CHANGED_HOST_NAME`;
export const onChangedHostName = host => ({
  type: ON_CHANGED_HOST_NAME,
  value: host,
});
