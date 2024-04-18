<template>
	<view class="usr-login">
		<image mode="aspectFit" class="usr-img" src="/static/icon/默认头像.png"></image>
		<text>生活缴费小程序</text>
		<button class="login-btn"  @click="toLogin">手机号登录</button>
		<!-- <view class="usr-phone usr-input">
			<text>手机号</text>
			<input value="请输入电话号码" type="tel"/>
		</view>
		<view class="usr-messg">
			
		</view> -->
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	function toLogin() {
		uni.navigateTo({
			url:"/pages/login/login",
		})
	}
</script>

<style>
	.login-btn {
		margin-top: 150rpx;
		color: white;
		background-color: #00B0FF;
		width: 100%;
		border-radius: 60rpx;
	}
	.usr-login {
		display: flex;
		flex-direction: column;
		align-items: center;
		
		margin-top: 100rpx;
		padding: 0 50rpx;
		gap: 20rpx;
	}
	.usr-img {
		height: 75px;		
	}
	.usr-input {
		padding-left: 30rpx;
		width: 100%;
		display: flex;
		justify-content: space-around;
	}
</style>