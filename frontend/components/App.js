import React from 'react';
import {
    Text,
    View
} from 'react-native';

export default class App extends React.Component {
  render() {
    const styles = JSON.parse(this.props.styles);

    return (
      <View style={ styles.container }>
        <Text style={ styles.hello }>Hello, World</Text>
      </View>
    );
  }
}
