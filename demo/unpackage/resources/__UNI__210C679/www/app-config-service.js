
var isReady=false;var onReadyCallbacks=[];
var __uniConfig = {"pages":["pages/login/login","pages/index/index","pages/user/user","pages/my-curriculum/my-curriculum","pages/curriculum_list/curriculum_list","pages/curriculum/curriculum","pages/comment/comment"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"欢迎使用NEXT学院","navigationBarBackgroundColor":"#FFFFFF","backgroundColor":"#FFFFFF"},"tabBar":{"color":"#515151","selectedColor":"#33c9ff","list":[{"pagePath":"pages/index/index","iconPath":"static/img/home-1.png","selectedIconPath":"static/img/home-2.png","text":"首页"},{"pagePath":"pages/my-curriculum/my-curriculum","iconPath":"static/img/calendar-1.png","selectedIconPath":"static/img/calendar-2.png","text":"课程表"},{"pagePath":"pages/user/user","iconPath":"static/img/people-1.png","selectedIconPath":"static/img/people-2.png","text":"我的"}]},"nvueCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"demo","compilerVersion":"2.7.9","entryPagePath":"pages/login/login","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/login/login","meta":{"isQuit":true},"window":{}},{"path":"/pages/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"测试用demo"}},{"path":"/pages/user/user","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/my-curriculum/my-curriculum","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/curriculum_list/curriculum_list","meta":{},"window":{}},{"path":"/pages/curriculum/curriculum","meta":{},"window":{}},{"path":"/pages/comment/comment","meta":{},"window":{}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
