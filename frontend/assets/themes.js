const colors = {
  mizuki: {
    primary: '#00ffff',
    secondary: '#008B8B',
    text: '#afeeee',
    disabled: '#7ea8a8',
    placeholder: '#7ea8a8',
    background: '#000'
  },
  tsumugi: {
    primary: '#ff00ff',
    secondary: '#8B008B',
    text: '#eeafee',
    disabled: '#a87ea8',
    placeholder: '#a87ea8',
    background: '#000'
  },
  shiho: {
    primary: '#ffff00',
    secondary: '#8B8B00',
    text: '#eeeeaf',
    disabled: '#a8a87e',
    placeholder: '#a8a87e',
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
      borderColor: colors.primary,
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
      color: colors.secondary,
    },
  },
});

export { colors, createTheme }
