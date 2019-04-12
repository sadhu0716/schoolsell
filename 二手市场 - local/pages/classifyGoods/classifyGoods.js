// pages/classifyGoods/classifyGoods.js
var CurrentCount = 0;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // navItems
    menus: [],
    bigkindID:'',
    newGoods: [],
    hidden: false, //隐藏加载更多控件,true为隐藏
    isLoading: false, //是否正在加载请求数据，防止一次上拉加载请求多次request
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
      this.setData({
        bigkindID: options.kindID
      })
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
    var that = this;
    wx.request({              //小类别导航请求
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/smallkind',
      method: 'GET',
      data:{
        'bigkindID':that.data.bigkindID
      },
      success: function(res) {
        var kindmsg = JSON.stringify(res.data) //stringify: json->字符串  parse:字符串->json
        that.data.smallname = JSON.parse(kindmsg)
        for (var i = 0; i < that.data.smallname.length; i++) {
          var menusdata = 'menus[' + i + '].name'
          var ID = 'menus[' + i + '].bigkindId'
          that.setData({
            [menusdata]: that.data.smallname[i].smallkindname,
            [ID]: that.data.smallname[i].smallkindid
          })
        }
      }
    })

    wx.request({      //商品信息，缩略图的cid和name,price请求,图片由路径+cid获取
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/bigkindAllByID',
      method: "GET",
      data: {
        'bigkindID': that.data.bigkindID
      },
      success: function (res) {
        var firstpic = JSON.stringify(res.data)
        that.data.pic = JSON.parse(firstpic);
        console.log(that.data.pic.length)
        var count = that.data.pic.length
        if (count < 8) {
          that.setData({
            hidden: true,
            isLoading: true
          })
        }
        console.log('第一页商品数量为:' + count)
        CurrentCount = count
        for (var j = 0; j < that.data.pic.length; j++) {
          var goodsmsg = 'newGoods[' + j + '].name'
          var cID = 'newGoods[' + j + '].cID'
          var price = 'newGoods[' + j + '].price'
          that.setData({
            [goodsmsg]: that.data.pic[j].cname,
            [cID]: that.data.pic[j].cid,
            [price]: that.data.pic[j].cprice,
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
  imageLoad: function (e) {
    // console.log(e.detail.width)
    // console.log(e.detail.height)
    var _this = this;
    var $width = e.detail.width, //获取图片真实宽度
      $height = e.detail.height,

      ratio = $width / $height; //图片的真实宽高比例
    var viewWidth = 500 / ratio, //设置图片显示宽度，
      viewHeight = 380 / ratio; //计算的高度值
    this.setData({
      imgwidth: viewWidth,
      imgheight: viewHeight
    })
  },
  loadMore: function (e) { //分页加载
    var hidden = this.data.hidden
    var isLoading = this.data.isLoading
    var that = this
    if (isLoading) { //防止多次request
      return;
    }
    if (hidden) { //在底部显示加载更多控件  
      that.setData({
        hidden: false
      })
      console.log(hidden)
    }

    that.setData({
      isLoading: true
    })
    setTimeout(function () {
      that.getRequest(that);
    }, 1000);
  },
  getRequest: function (e) {
    var that = e
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/bigkindAllByID',
      method: "GET",
      data: {
        'bigkindID':that.data.bigkindID
      },
      success: function (res) {
        var firstpic = JSON.stringify(res.data)
        that.data.pic = JSON.parse(firstpic);
        console.log(that.data.pic.length)
        var count = that.data.pic.length //获取后台传来图片的数量
        if (count > 0 && count < 8) { //最后一页
          for (var j = 0; j < that.data.pic.length; j++) {
            var goodsmsg = 'newGoods[' + (CurrentCount + j) + '].name'
            var cID = 'newGoods[' + (CurrentCount + j) + '].cID'
            var price = 'newGoods[' + (CurrentCount + j) + '].price'
            that.setData({
              [goodsmsg]: that.data.pic[j].cname,
              [cID]: that.data.pic[j].cid,
              [price]: that.data.pic[j].cprice,
            })
          }
          CurrentCount = CurrentCount + count;
          wx.showToast({
            title: '已到底',
            duration: 2000
          })
          that.setData({
            isLoading: true,
            hidden: true
          })
        }
        if (count == 0) {     //恰好总页数为8的倍数
          wx.showToast({
            title: '已到底',
            duration: 2000
          })
          that.setData({
            isLoading: true,
            hidden: true
          })
        }
        if (count == 8) { //中间页
          for (var j = 0; j < that.data.pic.length; j++) {
            var goodsmsg = 'newGoods[' + (CurrentCount + j) + '].name'
            var cID = 'newGoods[' + (CurrentCount + j) + '].cID'
            var price = 'newGoods[' + (CurrentCount + j) + '].price'
            that.setData({
              [goodsmsg]: that.data.pic[j].cname,
              [cID]: that.data.pic[j].cid,
              [price]: that.data.pic[j].cprice,
            })
          }
          CurrentCount = CurrentCount + count;
          that.setData({
            hidden: true,
            isLoading: false
          })
          // setTimeout(function () { //1秒后显示加载更多控件并改变状态
          //   that.setData({
          //     hidden: true,
          //     isLoading: false
          //   })
          // }, 3000)
        }
      }
    })
  },
  todetail: function (e) {
    var cid = e.currentTarget.id
    console.log("cid:" + cid)
    wx.navigateTo({
      url: '../details/details?cid=' + cid,
    })
  },
  cilcktoBrowsedoods: function (e) {
    var kname = e.currentTarget.id
    console.log(kname)
    wx.navigateTo({
      url: '../Browsegoods/Browsegoods?smallKindname=' + kname,
    })
  },

})