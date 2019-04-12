// details/details.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // isLike: true,
    price: 0,
    num: 1,
    name: "",
    sellerid: '',
    cid: 0,
    thumbnail: '',
    bargain: false,
    //商品详细信息描述
    goodsInfo: '',
    userStars: [],
    imgUrls: [],
    indicatorDots: true, //是否显示面板指示点
    autoplay: true, //是否自动切换
    interval: 3000, //自动切换时间间隔,3s
    duration: 1000, //  滑动动画时长1s
    detailImg: []
  },
  //预览图片
  previewImage: function(e) {
    var current = e.target.dataset.src;

    wx.previewImage({
      current: current, // 当前显示图片的http链接  
      urls: this.data.imgUrls // 需要预览的图片http链接列表  
    })
  },
  //联系卖家进入卖家页面
  contactSeller: function(e) {
    var name = e.currentTarget.id;
    wx.navigateTo({
      url: '../chat/chat?name=' + name,
    })
  },


  // 跳到购物车  实际返回上一层购物车界面
  addCar: function(e) {
    var self = this;
    wx.showToast({
      title: '加入购物车成功！！！',
    })
    var shopcartemp = {
      'userid': app.appData.userinfo.userID,
      'cid': self.data.cid,
      'amount': self.data.num
    };
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/addShopCar?userid=' + app.appData.userinfo.userID + '&cid=' + self.data.cid + '&amount=' + self.data.num,
      header: {
        "Content-Type": "application/json"
      },
      success: function(e) {
        console.log(e.data)
      }
    })
    // wx.navigateBack({

    // })
  },
  // 立即购买
  immeBuy: function(e) {

    // var detaildata = JSON.stringify(this.data)
    wx.navigateTo({
      url: '../immeBuy/immeBuy?num=' + this.data.num + '&price=' + this.data.price + '&name=' + this.data.name + '&cid=' + this.data.cid + '&thumbnail=' + this.data.thumbnail,
    })
    console.log("详细页面的值：" + this.data.name)
  },
  // 增加数量
  addCount(e) {
    var that = this;
    var numCount = parseInt(that.data.num);
    numCount = numCount + 1;
    that.setData({
      num: numCount
    })
    console.log(numCount)
  },

  // 减少数量
  minusCount(e) {
    var that = this;
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

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      cid: options.cid
    });
    var that = this;
    that.getPicture();

    // var self=this;
    // this.setData({
    //   /*name: options.name,
    //   price: options.price,
    //    num:options.num,
    //    sellerid:options.sellerid*/
    //   //  imgUrls:options.pic,
    //   // imgUrls[0]:options.
    //   cid:options.cid
    // });
    //   wx.request({
    //     url: 'http://localhost:8080/getCommodityinfo?cid=' + self.data.cid,
    //     header: {
    //       "Content-Type": "application/json"
    //     },
    //     success:function(e){
    //      self.setData({
    //        price: e.data.commodityDetail.cprice,
    //        name: e.data.commodityDetail.cname,
    //        sellerid:  e.data.commodityDetail.sellerid,
    //        imgUrls:   e.data.detailPic,
    //        detailImg: e.data.detailPic,
    //        goodsInfo: e.data.commodityDetail.cdescription,
    //        thumbnail: e.data.commodityDetail.thumbnail,
    //        bargain: e.data.commodityDetail.bargain
    //      })
    //       console.log("bargain的值为" + self.data.bargain)

    //     }
    //   });
    //   self.getPicture();


    // wx.request({
    //   url: 'http://localhost:8080/getStoreCredit?userid=' + self.data.sellerid,
    //   header: {
    //     "Content-Type": "application/json"
    //   },
    //   success:function(e){
    //     var credit=[];
    //     var temp=5;
    //     for(var i=0;i<(e.data.credit)/10;i++){
    //       credit.push('../../images/liked.png');
    //       temp=temp-1
    //     };
    //     for(var i=0;i<temp;i++){
    //       credit.push('../../images/like.png');
    //     }
    //     self.setData({
    //       userStars: credit
    //     })
    //   }
    // })
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
    
    // var self = this;
    // wx.request({
    //   url: 'http://localhost:8080/getCommodityinfo?cid=' + self.data.cid,
    //   header: {
    //     "Content-Type": "application/json"
    //   },
    //   success: function(e) {
    //     self.setData({
    //       price: e.data.commodityDetail.cprice,
    //       name: e.data.commodityDetail.cname,
    //       sellerid: e.data.commodityDetail.sellerid,
    //       imgUrls: e.data.detailPic,
    //       detailImg: e.data.detailPic,
    //       goodsInfo: e.data.commodityDetail.cdescription,
    //       thumbnail: e.data.commodityDetail.thumbnail,
    //       bargain: e.data.commodityDetail.bargain
    //     })
    //     console.log("bargain的值为" + self.data.bargain)
    //   }
    // });
    // self.getPicture();

  },

  bargain: function() {
    let that = this;
    // var name = that.data.name;
    // var price = that.data.price;
    app.globalData.name = that.data.name;
    app.globalData.price = that.data.price;
    wx.navigateTo({
      url: "../bargain/bargain",
    })
    // console.log(app.globalData.name + app.globalData.price)
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
  getPicture:function() {
    var self = this;
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/getCommodityinfo?cid=' + self.data.cid,
      header: {
        "Content-Type": "application/json"
      },
      success: function(e) {
        self.setData({
          price: e.data.commodityDetail.cprice,
          name: e.data.commodityDetail.cname,
          sellerid: e.data.commodityDetail.sellerid,
          imgUrls: e.data.detailPic,
          detailImg: e.data.detailPic,
          goodsInfo: e.data.commodityDetail.cdescription,
          thumbnail: self.data.cid+'.jpg',
          bargain: e.data.commodityDetail.bargain
        })
        console.log(self.data.sellerid);
        self.getSecondPic(self);
        console.log("bargain的值为" + self.data.bargain)
      }
    });
    
    // self.getPicture();
  },
  getSecondPic: function(e) {
    var self=e
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell-war/schoolsell/getStoreCredit?userid=' + self.data.sellerid,
      header: {
        "Content-Type": "application/json"
      },
      success: function(e) {
        var credit = [];
        var temp = 5;     //总共五颗星
        for (var i = 0; i < (e.data.credit) / 10; i++) {
          credit.push('../images/liked.png');       //信誉值每多10分，加一颗星
          temp = temp - 1
        };
        for (var i = 0; i < temp; i++) {
          credit.push('../images/like.png');
        }
        self.setData({
          userStars: credit
        })
      }
    })
  },
})