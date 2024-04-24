<template>
	<view class="container">
		<view class="nav">
			<text>缴费项目</text>
			<text>{{cityName}}</text>
		</view>
		<view class="company">
			<view class="com-item" 
			v-for="(item, index) in company" 
			:key="index"
			@click="toPay(item.id)"
			>
				<text>{{item.name}}</text>
			</view>
		</view>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	import {requestApi} from '@/api/request.js';
	import {onLoad, onShow}	from '@dcloudio/uni-app';
	const company = ref([]);
	const cityName = ref();
	const servId = ref(1);
	const cityId = ref(1);
	onLoad((option)=> {
		console.log(option);
		servId.value = Number(option.servId);
		cityId.value = Number(option.cityId);
		cityName.value = option.cityName;
	});
	onShow(async()=>{
		const data = await requestApi("/company",{s_id:servId.value,c_id:cityId.value});
		company.value = data.company;
		console.log(company.value);
	});
	function toPay(compId) {
		console.log("compId",compId);
		console.log("servId", servId.value);
		console.log("cityId", cityId.value);
		uni.navigateTo({
			url:"/pages/detail/pay?"+"compId="+compId+"&servId="+servId.value+"&cityId="+cityId.value,
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