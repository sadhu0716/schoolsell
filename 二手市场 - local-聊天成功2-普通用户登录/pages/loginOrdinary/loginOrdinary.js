// pages/loginOrdinary/loginOrdinary.js
var App=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userID: '',

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },
  getID: function(e) {
    this.setData({
      userID: e.detail.value,
    })
  },
  login: function() {
    var that = this;
    var userid = that.data.userID
    wx.request({
      url: 'http://127.0.0.1:8080/schoolsell/loginOrdinary?userID=' + userid,
      method: "GET",
      success: function(res) {
        var result = JSON.stringify(res);
        var resu_temp = JSON.parse(result);
        console.log("普通登录是否存在该用户:" + resu_temp);
        if (resu_temp.data.isExist == "存在") {
          that.myWebsocketConnect();
          var ordinary=resu_temp.data.isOrdinary;
          // App.appData.userinfo = {
          //   isOrdinary: ordinary,
          // };
          // console.log("登录时测试----此用户为普通用户登录:"+App.appData.userinfo.isOrdinary);
          wx.switchTab({
            url: '../first/first',
          })
          //保存用户ID进app.js
          App.appData.userinfo = {
            userID: userid,
          };
          wx.setStorageSync("isOrdinary", ordinary);
          console.log("登录时--获取全局变量isOrdinary:"+App.appData.isOrdinary);
        } else {
          wx.showModal({
            title: '不存在该用户',
            content: '',
          })
        }


      }
    })
  },
  myWebsocketConnect: function () {
    wx.request({
      url: 'http://127.0.0.1:8080/schoolsell/logingetmsgnum?userid=' + this.data.userID,
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
      url: 'ws://127.0.0.1:8080/schoolsell/websocket/' + this.data.userID,
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

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})