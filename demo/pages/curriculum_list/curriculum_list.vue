<template>
	<view>
		<view class="curriculum-content" v-for="one in list" :key="one">
			<view class="info">
				<view class="curriculum-name">
					{{one.name}}
				</view>
				<view class="curriculum-desc">
					学习周期：{{one.learning_cycle}}
				</view>
				<view class="curriculum-desc">
					{{one.tag}}
				</view>
				<view class="teacher">
					{{one.title}} {{one.nick_name}}
				</view>
			</view>
			<image :src="one.icon" mode="widthFix" class="type-icon"></image>
		</view>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				difficulty:"",
				keyword:"",
				page:1,
				length:10,
				list:[],
				flag:false
			}
		},
		methods: {
			searchByPage:function(that,bool){
				uni.request({
					url:that.url.searchByPage,
					method:"POST",
					header:{
						"token":uni.getStorageSync("token")
					},
					data:{
						page:that.page,
						length:that.length,
						difficulty:that.difficulty,
						keyword:that.keyword
					},
					success:function(resp){
						//console.log(resp.data.result)
						let list = resp.data.result
						if(bool && list.length==0){
							that.page = that.page-1
							that.flag = true
							uni.showToast({
								title:"已经拉到最底了，没有新数据啦！"
							})
							return
						}
						for(let one of list){
							one.icon = that.url.img+"/"+one.icon
							one.tag = JSON.parse(one.tag).join("  ·  ")
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
			if(options.hasOwnProperty("difficulty")){
				that.difficulty = options.difficulty
				console.log(that.difficulty)
			}
			if(options.hasOwnProperty("keyword")){
				that.keyword = options.keyword
			}
		},
	}
</script>
 
<style>
	@import url("curriculum_list.less");
</style>
