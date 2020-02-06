import { AppRegistry } from 'react-native';
import registerComponent from 'ID44-frontend/utilities/registerComponent';
import { SignIn } from 'ID44-frontend/components/signin/containers/SignIn';
import reducer from 'ID44-frontend/components/signin/reducers';
import { SignInNativeActions } from 'ID44-bridges-signin';

console.log('SignInNativeActions', SignInNativeActions);
registerComponent('SignIn', SignIn, reducer, SignInNativeActions);

AppRegistry.runApplication('SignIn', { rootTag: document.getElementById('app') });
