// pages/register/register.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userID: '',
    username: '',
    phonenumber: '',
    code: '',
    password: '',
    repassword: '',
    realname: '',
    IDnumber: '',
    address: '',
    checked: false

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
  getEamilValue: function (e) {
    var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    var idResult = pattern.test(e.detail.value);
    if (idResult == true) {
      this.setData({
        userID: e.detail.value
      })
    } else {
      wx.showToast({
        title: '邮箱格式错误，请重新填写!',
        duration: 1000
      })
    }
  },
  getNameValue: function (e) {
    this.setData({
      username: e.detail.value
    })

  },
  getPhoneValue: function (e) {
    var pattern = /^0?(13|14|15|18)[0-9]{9}$/
    var phoneResult = pattern.test(e.detail.value)
    if (phoneResult == true) {
      this.setData({
        phonenumber: e.detail.value
      })
    } else {
      wx.showToast({
        title: '手机号格式错误，请重新填写!',
        duration: 2000
      })
    }

  },
  getCodeValue: function (e) {
    var pattern = /^[0-9]{6}$/
    var codeResult = pattern.test(e.detail.value)
    if (codeResult == true) {
      this.setData({
        code: e.detail.value
      })
    } else {
      wx.showToast({
        title: '验证码格式错误!',
        duration: 2000
      })
    }
  },
  getpassword: function (e) {
    var pattern = /^\w*$/
    var passwordResult = pattern.test(e.detail.value)
    if (passwordResult == true) {
      this.setData({
        password: e.detail.value
      })
    } else {
      wx.showToast({
        title: '密码为字母数字下划线组成！',
        duration: 2000
      })
    }
  },
  getrepassword: function (e) {
    if (e.detail.value != this.data.password) {
      wx.showToast({
        title: '密码不一致',
        duration: 1000
      })
    } else {
      this.setData({
        repassword: e.detail.value
      })
    }
  },
  getrealname: function (e) {
    var pattern = /^[\u4e00-\u9fa5]{2,4}$/
    var realResult = pattern.test(e.detail.value)
    if (realResult == true) {
      this.setData({
        realname: e.detail.value
      })
    } else {
      wx.showToast({
        title: '请输入中文姓名!',
        duration: 2000
      })
    }
  },
  getIDnumber: function (e) {
    var pattern = /^\d{17}[\d|x]|\d{15}$/
    var idResult = pattern.test(e.detail.value);
    if (idResult == true) {
      this.setData({
        IDnumber: e.detail.value
      })
    } else {
      wx.showToast({
        title: '身份证号格式错误!',
        duration: 2000
      })
    }
  },
  getaddress: function (e) {
    var pattern = /^\d{1,4}$/
    var addressResult = pattern.test(e.detail.value)
    if (addressResult == true) {
      this.setData({
        address: e.detail.value
      })
    } else {
      wx.showToast({
        title: '请输入****的格式',
        duration: 2000
      })
    }
  },
  checkboxChange: function (e) {

    var checked = e.target.dataset.checked ? false : true
    // if(checked==true){
    //   checked=false
    // }
    this.setData({
      checked: checked
    })
    console.log(checked)
  },
  registerBtn: function () {
    var that = this
    // if (this.data.userID.length == 0) {
    //   wx.showToast({
    //     title: '邮箱账号必填!',
    //     icon: 'none',
    //     duration: 2000,
    //   })
    // } else if (this.data.username.length == 0) {
    //   wx.showToast({
    //     title: '昵称必填!',
    //     icon: 'none',
    //     duration: 2000,
    //   })
    // } else if (this.data.phonenumber.length == 0) {
    //   wx.showToast({
    //     title: '电话号码必填!',
    //     icon: 'none',
    //     duration: 2000
    //   })
    // } else if (this.data.code.length == 0) {
    //   wx.showToast({
    //     title: '请填写验证码!',
    //     icon: 'none',
    //     duration: 2000
    //   })
    // } else if (this.data.password.length == 0) {
    //   wx.showToast({
    //     title: '密码必填!',
    //     icon: 'none',
    //     duration: 2000
    //   })
    // } else if (this.data.repassword.length == 0) {
    //   wx.showToast({
    //     title: '请确认密码!',
    //     icon: 'none',
    //     duration: 2000
    //   })
    // } else if (this.data.password != this.data.repassword) {
    //   wx.showToast({
    //     title: '两次填写的密码不一样!',
    //     icon: 'none',
    //     duration: 2000
    //   })
    // } else if (this.data.realname.length == 0) {
    //   wx.showToast({
    //     title: '未填写真实姓名!',
    //     icon: 'none',
    //     duration: 2000
    //   })
    // } else if (this.data.IDnumber.length == 0) {
    //   wx.showToast({
    //     title: '请填写身份证号!',
    //     icon: 'none',
    //     duration: 2000
    //   })
    // } else if (this.data.adress.length == 0) {
    //   wx.showToast({
    //     title: '请填写你的住址!',
    //     icon: 'none',
    //     duration: 2000
    //   })
    if (this.data.checked == false || this.data.checked == "false") {
      wx.showToast({
        title: '请同意注册协议!',
        icon: 'none',
        duration: 2000
      })
    } else {
      wx.request({
        url: '',
      })
      //获取缓存tempFilePaths数据
      var card = wx.getStorageSync('card')
      var that = this
      wx.uploadFile({
        url: "https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/register",
        filePath: card,
        name: 'file',
        formData: {
          'userID': that.data.userID,
          'password': that.data.password,
          'userName': that.data.username,
          'realName': that.data.realname,
          'idCard': that.data.IDnumber,
          'credibility': '10',
          'phoneNumber': that.data.phonenumber,
          'address': that.data.address,
          // 'isChecked': that.data.checked
        },
        header: {
          "Content-Type": "multipart/form-data"
        },
        success: function (res) {
          var result = res.data.result
           wx.redirectTo({
             url: '../login/login',
           })
        },
        fail: function (res) {
          wx.hideToast();
          wx.showModal({
            title: '错误提示',
            content: '上传图片失败',
            showCancel: false,
            success: function (res) { }
          })
        }
      });

    }
  },
  /**
   * 上传图片
   */
  upload: function () {
    let that = this;
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片

        let tempFilePaths = res.tempFilePaths;
        console.log(tempFilePaths)
        //显示要上传的图片
        that.setData({
          tempFilePaths: tempFilePaths
        })
        //缓存tempFilePaths
        wx.setStorage({
          key: "card",
          data: tempFilePaths[0]
        })
      }
    })
  },


})