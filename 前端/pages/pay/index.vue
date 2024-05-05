<template>
	<view class="container">
		<view class="pay-body">
			<view class="p-1">
				<image class="pay-img" mode="aspectFit" :src="serv.img_url"></image>
			</view>
			<view class="pay-money-detail">
				<view class="pay-money-item">
					<text class="money-title">应缴金额:</text>
					<text class="money-num">{{payItem.amount_due.toFixed(2)}}元</text>
				</view>
				<view class="pay-money-item-2">
					<text class="money-title">当前余额:</text>
					<text class="money-num">{{payItem.current_balance.toFixed(2)}}元</text>
				</view>
			</view>
			<view class="pay-user-detail">
				<view class="pay-user-item">
					<text class="item-title">缴费户名:</text>
					<text class="item-name">{{payItem.account_name}}</text>
				</view>
				<view class="pay-user-item">
					<text class="item-title">缴费户号:</text>
					<text class="item-name">{{household_number}}</text>
				</view>
				<view class="pay-user-item">
					<text class="item-title">收款单位:</text>
					<text class="item-name">{{comp.name}}</text>
				</view>
			</view>
			<view class="pay-input-money">
				<view class="input-title">输入金额</view>
				<view class="input-body">
					<text class="input-sig">¥ </text>
					<input class="input-money"
					placeholder-style="color:#ced4da" 
					placeholder="请输入缴费金额" 
					type="number"
					@input="onInputOfNum"
					/>
				</view>
			</view>
		</view>
		<button
		 class="btn-l btn-pay"
		 @click="btnPay"
		 >
			立即缴费
		</button>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	import {requestApi,getToken,navigateTo, getUid, showToast} from '@/api/request.js';
	import {onShow, onLoad} from '@dcloudio/uni-app';
	
	const servId = ref(0);
	const compId = ref(0);
	const household_number = ref('');
	const serv = ref();
	const comp = ref();
	const payItem = ref();
	let payNum = 0;	
	onLoad((option)=> {
		console.log("option", option);
		servId.value = Number(option.servId);
		compId.value = Number(option.compId);
		household_number.value = option.household_number;
	});
	onShow(async()=>{
		const compdata = await requestApi("/company/id", {id:compId.value});
		const servdata = await requestApi("/serv/id",{id:servId.value});
		const paydata = await requestApi("/bill/getpay",{uid:getUid(),comid:compId.value});
		
		payItem.value = paydata.payItem;
		serv.value = servdata.serv;
		comp.value = compdata.company;
	});
	function onInputOfNum(e) {
		payNum = parseFloat(e.detail.value);
	}
	async function btnPay() {
		const data = await requestApi("/bill/pay", {uid:getUid(),compid:compId.value, paynum:payNum}, 'POST');
		console.log(data.flag);
		if(data.flag === true) {
			navigateTo("/pay/success");
		}else {
			showToast("支付失败");
		}
	}
</script>

<style>
	page {
		background-color: #f1f3f5;
	}
	.container {
		padding: 60rpx;
	}
	.pay-body {
		background-color: white;
		padding: 20rpx 32rpx;
		padding-bottom: 60rpx;
	}
	.pay-img {
		width: 150rpx;
		height: 150rpx;
	}
	.p-1 {
		display: flex;
		justify-content: space-around;
		margin-bottom: 30rpx;
	}
	.pay-money-detail {
		/* font-weight: 600; */ 
		padding: 0 20rpx;
		border-bottom: 4rpx solid #f1f3f5;
	}
	.money-title {
		color: #868e96;
		margin-right: 90rpx;
	}
	.money-num {
		font-weight: bold;
	}
	.pay-money-item {
		margin-bottom: 30rpx;
	}
	.pay-money-item-2 {
		padding-bottom: 50rpx;
	}
	.pay-user-detail {
		/* font-weight: 600; */ 
		margin-top: 50rpx;
		padding: 0 20rpx;
	}
	.pay-user-item {
		margin-bottom: 30rpx;
	}
	.item-title {
		color: #868e96;
		margin-right: 90rpx;
	}
	.item-name {
		font-weight: bold;
	}
	.pay-input-money {
		border-bottom: 4rpx solid #f1f3f5;
		padding-bottom: 28rpx;
	}
	.input-title {
		margin-bottom: 30rpx;
	}
	.input-sig {
		font-size: 52rpx;
		font-weight: bold;
	}
	.input-body {
		display: flex;
		align-items: center;
		gap: 28rpx;
	}
	.btn-pay {
		margin-top: 90rpx;
	}
	.btn-l {
		width: 100%;
		font-size: 36rpx;
		color: white;
		background-color: #00B0FF;
	/* 	border-radius: 60rpx; */
	}
</style>