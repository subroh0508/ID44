const locales = {
  commons: {
    time: {
      now: '今',
      seconds: '{{time}}秒',
      minutes: '{{time}}分',
      hours: '{{time}}時間',
      day: '{{day}}日',
      date: '{{date}}',
    },
  },
  signIn: {
    exceptions: {
      accessDenied: 'アカウントへのアクセスが拒否されました',
      authorizeFailed: '認証中にエラーが発生しました',
      browserAppNotFound: 'ブラウザアプリが見つかりません',
      unknownHost: 'ホスト名に誤りがあります',
      unknown: '予期せぬエラーが発生しました',
    },
  },
  privacy: {
    'public': {
      short: '公開',
      long: '公開TLに投稿する',
    },
    unlisted: {
      short: '未収載',
      long: '公開TLで表示しない',
    },
    'private': {
      short: 'フォロワー限定',
      long: 'フォロワーだけに公開',
    },
    direct: {
      short: 'ダイレクト',
      long: 'メンションしたユーザーだけに公開',
    },
  },
};

export default locales;
