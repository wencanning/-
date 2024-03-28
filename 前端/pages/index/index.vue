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
			<view class="item-card" v-for="(item, index) in data" :key="index"
			@click="toPayChoice()"
			>
				<image :src="item.url" mode="aspectFit"></image>
				<text>{{item.name}}</text>
			</view>
		</view>
	</view>
	
</template>

<script setup>
	import {ref} from 'vue';
	const cityName = ref("武汉");
	const data = ref([
		{
			url: "/static/payitem/电费.png",
			name: "电费"
		},
		{
			url: "/static/payitem/水.png",
			name: "水费"
		},
		{
			url: "/static/payitem/火.png",
			name: "燃气费"
		},
		{
			url: "/static/payitem/电话.png",
			name: "固话费"
		}
	])
	
	// 与城市切换页面通信
	function changeCity() {
		uni.navigateTo({
			url:"/pages/city_choice/city",
			events: {
				acceptDataFromOpenedPage: function(data) {
					console.log(data);
					cityName.value = data.name;
				}
			}
		})	
	}
	function toPayChoice() {
		uni.navigateTo({
			url:"/pages/detail/pay_choice",
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
