<template>
	<view class="container">
		<view class="nav">
			<view class="nav-1">
				<image :src="serv.img_url"></image>
				<text>{{serv.name}}</text>
			</view>
			<text class="nav-city">{{city.name}}</text>
		</view>
		<view class="pay-card">
			<view class="card-item">
				<view class="card-1">缴费单位</view>
				<view class="card-2">{{comp.name}}</view>
			</view>
			<view class="card-item">
				<view class="card-1">缴费户号</view>
				<view class="card-2">
					<input
					placeholder-style="color:#ced4da" 
					placeholder="请输入缴费户号" 
					type="text"
					@input="onInputOfMess"
					/>
				</view>
			</view>
		</view>
		<button
		 class="btn-l btn-send"
		 @click="btn-send"
		 >
			查询并缴费
		</button>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	import {requestApi} from '@/api/request.js';
	import {onLoad, onShow}	from '@dcloudio/uni-app';
	const servId = ref(1);
	const cityId = ref(1);
	const compId = ref(1);
	const serv = ref();
	const city = ref();
	const comp = ref();
	let household_number = "";
	onLoad((option)=> {
		console.log("option", option);
		servId.value = Number(option.servId);
		cityId.value = Number(option.cityId);
		compId.value = Number(option.compId);
	});
	onShow(async()=>{
		const compdata = await requestApi("/company/id", {id:compId.value});
		const servdata = await requestApi("/serv/id",{id:servId.value});
		const citydata = await requestApi("/city/"+cityId.value);
		city.value = citydata.city;
		serv.value = servdata.serv;
		comp.value = compdata.company;
	});
	function onInputOfMess(e) {
		household_number = e.detail;
	}
</script>

<style>
	page {
		background-color: #f1f3f5;
	}
	.card-1 {
		font-size: 32rpx;
		margin-bottom: 36rpx;
	}
	.card-2{
		font-weight: bold;
		padding-bottom: 26rpx;
		border-bottom: 4rpx solid #f1f3f5;
	}
	.card-item input {
		font-weight: 400;
	}
	.card-item {
		margin-bottom: 56rpx;
	}
	.container {
		padding: 0 25rpx;
		letter-spacing: 2rpx;
	}
	.nav {
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding-top: 40rpx;
		padding-bottom: 50rpx;
	}
	.nav image {
		height: 70rpx;
		width: 70rpx;
	}
	
	.nav-city {
		font-size: 30rpx;
	}
		
	.nav-1 {
		display: flex;
		align-items: center;
		justify-content: space-between;
		gap: 10rpx;
		font-weight: bold;
		font-size: 38rpx;
	}

	.pay-card {
		background-color: #fff;
		border-radius: 10rpx;
		box-shadow: 0 0 50rpx rbga(0, 0, 0, 0.1);
		display: flex;
		flex-direction: column;
		padding: 40rpx 45rpx;
	}
	.btn-l {
		width: 100%;
		font-size: 40rpx;
		color: white;
		background-color: #00B0FF;
		border-radius: 10rpx;
	}
	.btn-send {
		margin-top: 64rpx;
	}
</style>