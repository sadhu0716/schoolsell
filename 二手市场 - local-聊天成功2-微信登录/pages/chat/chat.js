var app = getApp();
var utils = require("../../utils/util.js")


Page({
      data: {
       
        scrollTop: 0,
        newsList: [],
        acceptid: '',
        context: '',
        myinput:'',
        isfisrt:false,
        lastId:'',
        picsend:'',
        picget:'',

      

      },
      onLoad: function(options) { 
        var self=this;
        console.log("传过来的name" + options.name);
        self.setData({
          acceptid: options.name

        });
        
        //把收消息放在这里
        wx.onSocketMessage(function (res) {
          console.log('收到服务器内容：' + res.data);
          var rd = JSON.parse(res.data);
          console.log("res.data.sendid为" + rd.sendid);
          if (app.globalData.isOpenchatface) {
            if (rd.sendid == self.data.acceptid) {
              var chattemp = self.data.newsList;
              chattemp.push(rd);
              self.setData({
                newsList: chattemp
              });
              self.changeMsglistCache('accpet');
              wx.request({
                url: 'http://localhost:8080/schoolsell/chatInterface?userid=' + app.appData.userinfo.userID + '&otherid=' + self.data.acceptid,
                header: {
                  "Content-Type": "application/json"
                }, success: function (res) {
                  // console.log(res.data);
                }
              });
             
              
            } else {
              app.globalData.msgnumber = app.globalData.msgnumber + 1;
              //将收到的消息存入全局新收到消息列表
              app.globalData.iscanrefresh=true;
              var freshtemplist = app.globalData.freshnewsList;
              var isfreshexist = false;
              if (freshtemplist) {
                for (var i = 0; i < freshtemplist.length; i++) {
                  if (rd.sendid == freshtemplist[i].name) {
                    freshtemplist[i].onechatlist.push(rd);
                    isfreshexist = true;
                  }
                }
              };
              if (!isfreshexist) {
                var newonelist = [];
                newonelist.push(rd);
                var tempmap = { 'name': rd.sendid, 'onechatlist': newonelist }
                freshtemplist.push(tempmap);
              }
              app.globalData.freshnewsList = freshtemplist;
             
             
              //存入给消息列表的新消息App.globalData.tomsglistfreshnews
              var tomsglistfreshtemplist = app.globalData.tomsglistfreshnews;
              var isfreshexist = false;
              if (tomsglistfreshtemplist) {
                for (var i = 0; i < tomsglistfreshtemplist.length; i++) {
                  if (rd.sendid == tomsglistfreshtemplist[i].name) {
                    tomsglistfreshtemplist[i].onechatlist.push(rd);
                    isfreshexist = true;
                  }
                }
              };
              if (!isfreshexist) {
                var newonelist = [];
                newonelist.push(rd);
                var tempmap = { 'name': rd.sendid, 'onechatlist': newonelist }
                tomsglistfreshtemplist.push(tempmap);
              }
              app.globalData.tomsglistfreshnews = tomsglistfreshtemplist;
              console.log("已经装进全局的tomsglistfreshnews的长度" + app.globalData.tomsglistfreshnews.length);


            }}else{
            app.globalData.msgnumber = app.globalData.msgnumber + 1;
            //将收到的消息存入全局新收到消息列表
            app.globalData.iscanrefresh=true;
            var freshtemplist = app.globalData.freshnewsList;
            var isfreshexist = false;
            if (freshtemplist) {
              for (var i = 0; i < freshtemplist.length; i++) {
                if (rd.sendid == freshtemplist[i].name) {
                  freshtemplist[i].onechatlist.push(rd);
                  isfreshexist = true;
                }
              }
            };
            if (!isfreshexist) {
              var newonelist = [];
              newonelist.push(rd);
              var tempmap = { "name": rd.sendid, "onechatlist": newonelist }
              freshtemplist.push(tempmap);
              console.log("执行到了这里freshtemplistd的值为" + freshtemplist);
            }
            app.globalData.freshnewsList = freshtemplist;
           //将新收到的消息存入给消息列表的全局App.globalData.tomsglistfreshnews
            var tomsglistfreshtemplist = app.globalData.tomsglistfreshnews;
            var isfreshexist = false;
            if (tomsglistfreshtemplist) {
              for (var i = 0; i < tomsglistfreshtemplist.length; i++) {
                if (rd.sendid == tomsglistfreshtemplist[i].name) {
                  tomsglistfreshtemplist[i].onechatlist.push(rd);
                  isfreshexist = true;
                }
              }
            };
            if (!isfreshexist) {
              var newonelist = [];
              newonelist.push(rd);
              var tempmap = { "name": rd.sendid, "onechatlist": newonelist }
              tomsglistfreshtemplist.push(tempmap);
            };
            };
          app.globalData.tomsglistfreshnews = tomsglistfreshtemplist;
         // app.globalData.msgnumber = app.globalData.msgnumber + 1;
          console.log("全局的freshnewList为" + app.globalData.freshnewsList );
          wx.setTabBarBadge({
            index: 1,
            text: app.globalData.msgnumber.toString(),
          })
          var indexid = self.data.newsList.length - 1;
          indexid = "msg" + indexid;
          self.setData({
            lastId: indexid
          })
        })
      
      
      },
      onShow :function(e){
       // console.log("调用了onshow")
        var self = this;
        var s = 0;
        app.globalData.isOpenchatface=true;
        var newsListsTemp = [];
        //当页面打开时在本地缓存中找到对应的聊天记录，并赋值给该页面的newsList
        var temphuans=wx.getStorageSync('newsLists')
        if (temphuans){
          newsListsTemp = temphuans;
        };
        
        console.log("在这里中取出了数据库的数据为长度为" + newsListsTemp.length);
        if (newsListsTemp.length>0){
          console.log("一号的名字为" + newsListsTemp[0].name);
          for (var i = 0; i < newsListsTemp.length; i++) {
            var eachchat = newsListsTemp[i]
            if (eachchat.name == self.data.acceptid) {
              var tm = self.data.newsList.concat(eachchat.newsList);
              // console.log("tm为"+tm);
              self.setData({
                newsList: tm
              });
             // console.log("执行完从数据库中赋值后的newsList" + self.data.newsList);
              newsListsTemp.splice(i, 1);
              i = i - 1;
            }
          }

        };
        wx.setStorageSync('newsLists', newsListsTemp);
        

        var fisrtmsglist=  app.globalData.fisrtmsglist;
        for(var i=0;i<fisrtmsglist.length;i++){
          if(fisrtmsglist[i].name==self.data.acceptid){
            var temponechatlist = JSON.parse(fisrtmsglist[i].onechatlist);
            self.setData({
              newsList: self.data.newsList.concat( temponechatlist)
            });
            s = temponechatlist.length;
            console.log("1.到这s的值为" + s + "self.data. newsList的长度为" + self.data. newsList.length);
            app.globalData.fisrtmsglist.splice(i,1);
            
          }
        };
        //console.log("现在的newList的值为"+self.data.newsList);
        //给服务器发送sendid与acceptid，让服务器包含这两个字段的记录的isbrose=true；
          wx.request({
            url: 'http://localhost:8080/schoolsell/chatInterface?userid=' + app.appData.userinfo.userID +'&otherid='+self.data.acceptid,
            header: {
              "Content-Type": "application/json"
            }, success: function (res) {
                 // console.log(res.data);
            }
          });
      

  //查找全局的消息freshnewsList，找到对应发送者名的时候，将消息附加到本页面的newsList

        if (app.globalData.freshnewsList.length!=0) {
          var mylist = [];
          
          // var mylist2= app.globalData.list;
          mylist = self.data.newsList;
          for (var i = 0; i < app.globalData.freshnewsList.length; i++) {
            if (app.globalData.freshnewsList[i].name == self.data.acceptid) {
              var temp = app.globalData.freshnewsList[i].onechatlist;
              s = s+temp.length;
              console.log("2.到这s的值为"+s);
              for (var j = 0; j < temp.length; j++) {
                var each = temp[j];
                //var te = JSON.parse(each);
                mylist.push(each);
              };
              app.globalData.freshnewsList.splice(i,1);
            };
          };
          self.setData({
            newsList: mylist,
          });
          

        };
        app.globalData.msgnumber = app.globalData.msgnumber - s;
        
        var msgindex = self.data.newsList.length-1;
        msgindex = "msg" + msgindex;
        self.setData({
          lastId: msgindex
        });
        console.log("lastID为" + self.data.lastId);
      
        var indexid = self.data.newsList.length-1;
        indexid = "msg" + indexid;
        setTimeout(() => {
          self.setData({
            lastId: indexid
          });},700);
          
        
        
      },
      
      //当页面退出时，将聊天记录存入全局的chatList中
  onUnload:function(e){
    var self=this;
    app.globalData.isOpenchatface = false;
    var temp={
      'name':self.data.acceptid,
      'newsList':self.data.newsList
    }
    //app.globalData.chatList.push(temp);
    //定义临时的数组
    var newsListsTemp=[];
    var istmpe = wx.getStorageSync('newsLists')
   if(istmpe){
     newsListsTemp = istmpe
   }
  //将当前页面的消息存入临时数组中
  newsListsTemp.push(temp);
  //将这个临时数组取代原来存储的消息
    wx.setStorageSync('newsLists', newsListsTemp)
    
    wx.setTabBarBadge({
      index: 1,
      text: app.globalData.msgnumber.toString(),
    });
    console.log("执行到这了吗app.globalData.msgnumber为" + app.globalData.msgnumber);
  },
//当聊天界面打开时，当前对像发过来的消息也存入消息列表的缓存中
          changeMsglistCache:function(e){
            var self = this;
            var msliftemp = wx.getStorageSync('msgListInterface');
            var msliftempnotnull=[];
            if (msliftemp) {
              msliftempnotnull = msliftemp;
            }
            var tempnewsList=self.data.newsList;
            var lastlist = tempnewsList[tempnewsList.length-1];
            if(e=='accpet'){
              for (var i = 0; i < msliftempnotnull.length; i++) {
                var issanmename = false;
                if (msliftempnotnull[i].name == lastlist.sendid) {
                  msliftempnotnull[i].msgDetail = lastlist.context;
                  msliftempnotnull[i].msgTime = lastlist.date;
                  msliftempnotnull[i].num = 0;
                  issanmename = true;
                }
              }
              if (!issanmename) {
                var msg = {
                  'name': lastlist.sendid,
                  'msgDetail': lastlist.context,
                  'msgTime': lastlist.date,
                  'num': 0,
                  'pic': ''

                };
                msliftempnotnull.push(msg);
              }
            }else{
              for (var i = 0; i < msliftempnotnull.length; i++) {
                var issanmename = false;
                if (msliftempnotnull[i].name == lastlist.acceptid) {
                  msliftempnotnull[i].msgDetail = lastlist.context;
                  msliftempnotnull[i].msgTime = lastlist.date;
                  msliftempnotnull[i].num = 0;
                  issanmename = true;
                }
              }
              if (!issanmename) {
                var msg = {
                  'name': lastlist.acceptid,
                  'msgDetail': lastlist.context,
                  'msgTime': lastlist.date,
                  'num': 0,
                  'pic': ''

                };
                msliftempnotnull.push(msg);
              }
            }
           
            wx.setStorageSync('msgListInterface', msliftempnotnull);
          },
          
          bindChange: function(e) {
            var that=this;
            that.data.context = e.detail.value
           
          },
          //事件处理函数
          add: function(e) {
            var that = this;
            // var userInfo=wx.getStorageSync("userInfo");
            var userId = app.appData.userinfo.userID
            console.log("聊天时获取的userID信息为:" + userId);
            // var get_pic = userInfo.avatarUrl
            var get_pic = "http://localhost:8080/getimage/" + userId + ".jpg";
            
            // var get_pic = userInfo.avatarUrl
            that.setData({
              picsend:get_pic
            })

            var senddata = {
              sendid: app.appData.userinfo.userID,
              acceptid: that.data.acceptid,
              context: that.data.context
            };
            var list = [];
            list = that.data.newsList;
            var temp = { 'context': that.data.context, 'date': utils.datetimeFormat_1(new Date()), 'type': 1, 'sendid': app.appData.userinfo.userID, acceptid: that.data.acceptid};
            list.push(temp);
            that.setData({
              newsList:list,
              myinput:'',
              context:''
             
            });
           that.changeMsglistCache('send');
            var obj = JSON.stringify(senddata)

            if (app.globalData.isOpen) {
              wx.sendSocketMessage({
                data: obj
              })

            }
            //定位数组的最后一个元素的位置，使scroll-view 定位到底部
            var indexid = that.data.newsList.length - 1;
            indexid = "msg" + indexid;
            
              that.setData({
                lastId: indexid
              });
              
          }
      })

    