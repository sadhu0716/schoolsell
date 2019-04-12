// pages/modify/modify.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    OKbtn:'确定',
   

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     this.setData({
       cID: options.cID
     })
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
  inputPrice:function(e){
    this.setData({
      Price:e.detail.value
    })
  },
  inputCount:function(e){
    this.setData({
      Count:e.detail.value
    })
  },
  clicktorelease:function(){
    if (this.data.Price.length==0){
      wx.showToast({
        title: '请填写新的商品价格',
        icon: 'none',
        duration: 2000
      })
    } else if (this.data.Count.length == 0){
      wx.showToast({
        title: '请填写新的商品数量',
        icon: 'none',
        duration: 2000
      })
    }else{
    var that = this
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/modify',
      method:"GET",
      data:{
          "cID":that.data.cID,
          "cPrice":that.data.Price,
          "cCount":that.data.Count
      },
      success:function(res){
         if(res.data.result=="执行成功"){
           wx.showToast({
             title: '修改成功',
           })
           wx.redirectTo({
             url: '../MyRelease/MyRelease',
           })
         }else{
           wx.showToast({
             title: '修改失败',
             icon: 'none',
             duration: 2000
           })
         }
      }
      
    })
    }
  }
})