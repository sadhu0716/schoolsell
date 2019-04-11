var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

    cid: 0,
    name: '',
    num: 1,
    price: 22,
    totalPrice: 0,
    image: '../images/java.png',
    newdate: null,
    username: '',
    userphone: '',
    useraddress: '',
    radioItems: [{
        name: 'ONLINE',
        value: '线上支付'
      },
      {
        name: 'OFFLINE',
        value: '线下支付',
        checked: true
      }

    ]
    // 购物车列表
    // hasList: false, // 列表是否有数据
    // totalPrice: 0, // 总价，初始为0
    // selectAllStatus: false ,// 全选状态，默认非全选


  },
  radioChange: function(e) {
    console.log('radioChange:function(e)，携带value值为：', e.detail.name)
  },

  //改变输入框的值时，将输入框的值赋值到self.data中
  bindNamechange: function(e) {
    var self = this;
    self.setData({
      username: e.detail.value
    })
  },
  bindPhonechange: function(e) {
    var self = this;
    self.setData({
      userphone: e.detail.value
    })
  },
  bindAddresschange: function(e) {
    var self = this;
    self.setData({
      useraddress: e.detail.value
    })
  },
  buybtn: function() { //下单
    var self = this;
    wx.requestPayment({
      timeStamp: '',
      nonceStr: '',
      package: '',
      signType: '',
      paySign: '',
      'success': function(res) {
        console.log('success')
      },
      'fail': function(res) {
        console.log('fail')
      }
    });
    // 给服务器提交订单信息，生成待付款订单
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/orderSuccess',
    })
    console.log("self.data数据为" + JSON.stringify(self.data));
  },


  addCount(e) {
    var that = this;
    var numCount = parseInt(that.data.num);

    numCount = numCount + 1;
    that.setData({
      num: numCount
    })
    this.getTotalPrice();
    console.log(numCount)
  },

  // 减少数量
  minusCount(e) {
    let that = this;
    var numCount = that.data.num;

    if (numCount > 1) {
      numCount = numCount - 1;
      that.setData({
        num: numCount
      })
      console.log(numCount)
    } else {
      wx.showToast({
        title: '至少买一个',
        icon: 'none',
      })
    }
    this.getTotalPrice();
  },

  getTotalPrice() {
    var totalPricetemp = this.data.num * this.data.price;
    //console.log("总价：" + totalPricetemp);
    this.setData({
      totalPrice: totalPricetemp.toFixed(2)
    });
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

    var self = this;

    console.log("从商品详情传过来的options值为" + JSON.stringify(options));
    //     this.data.bean = JSON.parse(options.detaildata) 
    // console.log("上个界面传值："+options.detaildata)
    // console.log("全部数据！！"+this.data.bean)
    this.setData({

      name: options.name,
      num: options.num,
      price: options.price,
      image: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/getCommodityThumbnail/' + options.thumbnail,
      cid: options.cid,
    });
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/profilemsg',
      data: {
        "userID": app.appData.userinfo.userID
      },
      method: "GET",
      success: function(e) {
        self.setData({
          username: e.data.username,
          userphone: e.data.phonenumber,
          useraddress: e.data.address
        })
        console.log("电话号码" + self.data.userphone)
      }
    })
    //从购物车传到订单界面的值
    // this.setData({


    //   name:options.cartname,
    //   num: parseInt(options.cartnum),
    //   id:options.cartid,
    //   price:options.cartprice,
    //   image:options.cartimage
    // })
    //因为首次加载不显示总价就在这复制了一段
    var totalPricetemp = this.data.num * this.data.price;
    //console.log("总价：" + totalPricetemp);
    this.setData({
      totalPrice: totalPricetemp.toFixed(2)
    });

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
    // 增加数量

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

  }
})