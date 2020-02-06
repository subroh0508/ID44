import { NativeModules } from 'react-native';

import registerComponent from '../../utilities/registerComponent';
import { SignIn } from './containers/SignIn';
import reducer from './reducers';

registerComponent('SignIn', SignIn, reducer, NativeModules.SignInNativeActions);
