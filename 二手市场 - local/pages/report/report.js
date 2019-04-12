// pages/report/report.js
var i = 0
var submit = 0
Page({

  /**
   * 页面的初始数据
   */
  data: {
    files: [], //存放图片
    goodsName: '', //商品名称
    inputContent: '',
    cid: '', //商品id
    inputContent: '' //举报内容
  },
  // 把文本域的值放在了 inputContent
  textInput: function(e) {
    this.setData({
      inputContent: e.detail.value
    })
  },

  // 点击提交按钮上传服务器文本域内容
  submit: function(e) {
    var that = this
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/reportmsg',
      method: 'GET',
      data: {
        'despription': that.data.inputContent,
        'ocid': that.data.ocid,
        'jubaouserid': that.data.buyerid
      },
      success: function(res) {

        if (res.data == 1) {
          console.log("提交成功")
          submit = 1
          wx.showToast({
            title: '提交成功',
          })
          that.setData({
            form_info: '',
          })
        } else {
          wx.showToast({
            title: '提交失败',
          })
        }
      },
      complete: function (res) {
        if (submit == 1) {
          that.releaseDetail();
        }
        i = 0;
      }
    })
  },
  releaseDetail: function(res) {
    var that = this
    wx.uploadFile({
      url: 'http://127.0.0.1:8080/schoolsell/schoolsell-war/jubaoDetial',
      filePath: that.data.Detail[i],
      name: 'file',
      success: function(res) {
        console.log("第" + (i + 1) + "张图片插入成功")
      },
      fail: function(res) {
        console.log("第" + (i + 1) + "张图片插入失败")
      },
      complete: function(res) {
        i++;
        if (that.data.Detail.length == i) {
          console.log("图片上传完成")
          that.setData({
            Detail: []
          })
        } else if (i > that.data.Detail.length) {
          console.log('错误执行完毕')
        } else {
          that.releaseDetail();
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

    this.setData({
      goodsName: options.cname,
      cid: options.cid,
      buyerid: options.buyerid,
      ocid: options.ocid
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
  chooseImage1: function(e) {
    var that = this;
    wx.chooseImage({
      count: 4,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        that.setData({
          Detail: that.data.files.concat(res.tempFilePaths)
        });
      }
    })
  },
  previewImage1: function(e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.Detail // 需要预览的图片http链接列表
    })
  },
})