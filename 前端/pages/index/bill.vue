<template>
	<view class="tobar" style="border-bottom: 1rpx solid #dee2e6;" >
		<view class="export">账单导出</view>
		<view class="tobar-1">
			<text>统计</text>
			<image src="/static/icon/right_arrow.png"></image>
		</view>
		
	</view>
	<view class="tobar">
		<uni-datetime-picker type="date" class="date" v-model="single">
			<text>{{single}}</text>
		</uni-datetime-picker>
		<view class="pay">支出 ¥{{money}}</view>
	</view>
	<view class="bill">
		<view class="bill-item" v-for="(item, index) in data" :key="index">
			<view class="item-detil">
				<text class="item-name">{{item.name}}</text>
				<text class="item-date">{{item.date}}</text>
			</view>
			<view class="item-money">
				- {{item.money}}
			</view>
		</view>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	const year = ref(2024);
	const month = ref(3);
	const money = ref(0);
	const single = ref("2024-3-1");
	const data = ref([
		{
			name: "爱情麻辣烫",
			date: "3月20日 17:18",
			money: "24.00",
		},
		{
			name: "爱情麻辣烫",
			date: "3月20日 17:18",
			money: "24.00",
		},
		{
			name: "爱情麻辣烫",
			date: "3月20日 17:18",
			money: "24.00",
		},
		{
			name: "爱情麻辣烫",
			date: "3月20日 17:18",
			money: "24.00",
		}
	])
</script>

<style>
	.export {
		padding: 16rpx 26rpx;
		background-color: #dee2e6;
		border-radius: 40rpx;
		font-size: 32rpx;
		letter-spacing: 2rpx;
	}
	.tobar {
		background-color: #f1f3f5;
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 34rpx 24rpx;
	}
	.tobar-1 {
		display: flex;
		align-items: center;
		justify-content: space-between;
		
		font-size: 32rpx;
		color: #8a8a8a;
	}
	.tobar image {
		height: 60rpx;
		width: 60rpx;
	}
	.date {
		font-weight: 600;
		font-size: 40rpx;
	}
	.pay {
		font-weight: 300;
		font-size: 32rpx;
	}
	.bill {
		display: flex;
		flex-direction: column;
	}
	.item-detil {
		display: flex;
		flex-direction: column;
		line-height: 56rpx;
	}
	.bill-item {
		display: flex;
		align-items: center;
		justify-content: space-between;
		
		padding: 10rpx 30rpx;
		border-bottom: 4rpx solid #f1f3f5;
	}
	.item-name {
		font-size: 40rpx;
		letter-spacing: 3rpx;
		font-weight: 500;
		margin-bottom: 10rpx;
	}
	.item-date {
		font-size: 32rpx;
		font-weight: 300;
	}
	.item-money {
		font-weight: bold;
		font-size: 40rpx;
	}
</style>