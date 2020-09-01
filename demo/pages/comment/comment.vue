<template>
	<view>
		<view class="comment-container">
			<view class="comment-info" v-for="one in list" :key="one">
				<image :src="one.photo" mode="widthFix" class="user-header"></image>
				<view class="comment-content">
					<view class="line-1"><text>{{one.nick_name}}</text>
						<uni-rate :value="one.rating" size="24" style="margin-top: 15rpx;"></uni-rate>
					</view>
					<view class="line-2">{{one.comment}}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				page:1,
				length:10,
				list:[],
				flag:false,
				id:null
			};
		},
		methods:{
			searchByPage:function(that,bool){
				uni.request({
					url:that.url.searchCommentByPage,
					method:"POST",
					header:{
						"token":uni.getStorageSync("token")
					},
					data:{
						page:that.page,
						length:that.length,
						id:parseInt(that.id)
					},
					success:function(resp){
						//console.log(resp.data.result)
						let list = resp.data.result
						console.log(list.length)
						if(bool && list.length==0){
							that.page = that.page-1
							that.flag = true
							uni.showToast({
								title:"已经拉到最底了，没有新数据啦！"
							})
							return
						}
						that.list = that.list.concat(list)
						uni.showToast({
							title:"加载了"+list.length+"条记录"
						})
					}
				})
			}
		},
		onReachBottom:function(){
			let that = this
			that.page = that.page+1
			if (that.flag==false){
				that.searchByPage(that,true)
			}
			
		},
		onShow:function(){
			let that = this
			that.searchByPage(that,false)
		},
		onLoad:function(options) {
			let that = this
			if(options.hasOwnProperty("id")){
				that.id = options.id
				console.log(that.id)
			}
		}
	}
</script>

<style lang="less">
@import url("comment.less");
</style>
