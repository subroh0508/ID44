import { AppRegistry } from 'react-native';
import { remote } from 'electron';
import registerComponent from 'ID44-frontend/utilities/registerComponent';
import { SignIn } from 'ID44-frontend/components/signin/containers/SignIn';
import reducer from 'ID44-frontend/components/signin/reducers';
import { SignInNativeActions } from 'ID44-actions-signin';

console.log('SignInNativeActions', SignInNativeActions(remote.app, remote.shell));
registerComponent('SignIn', SignIn, reducer, SignInNativeActions(remote.app, remote.shell));

AppRegistry.runApplication('SignIn', { rootTag: document.getElementById('app') });
