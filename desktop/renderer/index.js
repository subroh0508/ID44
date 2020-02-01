import React from 'react';
import { AppRegistry } from 'react-native';
import { registerComponent } from 'ID44-frontend/components/signin';

registerComponent();
AppRegistry.runApplication('SignIn', { rootTag: document.getElementById('app') });
