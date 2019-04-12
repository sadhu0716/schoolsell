var ifLoadMore = null;
var page = 1; //默认第一页
var CurrentCount = 0;
var sectionData = []
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab: 0,
    inputShowed: false,
    inputVal: "", //搜索框输入值
    bidKindID: '', //大类ID
    navbars: [], //轮播图
    indicatorDots: true, //是否显示面板指示点
    autoplay: true, //是否自动切换
    interval: 3000, //自动切换时间间隔,3s
    duration: 1000, //  滑动动画时长1s
    hidden: false, //隐藏加载更多控件,true为隐藏
    isLoading: false, //是否正在加载请求数据，防止一次上拉加载请求多次request
    // navItems
    menus: [],
    // 新品上架
    newGoods: [],
    price: '',
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
    var that = this;
    wx.request({ //类别请求
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/first',
      method: 'GET',
      success: function(res) {
        var kindmsg = JSON.stringify(res.data) //stringify: json->字符串  parse:字符串->json
        that.data.bigname = JSON.parse(kindmsg)
        for (var i = 0; i < that.data.bigname.length; i++) {
          var menusdata = 'menus[' + i + '].name'
          var ID = 'menus[' + i + '].bigkindId'
          that.setData({
            [menusdata]: that.data.bigname[i].bigkindname,
            [ID]: that.data.bigname[i].bigkindid
          })
        }
      }
    })

    wx.request({ //商品信息，缩略图的cid和name,price请求,图片由路径+cid获取
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/firstpic',
      method: "GET",
      data: {},
      success: function(res) {
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

    wx.request({ //请求轮播图
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/headline',
      method: "GET",
      success: function(res) {
        var image = JSON.stringify(res.data.result)
        console.log(image)
        that.data.hendpic = JSON.parse(image)
        //  console.log(that.data.hendpic.result[2])
        //  console.log(that.data.hendpic)
        //  var j=0
        for (var i = 0; i < 6; i = i + 2) {
          // j=j+1;
          var picture = 'navbars[' + i + '].url'
          var cID = 'navbars[' + i + '].cID'
          that.setData({
            [picture]: that.data.hendpic.result[i],
            [cID]: that.data.hendpic.result[i + 1]
          })
          //  console.log(that.data.pic[4].picture[0])
        }
      }
    })

  },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {
    wx.request({            //当首页进入其他页面或者在底部切换时，将请求的首页商品数量清零，即刷新
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/leaveFirst',
      method:"GET",
      success:function(res){
          console.log("离开首页是否清零:"+res.data.result)
      }
    })
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
  imageLoad: function(res) {

  },
  clicktoclassifyGoods: function(e) {
    var id = e.currentTarget.id
    wx.navigateTo({
      url: '../classifyGoods/classifyGoods?kindID=' + id,
    })
  },

  imageLoad: function(e) {
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
  loadMore: function(e) { //分页加载
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
    setTimeout(function() {
      that.getRequest(that);
    }, 1000);
  },
  getRequest: function(e) {
    var that = e
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/firstpic',
      method: "GET",
      data: {},
      success: function(res) {
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
        if (count == 0) {
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
  todetail: function(e) {
    var cid = e.currentTarget.id
    console.log("cid:" + cid)
    wx.navigateTo({
      url: '../details/details?cid=' + cid,
    })
  },

})