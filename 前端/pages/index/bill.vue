<template>
	<view class="tobar" style="border-bottom: 1rpx solid #dee2e6;" >
		<view class="export" @click="getbill">账单导出</view>
		<view class="tobar-1" @click="navigateTo('/analysis/index')">
			<text>统计</text>
			<image src="/static/icon/right_arrow.png"></image>
		</view>
	</view>
	<view class="tobar">
		<picker 
			:start="startDate" 
			:end="endDate" 
			:value="currentDate"  
			class="date" 
			mode="date" 
			fields="month"
			@change="dateChange"
			>
			{{currentDate}}
			</picker>
		<view class="pay">支出 ¥{{money}}</view>
	</view>
	<view class="bill">
		<view class="bill-item" v-for="(item, index) in billdata" :key="index">
			<view class="item-detil">
				<text class="item-name">{{item.name}}</text>
				<text class="item-date">
					{{item.year}}年{{item.month}}月{{item.day}}日 {{item.hour}}:{{item.minute}}
				</text>
			</view>
			<view class="item-money">
				- {{item.money}}
			</view>
		</view>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	import {requestApi,getToken,navigateTo} from '@/api/request.js';
	import {onShow} from '@dcloudio/uni-app';
	const startDate = ref("2023-1");
	const endDate = ref("2050-1");
	const currentDate = ref("2024-04");
	const billdata = ref([])
	const userId = ref('');
	onShow(()=>{
		getData(currentDate.value);
		const userInfo = wx.getStorageSync('userInfo');
		userId.value = userInfo.user.id;
	});
	function dateChange(event) {
		currentDate.value = event.detail.value;
		console.log("currentDate",  currentDate);
		getData(currentDate.value);
	}
	function getbill() {
		console.log("getbill!");
		wx.downloadFile({
		  url:'http://localhost:8080/bill/getpdf?'+"uid="+userId.value,
		  header:{
			  'token':getToken()
		  },
		  success (res) {
		    // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调，业务需要自行判断是否下载到了想要的内容
		    console.log(res);	
			if (res.statusCode === 200) {
				console.log("下载文件成功");
				wx.openDocument({
					filePath: res.tempFilePath,
					success: function (res) {
						console.log('打开文件成功');
					}
				});
		    }
		  }
		})
	}
	async function getData(date) {
		const userInfo = wx.getStorageSync('userInfo');
		userId.value = userInfo.user.id;
		const data = await requestApi("/bill/ym", {dateStr:date,uid:userId.value});
		billdata.value = data.bill;
	}
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