const colors = {
  mizuki: {
    primary: '#00ffff',
    text: '#00ffff',
    disabled: '#afeeee',
    placeholder: '#afeeee',
    background: '#000'
  },
  tsumugi: {
    primary: '#ff00ff',
    text: '#ff00ff',
    disabled: '#ee82ee',
    placeholder: '#ee82ee',
    background: '#000'
  },
  shiho: {
    primary: '#ffff00',
    text: '#ffff00',
    disabled: '#fffacd',
    placeholder: '#fffacd',
    background: '#000',
  },
};

const createTheme = colors => ({
  colors,
  Text: {
    style: {
      color: colors.text,
    },
  },
  Input: {
    inputStyle: {
      color: colors.text,
    },
    inputContainerStyle: {
      borderColor: colors.placeholder,
      borderWidth: 1,
      borderRadius: 4,
      paddingStart: 8,
      paddingEnd: 8,
    },
    placeholderTextColor: colors.placeholder,
  },
  Button: {
    titleStyle: {
      color: colors.text,
    },
  },
  ListItem: {
    containerStyle: {
      backgroundColor: colors.background,
    },
    titleStyle: {
      color: colors.text,
    },
    subtitleStyle: {
      color: colors.placeholder,
    },
  },
});

export { colors, createTheme }
