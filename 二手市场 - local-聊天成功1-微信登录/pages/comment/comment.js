// pages/comment/comment.js
var value = 0
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: [], //存放订单数据
    orderid: '',
    sellerid: '', // 卖家id
    goodscomment: [{
      goodsName: 'java编程', //商品名称

      issuccess: '', //评论是否提交成功，1代表提交成功

      cid: '', //商品id
    }], //评论的订单商品
    userStars: [
      '../images/like.png',
      '../images/like.png',
      '../images/like.png',
      '../images/like.png',
      '../images/like.png'
    ],
  },
  starTap: function(e) {
    var index = e.currentTarget.dataset.index; // 获取当前点击的是第几颗星星
    var tempUserStars = this.data.userStars; // 暂存星星数组
    var len = tempUserStars.length; // 获取星星数组的长度
    value = (index + 1) * 2;
    console.log(value)
    for (var i = 0; i < len; i++) {
      if (i <= index) { // 小于等于index的是满心
        tempUserStars[i] = '../images/liked.png'

      } else { // 其他是空心
        tempUserStars[i] = '../images/like.png'
      }
    }
    // 重新赋值就可以显示了
    this.setData({
      userStars: tempUserStars
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      sellerid: options.sellerid,
      orderid: options.orderid
    })
    console.log(this.data.orderid)
    console.log(this.data.sellerid)
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
    var that = this
    wx.request({
      url: 'http://127.0.0.1:8080/schoolsell/goodscomment',
      method: 'GET',
      data: {
        'orderid': that.data.orderid
      },
      success: function(res) {
        console.log(res.data.ordercommdity)
        for (var i = 0; i < res.data.ordercommdity.length; i++) {
          var goodsname = 'goodscomment[' + i + '].goodsName'
          var cid = 'goodscomment[' + i + '].cid'
          that.setData({
            [goodsname]: res.data.ordercommdity[i].cname,
            [cid]: res.data.ordercommdity[i].cid,
          })
        }
      }
    })

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
    // wx.showModal({
    //   title: '确认取消发布吗？',
    //   // content: '确定要删除该图片？',
    //   showCancel: true, //是否显示取消按钮
    //   cancelText: "否", //默认是“取消”
    //   cancelColor: 'skyblue', //取消文字的颜色
    //   confirmText: "是", //默认是“确定”
    //   confirmColor: 'skyblue', //确定文字的颜色
    //   success: function(res) {
    //     if (res.cancel) {
    //       //点击取消,默认隐藏弹框

    //     } else {

    //     }
    //   },

    // })

    //返回给上个页面评论信息提交成功
    // var pages = getCurrentPages();
    // var currPage = pages[pages.length - 1]; //当前页面
    // var prePage = pages[pages.length - 2]; //上一个页面
    // prePage.setData({
    //   issuccess: this.data.issuccess
    // })
    // console.log("提交成功返回" + this.data.issuccess)
    // wx.navigateBack();
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

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  submit: function(e) {

    //提交评论信息
    var that = this
    wx.request({
      url: 'http://127.0.0.1:8080/schoolsell/comment',
      method: "GET",
      data: {
        "value": value,
        "sellerid": that.data.sellerid
      },
      success: function(res) {
        if (res.data == 1) {
          that.changstate();
          wx.showToast({
            title: '提交成功',
            icon: 'success',
            duration: 2000,
          })
          wx.redirectTo({
            url: '../Myorder/Myorder',
          })
          that.setData({
            issuccess: 1
          })
        }
      }
    })
    
  },
    changstate:function(res){
      var that = this
      wx.request({
        url: 'http://127.0.0.1:8080/schoolsell/commentupdatestate',
        method: 'GET',
        data: {
          'orderid': that.data.orderid
        },
        success: function (res) {
          console.log("订单状态更新返回值：" + res.data)
        }
      })
    }

})