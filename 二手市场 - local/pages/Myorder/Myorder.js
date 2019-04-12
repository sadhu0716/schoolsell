// pages/Myorder/Myorder.js
var App = getApp();
var currentcount = 0
var order
var util = require("../../utils/util.js");
var sendgoods = 0;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentData: '0',
    ordermsg: [],
    textbarname2: '待付款',
    textbarname3: '待收货',
    textbarname4: '待发货',
    textbarname5: '待评论',
    textname1: '待付款',
    textname2: '待收货',
    textname3: '待发货',
    textname4: '待评论',
    orderID: "订单编号:",
    orderdate: '订单日期：',
    address: "收货地址：",
    sellername: '卖家昵称：',
    buyername: "买家昵称：",
    buyerphone: '买家电话：',
    sellerphone: "卖家电话：",
    textname0: '已评论',
    okbtn1: '确认付款',
    okbtn2: '确认收货',
    okbtn3: '确认发货',
    okbtn4: "评论",
    okbtn5: "举报",
    hassuccess: '0', //接收是否评论提交成功
    showView: true, //点击事件后隐藏确认发货按钮，默认显示
    daifukuanorder: [{
      commoditymsg: []
    }], //待付款
    daishouhuoorder: [{
      commoditymsg: []
    }], //待收货
    daifahuoorder: [{
      commoditymsg: []
    }], //待发货
    daipinglunorder: [], //待评论
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that=this
    this.setData({
      userID: App.appData.userinfo.userID
    });
    ShoeView: (that.data.ShoeView == "true" ? true : false)
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
      url: 'http://127.0.0.1:8080/schoolsell/getOrderByBuyerID',
      method: 'GET',
      data: {
        "buyerID": that.data.userID,
      },
      success: function(res) {
        order = JSON.stringify(res.data.orderresult)
        var ordercommodity = JSON.stringify(res.data.oredercommodity)
        that.data.ordergoods = JSON.parse(ordercommodity)
        that.data.ordermsg = JSON.parse(order)
        // console.log(that.data.ordermsg)
        for (var i = 0; i < that.data.ordermsg.length; i++) {
          // 待付款
          if (that.data.ordermsg[i].state == -1) {
            var Ordernumber = 'daifukuanorder[' + i + '].orderID'
            var Orderdate = 'daifukuanorder[' + i + '].orderdate'
            var address = 'daifukuanorder[' + i + '].address'
            var buyerName = 'daifukuanorder[' + i + '].buyerName'
            var sellerName = 'daifukuanorder[' + i + '].sellerName'
            var buyerPhone = 'daifukuanorder[' + i + '].buyerPhone'
            var sellerPhone = 'daifukuanorder[' + i + '].sellerPhone'

            that.setData({
              [Ordernumber]: that.data.ordermsg[i].orderid,
              [Orderdate]: util.datetimeFormat_1(that.data.ordermsg[i].orderdate),
              [address]: that.data.ordermsg[i].address,
              [buyerName]: that.data.ordermsg[i].buyername,
              [sellerName]: that.data.ordermsg[i].sellername,
              [buyerPhone]: that.data.ordermsg[i].buyerphone,
              [sellerPhone]: that.data.ordermsg[i].sellerphone

            })
            for (var j = 0; j < that.data.ordergoods[i].length; j++) {
              var price = 'daifukuanorder[' + i + '].commoditymsg[' + j + '].Cprice'
              var cname = 'daifukuanorder[' + i + '].commoditymsg[' + j + '].Cname'
              var count = 'daifukuanorder[' + i + '].commoditymsg[' + j + '].count'
              var cid = 'daifukuanorder[' + i + '].commoditymsg[' + j + '].cid'
              that.setData({
                [price]: that.data.ordergoods[i][j].cprice,
                [cname]: that.data.ordergoods[i][j].cname,
                [count]: that.data.ordergoods[i][j].amount,
                [cid]: that.data.ordergoods[i][j].cid,
              })
            }
          }
          // 待发货 
          else if (that.data.ordermsg[i].state == 0 || that.data.ordermsg[i].state == 4) {
            if (that.data.ordermsg[i].state == 0) {
              that.setData({
                textname3: '待发货',
                showView: (that.data.showView) 
              })
            } else if (that.data.ordermsg[i].state == 4){
              that.setData({
                textname3: '已发货',
                showView: (!that.data.showView) 
              })
            }
            var Ordernumber = 'daifahuoorder[' + i + '].orderID'
            var Orderdate = 'daifahuoorder[' + i + '].orderdate'
            var address = 'daifahuoorder[' + i + '].address'
            var buyerName = 'daifahuoorder[' + i + '].buyerName'
            var sellerName = 'daifahuoorder[' + i + '].sellerName'
            var buyerPhone = 'daifahuoorder[' + i + '].buyerPhone'
            var sellerPhone = 'daifahuoorder[' + i + '].sellerPhone'
            that.setData({
              [Ordernumber]: that.data.ordermsg[i].orderid,
              [Orderdate]: util.datetimeFormat_1(that.data.ordermsg[i].orderdate),
              [address]: that.data.ordermsg[i].address,
              [buyerName]: that.data.ordermsg[i].buyername,
              [sellerName]: that.data.ordermsg[i].sellername,
              [buyerPhone]: that.data.ordermsg[i].buyerphone,
              [sellerPhone]: that.data.ordermsg[i].sellerphone

            })
            for (var j = 0; j < that.data.ordergoods[i].length; j++) {
              if ((that.data.ordergoods[i][j].orderid) == that.data.ordermsg[i].orderid) {
                var price = 'daifahuoorder[' + i + '].commoditymsg[' + j + '].Cprice'
                var cname = 'daifahuoorder[' + i + '].commoditymsg[' + j + '].Cname'
                var count = 'daifahuoorder[' + i + '].commoditymsg[' + j + '].count'
                var cid = 'daifahuoorder[' + i + '].commoditymsg[' + j + '].cid'
                that.setData({
                  [price]: that.data.ordergoods[i][j].cprice,
                  [cname]: that.data.ordergoods[i][j].cname,
                  [count]: that.data.ordergoods[i][j].amount,
                  [cid]: that.data.ordergoods[i][j].cid,
                })

              }
            }
          }
          //待收货
          else if (that.data.ordermsg[i].state == 1 ) {
            var Ordernumber = 'daishouhuoorder[' + i + '].orderID'
            var Orderdate = 'daishouhuoorder[' + i + '].orderdate'
            var address = 'daishouhuoorder[' + i + '].address'
            var buyerName = 'daishouhuoorder[' + i + '].buyerName'
            var sellerName = 'daishouhuoorder[' + i + '].sellerName'
            var buyerPhone = 'daishouhuoorder[' + i + '].buyerPhone'
            var sellerPhone = 'daishouhuoorder[' + i + '].sellerPhone'
            that.setData({
              [Ordernumber]: that.data.ordermsg[i].orderid,
              [Orderdate]: util.datetimeFormat_1(that.data.ordermsg[i].orderdate),
              [address]: that.data.ordermsg[i].address,
              [buyerName]: that.data.ordermsg[i].buyername,
              [sellerName]: that.data.ordermsg[i].sellername,
              [buyerPhone]: that.data.ordermsg[i].buyerphone,
              [sellerPhone]: that.data.ordermsg[i].sellerphone

            })
            for (var j = 0; j < that.data.ordergoods[i].length; j++) {

              if ((that.data.ordergoods[i][j].orderid) == that.data.ordermsg[i].orderid) {
                var price = 'daishouhuoorder[' + i + '].commoditymsg[' + j + '].Cprice'
                var cname = 'daishouhuoorder[' + i + '].commoditymsg[' + j + '].Cname'
                var count = 'daishouhuoorder[' + i + '].commoditymsg[' + j + '].count'
                var cid = 'daishouhuoorder[' + i + '].commoditymsg[' + j + '].cid'
                that.setData({
                  [price]: that.data.ordergoods[i][j].cprice,
                  [cname]: that.data.ordergoods[i][j].cname,
                  [count]: that.data.ordergoods[i][j].amount,
                  [cid]: that.data.ordergoods[i][j].cid,
                })
              }
            }
          }
          //待评论
          else if (that.data.ordermsg[i].state == 2) {
            var Ordernumber = 'daipinglunorder[' + i + '].orderID'
            var Orderdate = 'daipinglunorder[' + i + '].orderdate'
            var address = 'daipinglunorder[' + i + '].address'
            var buyerName = 'daipinglunorder[' + i + '].buyerName'
            var sellerName = 'daipinglunorder[' + i + '].sellerName'
            var buyerPhone = 'daipinglunorder[' + i + '].buyerPhone'
            var sellerPhone = 'daipinglunorder[' + i + '].sellerPhone'
            var sellerid = 'daipinglunorder[' + i + '].sellerid'
            that.setData({
              [Ordernumber]: that.data.ordermsg[i].orderid,
              [Orderdate]: util.datetimeFormat_1(that.data.ordermsg[i].orderdate),
              [address]: that.data.ordermsg[i].address,
              [buyerName]: that.data.ordermsg[i].buyername,
              [sellerName]: that.data.ordermsg[i].sellername,
              [buyerPhone]: that.data.ordermsg[i].buyerphone,
              [sellerPhone]: that.data.ordermsg[i].sellerphone,
              [sellerid]: that.data.ordermsg[i].sellerid,

            })
            for (var j = 0; j < that.data.ordergoods[i].length; j++) {
              if ((that.data.ordergoods[i][j].orderid) == that.data.ordermsg[i].orderid) {

                var price = 'daipinglunorder[' + i + '].commoditymsg[' + j + '].Cprice'
                var cname = 'daipinglunorder[' + i + '].commoditymsg[' + j + '].Cname'
                var count = 'daipinglunorder[' + i + '].commoditymsg[' + j + '].count'
                var cid = 'daipinglunorder[' + i + '].commoditymsg[' + j + '].cid'
                var buyerid = 'daipinglunorder[' + i + '].commoditymsg[' + j + '].buyerid'
                var ocid = 'daipinglunorder[' + i + '].commoditymsg[' + j + '].ocid'
                that.setData({
                  [price]: that.data.ordergoods[i][j].cprice,
                  [cname]: that.data.ordergoods[i][j].cname,
                  [count]: that.data.ordergoods[i][j].amount,
                  [cid]: that.data.ordergoods[i][j].cid,
                  [buyerid]: that.data.ordermsg[i].buyerid,
                  [ocid]: that.data.ordergoods[i][j].ocid,
                })

              }
            }
          } //全部
          else if (that.data.ordermsg[i].state == 3) {
            var Ordernumber = 'alloreder[' + i + '].orderID'
            var Orderdate = 'alloreder[' + i + '].orderdate'
            var address = 'alloreder[' + i + '].address'
            var buyerName = 'alloreder[' + i + '].buyerName'
            var sellerName = 'alloreder[' + i + '].sellerName'
            var buyerPhone = 'alloreder[' + i + '].buyerPhone'
            var sellerPhone = 'alloreder[' + i + '].sellerPhone'
            that.setData({
              [Ordernumber]: that.data.ordermsg[i].orderid,
              [Orderdate]: util.datetimeFormat_1(that.data.ordermsg[i].orderdate),
              [address]: that.data.ordermsg[i].address,
              [buyerName]: that.data.ordermsg[i].buyername,
              [sellerName]: that.data.ordermsg[i].sellername,
              [buyerPhone]: that.data.ordermsg[i].buyerphone,
              [sellerPhone]: that.data.ordermsg[i].sellerphone,
            })
            for (var j = 0; j < that.data.ordergoods[i].length; j++) {
              if ((that.data.ordergoods[i][j].orderid) == that.data.ordermsg[i].orderid) {
                var price = 'alloreder[' + i + '].commoditymsg[' + j + '].Cprice'
                var cname = 'alloreder[' + i + '].commoditymsg[' + j + '].Cname'
                var count = 'alloreder[' + i + '].commoditymsg[' + j + '].count'
                var cid = 'alloreder[' + i + '].commoditymsg[' + j + '].cid'
                that.setData({
                  [price]: that.data.ordergoods[i][j].cprice,
                  [cname]: that.data.ordergoods[i][j].cname,
                  [count]: that.data.ordergoods[i][j].amount,
                  [cid]: that.data.ordergoods[i][j].cid,

                })

              }
            }
          }
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
    // wx.stopPullDownRefresh()
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
  bindchange: function(e) {
    const that = this;
    that.setData({
      currentData: e.detail.current
    })
  },
  clickchang: function(e) {
    const that = this
    if (that.data.currentData === e.target.dataset.current) {

      return false;
    } else {
      that.setData({
        currentData: e.target.dataset.current
      })
    }

  },
  //举报
  toreport: function(e) {
    var msg = e.currentTarget.dataset

    console.log(msg)
    wx.navigateTo({
      url: '../report/report?cid=' + msg.id + '&buyerid=' + msg.buyerid + '&cname=' + msg.cname + '&ocid=' + msg.ocid,


    })
  },
  //评论
  tocomment: function(e) {
    var msg = e.currentTarget.dataset
    console.log(msg)
    wx.navigateTo({
      url: '../comment/comment?sellerid=' + msg.id + '&orderid=' + msg.orderid,
    })
  },
  // 确认收货
  totakegoods: function(e) {
    var that = this
    var orderid = e.currentTarget.dataset.orderid
    console.log("待收货的订单编号为：" + orderid)
    wx.showModal({
      title: '确认收货',
      success: function(res) {
        if (res.confirm) {
          console.log("用户点击确定")
          wx.request({
            url: 'http://127.0.0.1:8080/schoolsell/totakegoods',
            method: 'GET',
            data: {
              'orderid': orderid
            },
            success: function(res) {
              if (res.data == 1) {
                wx.redirectTo({
                  url: '../Myorder/Myorder',
                })
              }
            }
          })
        } else if (res.cancel) {
          console.log("用户点击了取消")
        }
      }
    })

  },
  // 确认发货
  tosendgoods: function(e) {
    var orderid = e.currentTarget.dataset.orderid
    var that = this
    wx.showModal({
      title: '是否确认发货',
      success: function(res) {
       wx.request({
         url: 'http://127.0.0.1:8080/schoolsell/tosendgoods',
         method:'GET',
         data:{
           'orderid':orderid
         },
         success:function(res){
           console.log("确认发货返回值为："+res.data.result)
           if(res.data.result==4){
             wx.redirectTo({
               url: '../Myorder/Myorder',
             })
           }
         }
       })
      }
    })
  }
})