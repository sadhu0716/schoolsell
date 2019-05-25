//app.js
App({
   appData:{
     userinfo:null
   },
  globalData: {
    //消息及聊天界面部分的全局变量
    //userid: 'jack',
    isOpen: false,
    fisrtmsglist: [],
    msgnumber: 0,
    //chatList: [],
    freshnewsList: [],
    //msgListInterface: [],
    isfirst: false,
    isOpenchatface: false,
    iscanrefresh: false,
    tomsglistfreshnews: []
  },
  aData: {
    show: false
  },
  setGlobalUserInfo:function(user){
      wx.setStorageSync("userInfo", user)
  }

})