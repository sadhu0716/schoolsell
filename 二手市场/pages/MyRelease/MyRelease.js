var App = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sell: [],
    Review:[],
    currentData: 0,
    topbtnname:'编辑',
    topbtnname1:'下架',
    topname1:'出售中',
    topname2:'下架',
    topname3:'审核中',
    userID: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({ userID: App.appData.userinfo.userID });
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
    // wx.request({
    //   url: 'http://127.0.0.1:8080/schoolsell/MyReleasepic',
    //   method:"GET",
    //   data:{
    //   },
    //   success:function(res){}
    // })
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/MyReleasemsg',
      method:"GET",
      data:{
        "userID":this.data.userID,
      },
      success:function(res){
        var MyReleasemsg=JSON.stringify(res.data)
        that.data.Cmsg=JSON.parse(MyReleasemsg)
        console.log(that.data.Cmsg)
        for (var i = 0; i < that.data.Cmsg.length;i++){
          if (that.data.Cmsg[i].ischecked == 1 || that.data.Cmsg[i].isChecked == 2){
            var goodsName = 'sell[' + i + '].Cname'
            var goodsPrice = 'sell[' + i + '].price'
            var goodsCount = 'sell[' + i + '].count'
            var cID='sell['+i+'].cID'
          that.setData({
            [goodsName]: that.data.Cmsg[i].cname,
            [goodsPrice]: that.data.Cmsg[i].cprice,
            [goodsCount]: that.data.Cmsg[i].ccount,
            [cID]: that.data.Cmsg[i].cid,
          })
          } else if (that.data.Cmsg[i].ischecked ==- 1) {
            var Name = 'Review[' + i + '].Cname'
            var Price = 'Review[' + i + '].price'
            var Count = 'Review[' + i + '].count' 
            that.setData({
              [Name]: that.data.Cmsg[i].cname,
              [Price]: that.data.Cmsg[i].cprice,
              [Count]: that.data.Cmsg[i].ccount
            })
        
        }
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
  bindchange: function (e) {
    const that = this;
    that.setData({
      currentData: e.detail.current
    })
  },
  //点击切换，滑块index赋值
  checkCurrent: function (e) {
    const that = this;

    if (that.data.currentData === e.target.dataset.current) {
      return false;
    } else {

      that.setData({
        currentData: e.target.dataset.current
      })
    }
  },
  // 商品下架
  clicktodown:function(e){
      var that=this
    var id = e.currentTarget.id
         wx.showModal({
           title: '提示',
           content: '是否下架该商品',
           success: function (res) {
             if (res.confirm) {
              wx.request({
                url:'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/MyReleaseObtained',
              method:"GET",
              data:{
                   "cID":id
              },
              success:function(res){
                if(res.data.result==1){
                  const index = e.currentTarget.dataset.index;
                  let sell = that.data.sell;
                  sell.splice(index, 1);
                  that.setData({
                    sell: sell
                  })
                  wx.showToast({
                    title: '商品下架成功',
                  })
                }else{
                  wx.showToast({
                    title: '商品下架失败',
                  })
                }
              }
              })
              console.log("点击了确定")
             } else if (res.cancel) {
               console.log("取消")
             }
           }
         })    
  },
  cilcktochang:function(e){
    var cid=e.currentTarget.id
    // console.log(cid)
    wx.navigateTo({
      url: '../modify/modify?cID='+cid,
    })
  }
})