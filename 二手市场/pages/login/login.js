 var  App =getApp();
//  var isLoginSuccess=false
Page({
  data: {
    userID:'',
    password:''
  },

  // 获取输入账号
  numberInput: function (e) {
    this.setData({
      userID:e.detail.value,
      
    })
  },

  // 获取输入密码
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value,
     
    })
  },

  // 登录
  login:function () {
    var self=this;
     if (this.data.userID.length == 0 ) {
      wx.showToast({
        title: '用户名不能为空!',
        icon: 'none',
        duration: 2000,
      })
    } 
    else if (this.data.password.length == 0) {
      wx.showToast({
        title: '密码不能为空!',
        icon: 'none',
        duration: 2000,
      })
    } 
    else {
       
      var that=this
       wx.request({
         url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/login',
         data: { 
           "userID":this.data.userID,
           "password":this.data.password
         },
         method: 'GET',
         success: function (res) {
           App.appData.userinfo = { userID: self.data.userID };
           var result=res.data.result;
           var totalText = result;
          //  isLoginSuccess=true
           if(result=="密码正确"){
            that.myWebsocketConnect();
             wx.switchTab({
               url: '../first/first',
             })
           }else{
             wx.showToast({
               title:totalText,
                 icon: 'none',
                   duration: 2000,
             })
           }
           
         }
       })
      

    }

    


  },
  register: function(){
      wx.navigateTo
       
      ({
        url: '../register/register',
      })
  },
  myWebsocketConnect:function(){
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/logingetmsgnum?userid=' + this.data.userID,
      header: {
        "Content-Type": "application/json"
      },
      success: function (res) {
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
      url: 'wss://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/websocket/' + this.data.userID,
      method: "GET",
      success: function (res) {
        //成功时将isOpen变为true
        App.globalData.isOpen = true;
      }
    })
    wx.onSocketOpen(function (res) {
      console.log('WebSocket连接已打开！')
    })
    wx.onSocketError(function (res) {
      console.log('WebSocket连接打开失败，请检查！')
    })
    wx.onSocketClose(function () {
      App.globalData.isOpen = false;
      console.log("连接已经关闭");
    })
    wx.onSocketMessage(function (res) {
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
          var tempmap = { "name": rd.sendid, "onechatlist": newonelist }
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
          var tempmap = { "name": rd.sendid, "onechatlist": newonelist }
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
  
 
})
