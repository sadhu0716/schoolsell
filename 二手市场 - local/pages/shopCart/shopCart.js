// shopCart/shopCart.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    carts: [], // 购物车列表
    hasList: false, // 列表是否有数据
    totalPrice: 0, // 总价，初始为0
    selectAllStatus: false // 全选状态，默认非全选
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

    let self=this;
    
    // wx.request({
    //   url: 'http://localhost:8080/getShopcartInfo?cid='+self.data.cid,
    //   header:{
    //     "Content-Type":"application/json"
    //   },
    //   success:function(e){
    //     carts[]:e.data.
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
    var self=this;
    wx.request({
      url: 'http://localhost:8080/schoolsell/getShopCar?userID=' + app.appData.userinfo.userID,
     
      header: {
        "Content-Type": "application/json"
      },
      success: function (e) {
        var tecarts=[];
        var e2=JSON.stringify(e.data)
        console.log("e.date的值为" + JSON.stringify(e.data.length));
        for(var i=0;i<e.data.length;i++){
          var tempcarts={
            'title': (e.data)[i].title,
            'image': (e.data)[i].image,
            'num': (e.data)[i].num,
            'price': (e.data)[i].price,
            'cid': (e.data)[i].cid
          }
          tecarts.push(tempcarts)
        }
        self.setData({
          carts: tecarts
        })
        console.log("电话号码" + self.data.userphone)
      }

    })
    self.setData({
      //每次转换页面回到购物车 全选按钮置空
      selectAllStatus:false,
      totalPrice: 0,
      hasList: true, // 既然有数据了，那设为true吧
   
      // carts: [{
      //     id: 1,
      //     title: 'java程序设计',
      //     image: '../../images/java.png',
      //     num: 1,
      //     price: 29.9,
      //     selected: false,
      //     sellerid:'122@qq.com'
      //   },
      //   {
      //     id: 2,
      //     title: 'php',
      //     image: '../../images/php.png',
      //     num: 1,
      //     price: 39.9,
      //     selected: false,
      //     sellerid: '122@qq.com'
      //   },
      //   {
      //     id: 3,
      //     title: 'qwer',
      //     image: '../../images/php.png',
      //     num: 1,
      //     price: 49.9,
      //     selected: false,
      //     sellerid: '122@qq.com'
      //   },
      //   {
      //     id: 4,
      //     title: 'asdf',
      //     image: '../../images/php.png',
      //     num: 1,
      //     price: 59.9,
      //     selected: false,
      //     sellerid: '122@qq.com'
      //   },


      // ]
    });
   // this.getTotalPrice();
    
  },




  getTotalPrice() {
    let carts = this.data.carts; // 获取购物车列表
    let total = 0;
    for (let i = 0; i < carts.length; i++) { // 循环列表得到每个数据
      if (carts[i].selected) { // 判断选中才会计算价格
        total += carts[i].num * carts[i].price; // 所有价格加起来
      }
    }
    this.setData({ // 最后赋值到data中渲染到页面
      carts: carts,
      totalPrice: total.toFixed(2)
    });
  },
 
  // 增加数量
  addCount(e) {
    const index = e.currentTarget.dataset.index;
    let carts = this.data.carts;
    let num = carts[index].num;
    num = num + 1;
    carts[index].num = num;
    this.setData({
      carts: carts
    });
    this.getTotalPrice();
  },
  // 减少数量
  minusCount(e) {
    const index = e.currentTarget.dataset.index;
    let carts = this.data.carts;
    let num = carts[index].num;
    if (num <= 1) {
      return false;
    }
    num = num - 1;
    carts[index].num = num;
    this.setData({
      carts: carts
    });
    this.getTotalPrice();
  },



  selectList(e) {
    const index = e.currentTarget.dataset.index; // 获取data- 传进来的index
    let carts = this.data.carts; // 获取购物车列表
    const selected = carts[index].selected; // 获取当前商品的选中状态
    carts[index].selected = !selected; // 改变状态
    this.setData({
      carts: carts
    });
    this.getTotalPrice(); // 重新获取总价
  },
  selectAll(e) {
    let selectAllStatus = this.data.selectAllStatus; // 是否全选状态
    selectAllStatus = !selectAllStatus;
    let carts = this.data.carts;

    for (let i = 0; i < carts.length; i++) {
      carts[i].selected = selectAllStatus; // 改变所有商品状态
    }
    this.setData({
      selectAllStatus: selectAllStatus,
      carts: carts
    });
    this.getTotalPrice(); // 重新获取总价
  },
  
  deleteList(e) {



    const index = e.currentTarget.dataset.index;
    let carts = this.data.carts;
    carts.splice(index, 1); // 删除购物车列表里这个商品
    this.setData({
      carts: carts
    });
    if (!carts.length) { // 如果购物车为空
      this.setData({
        hasList: false // 修改标识为false，显示购物车为空页面
      });
    } else { // 如果不为空
      this.getTotalPrice(); // 重新计算总价格
    }
  },

  //下单传数组给订单页面
  btnBuy: function() {
    let that = this;
    var cartsTemp=[];
    var cartsTempadd=[];
    if (that.data.totalPrice > 0) {
      
      for (var i = 0; i < that.data.carts.length; i++) {
        if (that.data.carts[i].selected) {
          cartsTemp = that.data.carts[i];
          console.log("cartsTemp--"+cartsTemp);
          cartsTempadd.push(cartsTemp);
          // console.log("cartsTempadd----" + cartsTempadd);
        }
        else{
          console.log("当前项没被选")
        }
        
      }
  
      console.log("cartsTempadd*******转型后传值前" + JSON.stringify(cartsTempadd))

      wx.navigateTo({
        url: '../immeBuyByShopcar/immeBuyByShopcar?selectedcarts=' + JSON.stringify(cartsTempadd),
       
       } )
       
    } else {
      wx.showToast({
        title: '你还没有选!',
        icon: 'none'
      })
    }
  
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