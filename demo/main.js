import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
 

 let baseUrl = "http://localhost:8080/renren-fast"
 //prototype绑定全局变量、方法等
 Vue.prototype.url={
	"img":"http://47.93.217.75",
 	"login":baseUrl + "/app/user/login",
	"searchTop":baseUrl+"/app/curriculum/searchTop",
	"searchHot":baseUrl+"/app/curriculum/searchHot",
	"searchRefined":baseUrl+"/app/curriculum/searchRefined",
	"searchByPage":baseUrl+"/app/curriculum/searchByPage"
 }