const locales = {
  commons: {
    time: {
      now: '今',
      seconds: '{{time}}秒',
      minutes: '{{time}}分',
      hours: '{{time}}時間',
      days: '{{day}}日',
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
  tabsBar: {
    localTimeline: 'ローカル',
    home: 'ホーム',
    federatedTimeline: '連合',
  },
  status: {
    sensitiveWarning: '閲覧注意',
    share: '共有',
    showLess: '隠す',
    showLessAll: '全て隠す',
    showMore: 'もっと見る',
    showMoreAll: '全て見る',
    showThread: 'スレッドを表示する',
    rebloggedBy: '{{name}}さんがブースト',
  },
};

export default locales;
