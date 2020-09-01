<template>
	<view >
		<view class="search-container">
			<image src="../../static/img/logo-small.jpg" mode="widthFix"class="logo"></image>
			<view class="serach-bar">
				<icon type="search" size="16" class="search-icon" @tap="toCurriculumList()"></icon>
				<input type="text" placeholder="请输入关键字" class="serach" v-model="keyword"
							confirm-type="search" @confirm="toCurriculumList()"/>		
			</view>
			<!-- 怎么排成一行：使用flex布局 -->
		</view>
		
		<swiper :indicator-dots="true" :autoplay="true" :interval="3000" :duration="1000" class="swiper-container">
			<swiper-item v-for="one in topList" :key="one.id" @tap="toCurriculum(one.id)">
				<image :src="one.banner" mode="widthFix" class="swiper-image"></image>
			</swiper-item>
		</swiper>
		<view class="hot-container" @tap="toCurriculum(hot.id)">
			<view class="title">热门课程</view>
			<view class="hot-content">
				<view class="info">
					<view class="curriculum-name">
						{{hot.name}}
					</view>
					<view class="curriculum-desc">
						{{hot.suggestion}}
					</view>
					<view class="teacher">
						{{hot.title}} {{hot.nick_name}}
					</view>
				</view>
				<image :src="hot.icon" mode="widthFix" class="type-icon"></image>
			</view>
		</view>
		
		<view class="refined-container">
			<view class="title">精制课程</view>
			<view class="tab-container">
				<view :class="tab_1" @tap="changeTab(1)">
					小白入门
				</view>
				<view :class="tab_2" @tap="changeTab(2)">
					基础进阶
				</view>
				<view :class="tab_3" @tap="changeTab(3)">
					专项突破
				</view>
			</view>
			<view class="tab-page-container">
				<view :class="page_1">
					<view class="refined-content" v-for="one in list_1" :key="one" @tap="toCurriculum(one.id)">
						<view class="info">
							<view class="curriculum-name">
								{{one.name}}
							</view>
							<view class="curriculum-desc">
								{{one.learning_cycle}}
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
					<button class="btn" @tap="toAllList('入门')">全部课程</button>
				</view>
				<view :class="page_2">
					<view class="refined-content" v-for="one in list_2" :key="one" @tap="toCurriculum(one.id)">
						<view class="info">
							<view class="curriculum-name">
								{{one.name}}
							</view>
							<view class="curriculum-desc">
								{{one.learning_cycle}}
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
					<button class="btn" @tap="toAllList('简单')">全部课程</button>
				</view>
				<view :class="page_3">
					<view class="refined-content" v-for="one in list_3" :key="one" @tap="toCurriculum(one.id)">
						<view class="info">
							<view class="curriculum-name">
								{{one.name}}
							</view>
							<view class="curriculum-desc">
								{{one.learning_cycle}}
							</view>
							<view class="curriculum-desc">
								{{one.tag}}
							</view>
							<view class="teacher">
								Next学院 {{one.title}} {{one.nick_name}}
							</view>
						</view>
						<image :src="one.icon" mode="widthFix" class="type-icon"></image>
					</view>
					<button class="btn"@tap="toAllList('中等')">全部课程</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				topList:[],
				hot:{},
				list_1:[],
				list_2:[],
				list_3:[],
				tab_1:"tab active",
				tab_2:"tab",
				tab_3:"tab",
				page_1:"page active",
				page_2:"page",
				page_3:"page",
				keyword:""
			}
		},
		
		methods:{
			changeTab:function(n){
				let that = this
				if (n==1){
					that.tab_1="tab active"
					that.tab_2="tab"
					that.tab_3="tab"
					that.page_1="page active"
					that.page_2="page"
					that.page_3="page"
				}
				else if (n==2){
					that.tab_1="tab"
					that.tab_2="tab active"
					that.tab_3="tab"
					that.page_1="page"
					that.page_2="page active"
					that.page_3="page"
				}
				else if(n==3){
					that.tab_1="tab"
					that.tab_2="tab"
					that.tab_3="tab active"
					that.page_1="page"
					that.page_2="page"
					that.page_3="page active"
				}
			},
			toAllList:function(param){
				uni.navigateTo({
					url:"../curriculum_list/curriculum_list?difficulty="+param
				})
			},
			toCurriculumList:function(){
				let that = this
				uni.navigateTo({
					url:'../curriculum_list/curriculum_list?keyword='+that.keyword
				})
			},
			toCurriculum:function(id) {
				uni.navigateTo({
					url:"../curriculum/curriculum?id="+id
				})
			}
		},
		
		onShow:function(){
			let that =this
			uni.request({
				url:that.url.searchTop,
				method:"GET",
				header:{
					"token":uni.getStorageSync("token")
				},
				success:function(resp){
					console.log(resp.data.result)
					let list=resp.data.result
					for(let one of list){
						one.banner=that.url.img+"/"+one.banner
					}
					that.topList=list
				}
			
			})

			uni.request({
				url:that.url.searchHot,
				method:"GET",
				header:{
					"token":uni.getStorageSync("token")
				},
				success:function(resp){
					console.log(resp.data)
					let result = resp.data.result
					that.hot = result
					that.hot.icon = that.url.img + '/' +that.hot.icon
				}
			})
	
			uni.request({
				url:that.url.searchRefined,
				method:'GET',
				header:{
					"token":uni.getStorageSync("token")
				},
				success:function(resp){
					console.log(resp.data)
					let result = resp.data.result
					let list_1 = resp.data.list_1
					for(let one of list_1){
						one.icon = that.url.img+'/'+one.icon
						one.tag = JSON.parse(one.tag).join("  ·  ")
					}
					let list_2 = resp.data.list_2
					for(let one of list_2){
						one.icon = that.url.img+'/'+one.icon
						one.tag = JSON.parse(one.tag).join("  ·  ")
					}
					let list_3 = resp.data.list_3
					for(let one of list_3){
						one.icon = that.url.img+'/'+one.icon
						one.tag = JSON.parse(one.tag).join("  ·  ")
					}
					that.list_1=list_1
					that.list_2=list_2
					that.list_3=list_3
				}
			})
		}
	}
</script>

<style lang="less">
	@import  url("index.less");	
</style>
