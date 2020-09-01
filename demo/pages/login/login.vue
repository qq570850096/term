<template>
	<view>
		<image src="../../static/img/logo-small.jpg"class="logo" mode="widthFix"></image>
		<button class="btn" open-type="getUserInfo" @tap="login()">登录系统</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		
		methods: {
					login:function(){
						let that = this
						uni.request({
							url:that.url.login,
							method:"POST",
							success:function(resp){
								console.log(resp)
								let token = resp.data.token
								uni.setStorageSync("token",token)
								uni.switchTab({
									url:"../index/index"
								})
							}
						})
						
						// uni.getUserInfo({
						// 	success:function(resp){
						// 		let nickName=resp.userInfo.nickName
						// 		let avatarUrl=resp.userInfo.avatarUrl
						// 		uni.login({
						// 			success:function(resp){
						// 				let code=resp.code
						// 				//发起登录请求
										
						// 			}
						// 		})
						// 	}
						// })
					}
				},
		}
</script>

<style lang="less">
	@import url("login.less");
</style>
