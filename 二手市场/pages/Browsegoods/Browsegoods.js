// pages/Browsegoods/Browsegoods.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    newGoods: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     this.setData({
       kname: options.smallKindname
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
    var that=this
     wx.request({
       url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/browsegoods',
       method:"GET",
       data:{
         "kName":that.data.kname
       },
       success:function(res){
         var goodsmsg=JSON.stringify(res.data)
        that.data.msg= JSON.parse(goodsmsg)
         console.log(that.data.msg)
         for (var i = 0; i < that.data.msg.length;i++){
           var cName ='newGoods['+i+'].name'
           var cID='newGoods['+i+'].cID'
           var price='newGoods['+i+'].price'
           that.setData({
             [cName]:that.data.msg[i].cname,
             [cID]:that.data.msg[i].cid,
             [price]:that.data.msg[i].cprice
           })
         }
        
       }
     })
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
  imageLoad: function (e) {
    // console.log(e.detail.width)
    // console.log(e.detail.height)
    var _this = this;
    var $width = e.detail.width,  //获取图片真实宽度
      $height = e.detail.height,

      ratio = $width / $height;  //图片的真实宽高比例
    var viewWidth = 500 / ratio,      //设置图片显示宽度，
      viewHeight = 380 / ratio;  //计算的高度值
    this.setData({
      imgwidth: viewWidth,
      imgheight: viewHeight
    })
  },
  todetail: function (e) {
    var cid = e.currentTarget.id
    console.log("cid:" + cid)
    wx.navigateTo({
      url: '../details/details?cid=' + cid,
    })
  },

})