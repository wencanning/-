<template>
	<view class="container">
		<view class="mess-1">
			短信验证登录
		</view>
		
		<view class="mess-2">
			未注册的手机号验证通过后, 将自动创建账号
		</view>
		
		<view class="v-2">
			<text>手机号: </text>
			<view class="input-border">
				<input class="tel-input" 
				placeholder-style="color:#ced4da" 
				placeholder="请输入手机号" 
				type="tel"
				@input="onInputOfPhone"
				/>
			</view>
		</view>
		<view class="v-3">
			<text>验证码: </text>
			<view class="input-border">
				<input 
				class="tel-input" 
				placeholder-style="color:#ced4da" 
				placeholder="请输入6位验证码" 
				type="number"
				@input="onInputOfMess"
				/>
			</view>
		</view>
		<button
		 class="btn-l btn-send"
		 @click="btnSend"
		 >
			获取短信验证码
		</button>
		<button
		 class="btn-l btn-login"
		 @click="btnLogin"
		 >
			登录
		</button>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	import {requestApi} from '@/api/request.js';
	let phone_num = "";
	let messg_num = "";
	const messg_rel = ref();
	function onInputOfPhone(e) {
		phone_num = e.detail;
	}
	function onInputOfMess(e) {
		messg_num = e.detail;
	}	
	function btnLogin() {
		wx.showLoading({title:'登录中'})
		wx.login({
			success:async(res)=> {
				const userData = await requestApi('/login', 	
				{
					phone: phone_num.value,
					message: messg_num.value
				},
				'POST'
				);
				console.log(userData);
				wx.setStorageSync('userInfo', userData.data);
				wx.hideLoading();
				wx.switchTab({
				  url: '/pages/index/index', // 替换成你的 tabBar 页面路径
				})
			}
		})
	}
</script>

<style>
	.v-2 {
		display: flex;
		gap: 20rpx;
		margin-top: 80rpx;
	}
		
	.v-3 {
		margin-top: 20rpx;
		display: flex;
		gap: 20rpx;
	}
		
	.input-border {
		padding-bottom: 20rpx;
		border-bottom: 0.8rpx solid #e9ecef;
	}
	
	.tel-input {
		
	}
	.container {
		padding: 75rpx 50rpx;
		letter-spacing: 2rpx;
	}
	.mess-1 {
		font-weight: bold;
		font-size: 56rpx;
		letter-spacing: 3rpx;
	}
	.mess-2 {
		margin-top: 20rpx;
		color: #8a8a8a;
		font-size: 28rpx;
		/* letter-spacing: 2rpx; */
	}
	.btn-send {
		margin-top: 120rpx;
	}
	.btn-login {
		margin-top: 40rpx;
	}
	.btn-l {
		width: 100%;
		font-size: 36rpx;
		color: white;
		background-color: #00B0FF;
		border-radius: 60rpx;
	}
</style>