var App = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

    userID: '', //userID
    userName: '昵称', //微信号
    gender: '', //性别
    picURL: '', //头像
    // username: '',
    // realname: '',
    // phonename: '电话号码',
    // phone: '',
    // IDnumber: '',
    // adderssname: '地址',
    // adderss: '',
    // wxNumber: '',
    // picURL: '',
    testchangeData: null,

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      userID: App.appData.userinfo.userID
    });

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */



  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this
    console.log("userID:" + that.data.userID)
    //从缓存获取用户信息
    var userInfo = wx.getStorageSync("userInfo");

    console.log("用户信息: "+userInfo)

    var get_gender=userInfo.gender
    var get_userName=userInfo.nickName
    var get_picURL = userInfo.avatarUrl

    console.log("微信头像地址为: "+get_picURL)

    if(get_gender==1){
      that.setData({
        gender:'男'
      })
    }else{
      that.setData({
        gender:'女'
      })
    }
    that.setData({
      picURL:get_picURL,
      userName:get_userName,
    })

    // wx.request({ //获取用户信息
    //   url: 'http://127.0.0.1:8080/schoolsell/profilemsg',
    //   method: 'GET',
    //   data: {
    //     "userID": this.data.userID
    //   },
    //   success: function(res) {
    //     var msg = JSON.stringify(res.data);
    //     that.data.peoplemsg = JSON.parse(msg)

    //     that.setData({
    //       username: that.data.peoplemsg.username,
    //       realname: that.data.peoplemsg.realname,
    //       phone: that.data.peoplemsg.phonenumber,
    //       IDnumber: that.data.peoplemsg.idnumber,
    //       adderss: that.data.peoplemsg.address,

    //     })

    //   }
    // })

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
  // nickchange: function() {
  //   var item = {
  //     name: this.data.nick,
  //     userID: this.data.userID,

  //   }
  //   wx.redirectTo({
  //     url: '../modifyInfor/modifyInfor?extra=' + JSON.stringify(item)
  //   })
  // },
  // phonechange: function() {
  //   var item = {
  //     name: this.data.phonename,
  //     userID: this.data.userID,
  //   }
  //   wx.redirectTo({
  //     url: '../modifyInfor/modifyInfor?extra=' + JSON.stringify(item)
  //   })
  // },
  // addersschange: function() {
  //   var item = {
  //     name: this.data.adderssname,
  //     userID: this.data.userID,
  //   }
  //   wx.redirectTo({
  //     url: '../modifyInfor/modifyInfor?extra=' + JSON.stringify(item)
  //   })
  // }

})