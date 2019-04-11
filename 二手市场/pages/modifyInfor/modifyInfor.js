// pages/modifyInfor/modifyInfor.js
Page({

  /**
   * 页面的初始数据
   */
  data: { 
    name:'',
    testData:null,
    newdata:'',
    userID:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.testData = JSON.parse(options.extra);
     this.setData({
       name:this.data.testData.name,
       userID:this.data.testData.userID,
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
  
  valueinput: function (e) {
    this.setData({
      newdata: e.detail.value
    })
   
  },
  
  OKclick: function () {
    if (this.data.newdata.length==0){
      wx.showToast({
        title: '请填写新的信息!',
        icon: 'none',
        duration: 2000,
      })
    }
     else{
        wx.request({
          url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/modifyInfor',
          method:'GET',
          data:{
            "userID": this.data.testData.userID,
            "mark": this.data.testData.name,
            "newmsg":this.data.newdata
          },
          success: function (res) {
           var result= res.data.result
           if(result==1){
             wx.redirectTo({
               url: '../profile/profile',
             })
           }else if(result==-1){
             wx.showToast({
               title: '电话号码已存在',
               icon: 'none',
               duration: 2000,
             })
           }else{
             wx.showToast({
               title: '修改失败',
               icon: 'none',
               duration: 2000,
             })
           }
          }
        })
    
  }
  }
})