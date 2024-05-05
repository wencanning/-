<template>
	<view class="container">
		<view class="tocenter">
			<image class="success-img" mode="aspectFit" src="../../static/icon/成功.png"></image>
		</view>
		<view class="tocenter">
			<text class="messg-1">
				缴费成功
			</text>
		</view>
		
		<button
		 class="btn-l"
		 @click="btnFinish"
		 >
			完成
		</button>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	import {requestApi,getToken,navigateTo, getUid, showToast} from '@/api/request.js';
	function btnFinish() {
		wx.switchTab({
		  url: '/pages/index/index', 
		})
	}
</script>

<style>
	.container {
		padding: 0rpx 20rpx
	}
	.tocenter {
		display: flex;
		justify-content: space-around;
	}
	.success-img {
		margin-top: 140rpx;
		margin-bottom: 50rpx;
		width: 220rpx;
		height: 220rpx;
	}
	.messg-1 {
		font-size: 44rpx;
	}
	.btn-pay {
		margin-top: 90rpx;
	}
	.btn-l {
		margin-top: 550rpx;
		width: 40%;
		font-size: 36rpx;
		color: #00B0FF;
		background-color: white;
		border: 2rpx solid #00B0FF;
	/* 	border-radius: 60rpx; */
	}
</style>