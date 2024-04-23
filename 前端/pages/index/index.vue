<template>
	<view clas="index-body">
		<view class="index-nav">
			<text class="font-bold"> 新增缴费 </text>
			<view class="city-ch" @click="changeCity">
				<text>{{cityName}}</text>
				<image src="/static/icon/down-arrow.png"></image>
			</view>
		</view>
		<view class="pay-item">
			<view class="item-card" v-for="(item, index) in serv" :key="index"
			@click="toPayChoice(item.id)"
			>
				<image :src="item.img_url" mode="aspectFit"></image>
				<text>{{item.name}}</text>
			</view>
		</view>
	</view>
	
</template>

<script setup>
	import {ref} from 'vue';
	import {requestApi} from '@/api/request.js';
	import {onShow} from '@dcloudio/uni-app';
	
	const cityName = ref("武汉");
	const cityId = ref(1);
	const serv = ref([]);
	//加载页面时获取数据
	onShow(async()=>{
		console.log('开始请求城市的服务');
		const servData = await requestApi('/serv/1');
		serv.value = servData.serv;
		console.log('已请求城市服务数据',serv.value);
		// getCityServ(1);
	})
	
	//获取当前城市的服务
	async function getCityServ(c_id) {
		serv.value = await requestApi('/serv/' + c_id);
		console.log(serv);
	} 

	// 与城市切换页面通信
	// 任务: 获取城市名和城市id
	function changeCity() {
		uni.navigateTo({
			url:"/pages/city_choice/city",
			events: {
				acceptDataFromOpenedPage: async function(data) {
					console.log("data", data);
					cityName.value = data.name;
					cityId.value = data.city_id;
					console.log('开始请求城市的服务', cityId.value);
					const servData = await requestApi('/serv/'+cityId.value);
					serv.value = servData.serv;
					console.log('已请求城市服务数据',serv.value);
				}
			}
		})	
	}
	// 跳转到公司选择页面
	function toPayChoice(servId) {
		console.log(servId);
		uni.navigateTo({
			url:"/pages/detail/pay_choice?"+"servId="+servId+"&cityId="+cityId.value,
		})
	}
	
</script>

<style>
	text {
		display: block;
	}
	
	page {
		background-color: #f1f3f5;
	}
	
	.city-ch image {
		height: 60rpx;
		width: 60rpx;
	}
	
	.city-ch {
		display:  flex;
		align-items: center;
	}
	
	.city-ch {
		font-size: 32rpx;
	}
	
	.index-nav {
		margin: 20px 10px;
		display: flex;
		align-items: center;
		justify-content: space-between;
	}
	
	.font-bold {
		font-weight: bold;
	}
	
	.pay-item {
		margin: 20px 10px;
		display: grid;
		grid-template-columns: repeat(3, 1fr);
		grid-template-rows: 200rpx 200rpx 200rpx;
		grid-column-gap: 5rpx;
		grid-row-gap: 5rpx;
		box-shadow: 0 0 32rpx rbga(0, 0, 0, 0.1);
	}
	
	.item-card {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-between;
		padding: 35rpx 50rpx;
		font-size: 32rpx;
		
		background-color: #fff;
	}
	
	.item-card image {
		width: 65rpx;
		height: 65rpx;
	}
</style>
