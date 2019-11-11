import { AppRegistry } from 'react-native';
import { Timeline } from "./components/timeline/index";
import { Auth } from "./components/auth/index";

AppRegistry.registerComponent('Auth', () => Auth);
AppRegistry.registerComponent('Timeline', () => Timeline);
