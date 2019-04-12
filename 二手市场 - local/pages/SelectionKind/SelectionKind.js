// pages/SelectionKind/SelectionKind.js
var App=getApp()
Page({
  
  data: {
 
    cateItems: [
      { children: [], cate_name:''}
    ],
   
    curNav: 1,
    curIndex: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
       url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/SelectionKind',
       method:"GET",
       data:{
       },
       success:function(res){
         var bigkind=JSON.stringify(res.data.bigkind)
         that.data.bigkindmsg=JSON.parse(bigkind);
         var smallkind = JSON.stringify(res.data.smallkind)
         that.data.smallkindmsg = JSON.parse(smallkind);
        //  console.log(that.data.smallkindmsg)
         for (var i = 0; i < that.data.bigkindmsg.length;i++){
           var bigkind = 'cateItems[' + i + '].cate_name'
           var kindID = 'cateItems[' + i + '].cate_id'
           that.setData({
             [bigkind]: that.data.bigkindmsg[i].bigkindname,
             [kindID]: i + 1,
           })  
          //  console.log("ccc")
            
           for (var j = 0; j < that.data.smallkindmsg.length;j++){
             if (that.data.smallkindmsg[j].bigkindid==(i+1)){
               var smallkind = 'cateItems[' + i + '].children[' + j + '].name'
              //  console.log("sss")
             that.setData({
               [smallkind]: that.data.smallkindmsg[j].smallkindname
             })
              //  c=c+1;
              //  console.log(that.data.smallkindmsg[10].bigKindID)
              //  console.log(c)
            }
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
  switchRightTab: function (e) {
    // 获取item项的id，和数组的下标值  
    let id = e.target.dataset.id,
      index = parseInt(e.target.dataset.index);
    // 把点击到的某一项，设为当前index  
    this.setData({
      curNav: id,
      curIndex: index
    })
  },
  kindclick:function(e){
    var smallkind = e.currentTarget.dataset.text
     console.log(smallkind)
    var pages = getCurrentPages();
    var currPage = pages[pages.length - 1];   //当前页面
    var prevPage = pages[pages.length - 2];  //上一个页面

    //直接调用上一个页面的setData()方法，把数据存到上一个页面中去
    prevPage.setData({
      smallkind: smallkind
    })
    wx.navigateBack();
  }
})