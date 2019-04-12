// pages/Release/Release.js
var app = getApp()
var i = 0
var upload = 0
var isUploadSuccess = true

Page({

  /**
   * 页面的初始数据
   */
  data: {
    sendInfo: '',
    files: [],
    name: '',
    describe: '',
    kind: '',
    usetime: '',
    price: '',
    bargain: '',
    submitbtn: '发布',
    chosekind: '商品类别',
    choseusetime: '商品几成新',
    showusetime: '',
    count: '1',
    smallkind: "",
    arraybargain: ['是', '否'],
    array: ['四成新', '五成新', '六成新', '七成新', '八成新', '九成新', '全新'],

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
    var pages = getCurrentPages();
    var currPage = pages[pages.length - 1]; //当前页面
    var smallkind = currPage.data.smallkind
    this.setData({
      smallkind: smallkind
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {},

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
  chooseImage: function(e) {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        that.setData({
          Thumbnail: that.data.files.concat(res.tempFilePaths)
        });
      }
    })
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
  previewImage: function(e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.Thumbnail // 需要预览的图片http链接列表
    })
  },
  previewImage1: function(e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.Detail // 需要预览的图片http链接列表
    })
  },
  getpicture: function(e) {
    this.setData({
      picture: e.detail.value
    })
  },
  getcname: function(e) {
    this.setData({
      name: e.detail.value
    })
  },
  getdescribe: function(e) {
    this.setData({
      describe: e.detail.value
    })
  },
  getchose: function(e) {
    this.setData({
      smallkind: this.data.smallkind
    })
    console.log(this.data.smallkind)
  },
  getCount: function(e) {
    this.setData({
      count: e.detail.value
    })
  },

  getprice: function(e) {
    this.setData({
      price: e.detail.value
    })
  },

  clicktorelease: function() {
    if (this.data.Thumbnail.length == 0) {
      wx.showToast({
        title: '请添加商品首页展示图',
        icon: 'none',
        duration: 2000,
      })
    } else if (this.data.Detail.length == 0) {
      wx.showToast({
        title: '请填写商品详情图',
        icon: 'none',
        duration: 2000,
      })
    } else if (this.data.name.length == 0) {
      wx.showToast({
        title: '请填写商品名称',
        icon: 'none',
        duration: 2000,
      })
    } else if (this.data.describe.length == 0) {
      wx.showToast({
        title: '请填写商品描述',
        icon: 'none',
        duration: 2000,
      })
    } else if (this.data.smallkind.length == 0) {
      wx.showToast({
        title: '请选择商品类别',
        icon: 'none',
        duration: 2000,
      })
    } else if (this.data.usetime.length == 0) {
      wx.showToast({
        title: '请选择商品新旧程度',
        icon: 'none',
        duration: 2000,
      })
    } else if (this.data.bargain.length == 0) {
      wx.showToast({
        title: '请选择商品是否接受砍价',
        icon: 'none',
        duration: 2000,
      })
    } else if (this.data.price.length == 0) {
      wx.showToast({
        title: '请填写商品价格',
        icon: 'none',
        duration: 2000,
      })
    } else {
      // App.appData.cName = {
      //   cName: this.data.name
      // }
      var that = this
      wx.showModal({
        title: '是否提交',
        content: '',
        success: function(res) {
          if (res.confirm) {
            wx.uploadFile({
              url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/releaseThumbnail',
              filePath: that.data.Thumbnail[0],
              name: 'file',
              formData: {
                'cName': that.data.name, //商品名称
                'kName': that.data.smallkind, //所属类别
                'cPrice': that.data.price, //商品价格
                'useTime': that.data.usetime, //使用时长
                'userID': app.appData.userinfo.userID, //登录用户ID
                // 'userName': app.appData.userinfo.userName,
                'cDescription': that.data.describe, //商品描述
                'bargain': that.data.bargain, //是否能够讨价还价
                'count': that.data.count, //商品数量
              },
              success: function(res) {
                upload = 1
                console.log('缩略图上传成功')
              },
              fail: function(res) {
                console.log('缩略图上传失败')
                isUploadSuccess = false
              },
              complete: function(res) {
                if (upload == 1) {
                  that.releaseDetail();
                }
                i = 0;
                //不用wx.switchTab（不刷新）
                //wx.reLaunch(可刷新)会执行onLoad()
                wx.reLaunch({
                  url: '../Personal/Personal',
                })
                
              }
            });

          } else {
            console.log('用户点击取消!')
          }

        }

      })


    }
  },
  clicktoselectionkind: function() {
    wx.navigateTo({
      url: '../SelectionKind/SelectionKind',
    })
  },
  bindPickerChange: function(e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value,
      // usetime: this.data.array[e.detail.value]
    })
    this.setData({
      usetime: this.data.array[e.detail.value]
    })
  },
  bindbargain: function(e) {
    this.setData({
      bargainindex: e.detail.value,
      bargain: this.data.arraybargain[e.detail.value]
    })

  },
  releaseDetail: function() { //上传商品详情图
    var that = this
    wx.uploadFile({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/releaseDetail',
      filePath: that.data.Detail[i],
      name: 'file',
      formData: {
        'userID': app.appData.userinfo.userID, //登录用户ID
        'cName': that.data.name, //商品名称
      },
      success: function(res) {
        console.log('详情图第' + (i + 1) + '张上传成功')
      },
      fail: function(res) {
        console.log('详情图第' + (i + 1) + '张上传失败')
        isUploadSuccess = false
      },
      complete: function(res) {
        i++;
        if (i == that.data.Detail.length) {
          console.log("执行完毕");
          that.cleanData();
          if (isUploadSuccess == true) {
            wx.showToast({
              title: '发布成功',
              duration: 2000
            })
          } else {
            wx.showToast({
              title: '发布失败',
              duration: 3000
            })
          }
        } else {
          if (i > that.data.Detail.length) {
            console.log('错误执行完毕')
          } else {
            that.releaseDetail();
          }
        }
      }
    })
  },
  cleanData: function() {
    i = 0
    upload = 0
    isUploadSuccess = true
    this.setData({
      Thumbnail: [],
      Detail: [],
      files: [],
      name: '', //商品名称
      price: '', //商品价格
      smallkind: '',
      usetime: '',
      describe: '', //商品描述
      bargain: '', //是否能够讨价还价
      count: 1, //商品数量
    })
  }



})