<template>
	<view class="container">
		<view class="user-card">
			<image mode="aspectFit" :src="img_url"></image>
			<text>{{userName}}</text>
		</view>
		<view class="user-app">
			<view class="app-item">
				<image mode="aspectFill" src="../../static/icon/账单.png"></image>
				<view class="app-detail" @click="toBill">
					<text>我的账单</text>
					<image mode="aspectFill" src="../../static/icon/right_arrow.png"></image>
				</view>
			</view>
			<view class="app-item">
				<image mode="aspectFill" src="../../static/icon/设置.png"></image>
				<view class="app-detail">
					<text>个人设置</text>
					<image class="img-set" mode="aspectFill" src="../../static/icon/right_arrow.png"></image>
				</view>
			</view>
			<button class="btn-l" @click="changeUser">切换用户</button>
		</view>
	</view>
	
</template>

<script setup>
	import {ref} from 'vue';
	import {requestApi} from '@/api/request.js';
	import {onLoad, onShow}	from '@dcloudio/uni-app';
	const userName = ref('用户名');
	const img_url = ref('/static/icon/账单.png');
	const userId = ref();
	const phone = ref();
	onShow(()=>{
		const userInfo = wx.getStorageSync('userInfo');
		if(userInfo) {
			userName.value = userInfo.user.username;
			img_url.value = userInfo.user.img_url;
			userId.value = userInfo.id;
			phone.value = userInfo.phonenumber;
		}
	})
	function toBill() {
		wx.navigateTo({
			url: "/pages/index/bill"
		});
	}
	function changeUser() {
		wx.navigateTo({
			url: "/pages/login/index"
		})
	}
</script>

<style>
	page {
		background-color: #f1f3f5;
	}
	.btn-l {
		margin-top: 300rpx;
		
		width: 100%;
		font-size: 38rpx;
		color: black;
		background-color: #f1f3f5;
		border-radius: 60rpx;
	}
	.container {
		padding: 50rpx 20rpx;
		letter-spacing: 2rpx;
	}

	.user-card {
		display: flex;
		padding: 20rpx 25rpx;
		background-color: white;
		border-radius: 25rpx;
		align-items: center;
	}
	.user-card image {
		height: 150rpx;
		width: 200rpx;
	}
	.user-card text {
		font-weight: bold;
	}
	.user-app {
		margin-top: 20rpx;
		height: 1000rpx;
		background-color: white;
		padding-top: 25rpx;
		padding-left: 25rpx;
		padding-right: 25rpx;
	}
	.app-item {
		display: flex;
		margin-top: 15rpx;
	}
	.app-item image {
		width: 65rpx;
		height: 65rpx;
		
	}
	
	.app-item text {
		color: #707070;
	}
	.img-set {
		width: 75rpx;
		height: 75rpx;
	}
	.app-detail {
		display: flex;
		margin-left: 40rpx;
		padding-bottom: 40rpx;
		border-bottom: 4rpx solid #f1f3f5;
		justify-content: space-between;
		width: 100%;
		font-size: 40rpx;
	}
</style>