 var App = getApp();
 //  var isLoginSuccess=false
 Page({
   data: {
     userID: '',
     password: ''
   },

   // 获取输入账号
   numberInput: function(e) {
     this.setData({
       userID: e.detail.value,

     })
   },

   // 获取输入密码
   passwordInput: function(e) {
     this.setData({
       password: e.detail.value,

     })
   },

   // 登录
   login: function() {
    //  var self = this;
    //  if (this.data.userID.length == 0) {
    //    wx.showToast({
    //      title: '用户名不能为空!',
    //      icon: 'none',
    //      duration: 2000,
    //    })
    //  } else if (this.data.password.length == 0) {
    //    wx.showToast({
    //      title: '密码不能为空!',
    //      icon: 'none',
    //      duration: 2000,
    //    })
    //  } else {
      //  var that = this
      //  wx.request({
      //    url: 'http://127.0.0.1:8080/schoolsell/login',
      //    data: {
      //      "userID": this.data.userID,
      //      "password": this.data.password
      //    },
      //    method: 'GET',
      //    success: function(res) {
      //      App.appData.userinfo = {
      //        userID: self.data.userID
      //      };
      //      var result = res.data.result;
      //      var totalText = result;
      //      //  isLoginSuccess=true
      //      if (result == "密码正确") {
      //        that.myWebsocketConnect();
      //        wx.switchTab({
      //          url: '../first/first',
      //        })
      //      } else {
      //        wx.showToast({
      //          title: totalText,
      //          icon: 'none',
      //          duration: 2000,
      //        })
      //      }

      //    }
      //  })
      wx.navigateTo({
        url: '../loginOrdinary/loginOrdinary',
      })


     },
  
   register: function() {
     wx.navigateTo

     ({
       url: '../register/register',
     })
   },
   myWebsocketConnect: function() {
     wx.request({
       url: 'http://127.0.0.1:8080/schoolsell/logingetmsgnum?userid=' + this.data.userID,
       header: {
         "Content-Type": "application/json"
       },
       success: function(res) {
         App.globalData.msgnumber = res.data.amount;
         console.log("res.data为" + res.data.amount);
         console.log("到了这里msgnumber的数量为" + App.globalData.msgnumber);
         //var n = this.globalData.msgnumber;
         //console.log("n的值为" + n);
         wx.setTabBarBadge({
           index: 1,
           text: res.data.amount.toString(),
         })
       }
     })

     //连接服务器websocket
     wx.connectSocket({
       url: 'ws://127.0.0.1:8080/schoolsell/websocket/' + this.data.userID,
       method: "GET",
       success: function(res) {
         //成功时将isOpen变为true
         App.globalData.isOpen = true;
       }
     })
     wx.onSocketOpen(function(res) {
       console.log('WebSocket连接已打开！')
     })
     wx.onSocketError(function(res) {
       console.log('WebSocket连接打开失败，请检查！')
     })
     wx.onSocketClose(function() {
       App.globalData.isOpen = false;
       console.log("连接已经关闭");
     })
     wx.onSocketMessage(function(res) {
       var rd = JSON.parse(res.data);
       App.globalData.msgnumber = App.globalData.msgnumber + 1;
       App.globalData.iscanrefresh = true;
       if (!App.globalData.isfirst) {
         //当消息列表的onload没有执行时不把新消息存入App.globalData.freshnewsList
       } else {
         //当消息列表的onload执行过后，则开始发新接收的消息存入App.globalData.freshnewsList
         var freshtemplist = App.globalData.freshnewsList;
         var isfreshexist = false;
         if (freshtemplist) {
           for (var i = 0; i < freshtemplist.length; i++) {
             if (rd.sendid == freshtemplist[i].name) {
               freshtemplist[i].onechatlist.push(rd);
               isfreshexist = true;
             }
           }
         };
         if (!isfreshexist) {
           var newonelist = [];
           newonelist.push(rd);
           var tempmap = {
             "name": rd.sendid,
             "onechatlist": newonelist
           }
           freshtemplist.push(tempmap);
           console.log("执行到了这里freshtemplistd的值为" + freshtemplist);
         }
         App.globalData.freshnewsList = freshtemplist;

         //存入给消息列表的全局新消息tomsglistfreshnews
         var tomsglistfreshtemplist = App.globalData.tomsglistfreshnews;
         var isfreshexist = false;
         if (tomsglistfreshtemplist) {
           for (var i = 0; i < tomsglistfreshtemplist.length; i++) {
             if (rd.sendid == tomsglistfreshtemplist[i].name) {
               tomsglistfreshtemplist[i].onechatlist.push(rd);
               isfreshexist = true;
             }
           }
         };
         if (!isfreshexist) {
           var newonelist = [];
           newonelist.push(rd);
           var tempmap = {
             "name": rd.sendid,
             "onechatlist": newonelist
           }
           tomsglistfreshtemplist.push(tempmap);
           //console.log("执行到了这里freshtemplistd的值为" + freshtemplist);
         }

         App.globalData.tomsglistfreshnews = tomsglistfreshtemplist;
       }

       wx.setTabBarBadge({
         index: 1,
         text: App.globalData.msgnumber.toString(),
       });
       console.log("在login收到信息")
     })
   },

   doLogin: function(e) {
     var that = this
     console.log(e.detail.errMsg)
     console.log(e.detail.userInfo)
     console.log(e.detail.rawData)
     wx.login({
       success: function(res) {
         console.log(res)
         //获取登录的临时凭证
         var code = res.code;
         //调用后端，获取微信的session_key,secret
         wx.request({
           url: 'http://127.0.0.1:8080/schoolsell/wxLogin?code=' + code,
           method: "POST",
           success: function(result) {
             var resu_temp = JSON.stringify(result.data);
             var result_temp = JSON.parse(resu_temp);
             console.log(result)
             console.log("是否存在此用户" + result_temp);
             if (result_temp.isExist == "no") {
               //注册用户信息
               that.userRegister(e.detail.userInfo);
               console.log("注册用户信息")
             } else if (result_temp.isExist == "yes") {
                console.log("已存在此用户!!!")
             }
              //保存用户ID进app.js
             App.appData.userinfo = {
               userID: result_temp.userID,
             };
             that.setData({
              userID:result_temp.userID,
             });
             console.log("用户ID: "+result_temp.userID);
              
             //保存用户信息到本地缓存
             App.setGlobalUserInfo(e.detail.userInfo);
             console.log(e.detail.userInfo)
              //将本地缓存中的isOrdinary改为0
              wx.setStorageSync("isOrdinary", 0);
             that.myWebsocketConnect();
             wx.switchTab({
               url: '../first/first',
             })

           }
         })

       }
     })

   },
   userRegister: function(userInfo) {
     var filePath = userInfo.avatarUrl
     var gender = userInfo.gender
     var userName = userInfo.nickName
     console.log("注册时用户微信号为： "+userName);

     wx.request({
       url: 'http://127.0.0.1:8080/schoolsell/userRegister?filePath=' + filePath + '&gender=' + gender + '&userName=' + userName,
       method: "GET",
       success: function(res) {
         if (res.data.result == "yes") {
           console.log("注册成功!!!");
         }
       }
     })


     //  wx.uploadFile({
     //    url: "http://127.0.0.1:8080/schoolsell/userRegister",
     //    filePath: userInfo.avataUrl,
     //    name: 'file',
     //    formData: {
     //      'gender': userInfo.gender,
     //      'userName': userInfo.nickName,
     //      'filePath': userInfo.avataUrl,
     //    },
     //    header: {
     //      "Content-Type": "multipart/form-data"
     //    },
     //    success: function(res) {
     //      var result = res.data.result
     //      console.log("注册是否成功? " + res.data.result)
     //    },
     //    fail: function(res) {
     //      wx.showModal({
     //        title: '错误提示',
     //        content: '后台信息注册错误!!!',
     //        showCancel: false,
     //        success: function(res) {}
     //      })
     //    }
     //  });
   }


 })