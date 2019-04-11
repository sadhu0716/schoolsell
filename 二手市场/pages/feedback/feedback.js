// pages/feedback/feedback.js
var App = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    suggest:'',
    sendInfo: '',
    userID:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({ userID: App.appData.userinfo.userID });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  getsuggest:function(e){
    this.setData({
      suggest: e.detail.value
    })
  },
  registerBtn: function () {
    if (this.data.suggest.length == 0) {
      wx.showToast({
        title: '请填写您对我们的建议或意见',
        icon: 'none',
        duration: 2000,
      })
    } else {
      var that=this
      wx.request({
        url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/feedback',
        method: 'GET',
        data: {
          "feedbackID": this.data.userID,
          "description": this.data.suggest
        },
        success: function (res) {
          var result=res.data.tip
          if(result=1){
            wx.showToast({
              title: '提交成功',
              icon: 'success',
              duration: 2000,
            })
            that.setData({
              form_info: ''
            })
          }else{
            wx.showToast({
              title: '提交失败',
              icon: 'none',
              duration: 2000,
            })
          }

        }
      })
     
    }
  }
})