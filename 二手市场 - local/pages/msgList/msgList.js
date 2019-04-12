// pages/msgList/msgList.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    msgList: [],

  },
  navmsgList: function(e) {
    var self=this;
    var id = e.currentTarget.id;
    console.log("跳转时获取的id" + id);
    wx.navigateTo({
      url: "../chat/chat?name=" + id
    })
    for(var i=0;i<self.data.msgList.length;i++){
      if(self.data.msgList[i].name==id){
        var t = self.data.msgList;
        t[i].num=0;
        self.setData({
          msgList:t
           });
        
      }
      //console.log("针对消息列表的所点击的num变为" + (self.data.msgList)[i].num);
    };
    wx.setTabBarBadge({
      index: 1,
      text: app.globalData.msgnumber.toString(),
    })
console.log("执行了msglsit页面到聊天界面的跳转方法");
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
 var that=this;
    console.log("执行了消息列表的onLoad方法")
    
    if (!app.globalData.isfirst) {
     
//第一次也要查询一下全局是否有新消息
      if (app.globalData.tomsglistfreshnews.length != 0) {
        app.globalData.tomsglistfreshnews=[];
        console.log("难道没有执行这里？");
        var tmpemsgList = that.data.msgList;
        for (var i = 0; i < app.globalData.tomsglistfreshnews.length; i++) {
          var issamename = false;
          var tempf = app.globalData.tomsglistfreshnews;
          //console.log("tempf[i].name为" + tempf[i].name);
          // console.log(" self.data.msgList.length为" + self.data.msgList.length);
          for (var j = 0; j < that.data.msgList.length; j++) {
            console.log("that.data.msgList[j].name为" + that.data.msgList[j].name);
            if (tempf[i].name == that.data.msgList[j].name) {
              tmpemsgList[j].num = tempf[i].onechatlist.length + (that.data.msgList)[j].num
              issamename = true;
              // console.log("1.到了这里issamename变为" + issamename);
            }
          }
          if (issamename == false) {
            //console.log("2.到了这里issamename为" + issamename);

            var msg = {
              'name': tempf[i].name,
              'msgDetail': tempf[i].onechatlist[tempf[i].onechatlist.length - 1].context,
              'msgTime': tempf[i].onechatlist[tempf[i].onechatlist.length - 1].date,
              'num': tempf[i].onechatlist.length,
              'pic': ''

            };
            tmpemsgList.push(msg);
          }
        };
        that.setData({
          msgList: tmpemsgList
        })
      }
    }

    console.log("执行完了onLoad方法msgList的值为" + JSON.stringify( that.data.msgList));

  },
  fisrtrequest:function(e){
    var self=this;
    wx.request({
      url: 'https://www.ykyschoolsell.cn:8443/schoolsell-war/schoolsell/chatlist?userid=' + app.appData.userinfo.userID,
      header: {
        "Content-Type": "application/json"
      },
      success: function (res) {

        console.log(res.data);
        //console.log("长度" + res.data.length);

        var temp = [];
        //var tmpenum=0;
        for (var i = 0; i < res.data.length; i++) {
          //获得一个人的消息
          var oneperson = res.data[i];
          //获取这个人的userid
          var myname = oneperson.name;
          //获取这个人发送过来的消息列表
          var onechatlist = oneperson.onechatlist;
          //把列表的json格式，转化正常的列表
          var oj = JSON.parse(onechatlist);
          //获取列表中最后发来的一条消息记录内容
          var mymsgDetail = oj[oj.length - 1].context;
          //获取列表中最后一条消息的时间
          var mymsgTime = oj[oj.length - 1].date;
          //获取这个消息列表的长度
          var mynum = oj.length;
          //console.log("现在的长度为" + mynum);
          var mypic = "";
          //var一个临时的json格式的字符串，并把获得的数据传入
          var msgdata = {
            "name": myname,
            "msgDetail": mymsgDetail,
            "msgTime": mymsgTime,
            "num": mynum,
            "pic": mypic
          }

          //传入临时数组
          temp.push(msgdata);
          // tmpenum = tmpenum + mynum;
        }
        var tempsgl = self.data.msgList;
        for (var i = 0; i < temp.length; i++) {
          var issanme = false;
          for (var j = 0; j < tempsgl.length; j++) {
            
            if (temp[i].name == tempsgl[j].name) {
              tempsgl[j] = temp[i];
              issanme=true;
              console.log("找到了相同的名字！！");
            }; 
          };
          if (!issanme){
            console.log("没有找到相同的名字直接存入！！");
            tempsgl.push(temp[i]);
          }
          
        };
        //将获得记录的临时数组付给paga.data.msgList
        self.setData({
          msgList: tempsgl
        });

        //将第一场加载的本页面的记录传给全局fisrtmsglist

        app.globalData.fisrtmsglist = res.data;

      },
      fail: function (err) {
        console.log(err)
      }


    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    console.log("执行消息列表的onReady")
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    console.log("执行了消息列表的onShow方法");
    var self=this;
    var msliftemp = wx.getStorageSync('msgListInterface');
    if (msliftemp) {
      self.setData({
        msgList: msliftemp
      });
      console.log("onShow里的取出缓存数据赋值给msgList为" + self.data.msgList);
    };
    if (!app.globalData.isfirst){
      console.log("onshow的第一次执行了这里的方法msgList为" + JSON.stringify(self.data.msgList));
      app.globalData.isfirst = true;
      self.fisrtrequest();
      //onShow的执行先于onload，如果是第一次打开，那么会重复在msgList装入相同的名字的新消息，所以不做任何事，
    }else{
      //不是第一次打开则执行下列操作
      
      
      //判断是否可以刷新
      if(app.globalData.iscanrefresh==true){
        if (app.globalData.tomsglistfreshnews.length != 0) {
          var tmpemsgList = self.data.msgList;
          for (var i = 0; i < app.globalData.tomsglistfreshnews.length; i++) {
            var issamename = false;
            var tempf = app.globalData.tomsglistfreshnews;
            
            for (var j = 0; j < self.data.msgList.length; j++) {
              console.log("self.data.msgList[j].name为" + self.data.msgList[j].name);
              if (tempf[i].name == self.data.msgList[j].name) {
                tmpemsgList[j].num = tempf[i].onechatlist.length + (self.data.msgList)[j].num
                tmpemsgList[j].msgDetail = tempf[i].onechatlist[tempf[i].onechatlist.length - 1].context
                tmpemsgList[j].msgTime = tempf[i].onechatlist[tempf[i].onechatlist.length - 1].date
                issamename = true;
                // console.log("1.到了这里issamename变为" + issamename);
              }
            }
            if (issamename == false) {
              //console.log("2.到了这里issamename为" + issamename);

              var msg = {
                'name': tempf[i].name,
                'msgDetail': tempf[i].onechatlist[tempf[i].onechatlist.length - 1].context,
                'msgTime': tempf[i].onechatlist[tempf[i].onechatlist.length - 1].date,
                'num': tempf[i].onechatlist.length,
                'pic': ''

              };
              tmpemsgList.push(msg);
            }
          }
          self.setData({
            msgList: tmpemsgList
          })
          app.globalData.tomsglistfreshnews=[];
          app.globalData.iscanrefresh=false;
        }
      }
      
    }
   
   
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {
    var self = this;
    wx.setStorageSync('msgListInterface', self.data.msgList);
    console.log("执行了消息页面隐藏方法onHide");
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {
    var self=this;
    console.log("执行消息列表页面的销毁方法onUnload");
   
   
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    var self=this;
    //判断是否可以刷新新的消息
    if(app.globalData.iscanrefresh==true){
      if (app.globalData.tomsglistfreshnews.length != 0) {
        var tmpemsgList = self.data.msgList;
        for (var i = 0; i < app.globalData.tomsglistfreshnews.length; i++) {
          var issamename = false;
          var tempf = app.globalData.tomsglistfreshnews;
          console.log("tempf[i].name为" + tempf[i].name);
          for (var j = 0; j < self.data.msgList.length; j++) {
            if (tempf[i].name == self.data.msgList[j].name) {
              tmpemsgList[j].num = tempf[i].onechatlist.length + (self.data.msgList)[j].num
              tmpemsgList[j].msgDetail = tempf[i].onechatlist[tempf[i].onechatlist.length - 1].context
              tmpemsgList[j].msgTime = tempf[i].onechatlist[tempf[i].onechatlist.length - 1].date
              issamename = true;
              console.log("到了这里issamename变为" + issamename);
            }
          }
          if (issamename == false) {
            console.log("到了这里issamename为" + issamename);

            var msg = {
              'name': tempf[i].name,
              'msgDetail': tempf[i].onechatlist[tempf[i].onechatlist.length - 1].context,
              'msgTime': tempf[i].onechatlist[tempf[i].onechatlist.length - 1].date,
              'num': tempf[i].onechatlist.length,
              'pic': ''

            };
            tmpemsgList.push(msg);
          }
        }
        self.setData({
          msgList: tmpemsgList
        })
      }
      app.globalData.tomsglistfreshnews=[];
      app.globalData.iscanrefresh=false;
    }
 
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