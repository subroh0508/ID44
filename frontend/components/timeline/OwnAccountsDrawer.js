import { StyleSheet, View } from "react-native";
import { Button, ListItem, ThemeContext } from "react-native-elements";
import { openAuthentication, switchAccount } from "./native/TimelineModule";
import React, {useContext} from "react";

const withStyles = ({ colors }) => (
  StyleSheet.create({
    root: {
      height: '100%',
      backgroundColor: colors.background,
      borderEndWidth: 1,
      borderColor: colors.primary,
      shadowColor: '#FFF',
      shadowOffset: {
        width: 0,
        height: 2,
      },
      shadowOpacity: 0.25,
      shadowRadius: 3.84,
      elevation: 5,
    },
  })
);

export const OwnAccountsDrawer = ({ screenProps }) => {
  const { theme } = useContext(ThemeContext);

  const styles = withStyles(theme);

  return (
    <View style={ styles.root }>
      {
        screenProps.ownAccounts.map((account, i) => (
          <ListItem key={ i }
            leftAvatar={{ source: { uri: account.avatar } }}
            title={ account.displayName }
            subtitle={ account.screen }
            bottomDivider
            onPress={ e => switchAccount(account.hostName, account.id) }>
          </ListItem>
        ))
      }
      <Button title='アカウント追加'
        type='clear'
        onPress={ e => openAuthentication() }/>
    </View>
  );
};
