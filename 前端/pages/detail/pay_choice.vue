<template>
	<view class="container">
		<view class="nav">
			<text>缴费项目</text>
			<text>城市</text>
		</view>
		<view class="company">
			<view class="com-item" 
			v-for="(item, index) in company" 
			:key="index"
			@click="toPay()"
			>
				<text>{{item.name}}</text>
			</view>
		</view>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	const company = ref([
		{
			name: '武汉燃气有限公司'
		},
		{
			name: '汉口燃气有限公司'
		},
		{
			name: '武昌燃气有限公司'
		}
	])
	function toPay() {
		uni.navigateTo({
			url: "/pages/detail/pay"
		})
	}
</script>

<style>
	.container {
		padding: 0 25rpx;
	}
	.nav {
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 40rpx 20rpx;
		padding-bottom: 50rpx;
		border-bottom: 4rpx solid #f1f3f5;
		font-size: 30rpx;
		letter-spacing: 2rpx;
	}
	
	.company {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	.com-item {
		padding: 40rpx 20rpx;
		border-bottom: 4rpx solid #f1f3f5;
		font-weight: bold;
		letter-spacing: 2rpx;
		font-size: 38rpx;
	}
</style>