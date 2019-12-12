import i18next from 'i18next';

import ja from './locales/ja';

export default (callback) => i18next.init({
  lng: 'ja',
  debug: true,
  ns: 'id44.mizuki',
  defaultNS: 'id44.mizuki',
  resources: {
    ja: {
      'id44.mizuki': ja,
    },
  },
}, callback)
