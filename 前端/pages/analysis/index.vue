<template>
	<view class="top">
		<view class="top-bar">
			<view class="bar-item" :class="{selected: billmod === 'month'}" @click="changeMod('month')">月账单</view>
			<view class="bar-item" :class="{selected: billmod === 'year'}" @click="changeMod('year')">年账单</view>
		</view>
		<view class="top-2">
			<view class="top-2-1">
				<picker
					:start="startDate" 
					:end="endDate" 
					:value="currentDate"  
					class="date" 
					mode="date" 
					fields="month"
					@change="dateChange"
					v-if="billmod === 'month'"
					>
					{{currentDate}}
				</picker>
				<picker
					:start="2000" 
					:end="2500" 
					:value="currentYear"  
					class="date" 
					mode="date" 
					fields="year"
					@change="dateChange"
					v-if="billmod === 'year'"
					>
					{{currentYear}}
				</picker>
				<image src="/static/icon/arrow_down.png"></image>
			</view>
			<view>
				支出
			</view>
		</view>
			
		<view class="top-3">
			共支出{{billnum}}笔, 合计
		</view>
		<view class="top-4">
			￥ {{moneySum.toFixed(2)}}
		</view>
	</view>
	<view class="body">
		<view class="body-1">
			支出对比
		</view>
		<view class="body-2">
			<qiun-data-charts 
			type="column"
			:opts="optsRef"
			:chart-data="chartDataRef"
			 :ontouch="true"
			/>
		</view>	
		<view class="rank-titke" v-if="billmod === 'month'">
			支出排行榜
		</view>
		<view class="body-4" v-if="billmod === 'month'">
			<view class="rank-item" v-for="(item, index) in ranklist" :key="index">
				<view>
					<text>{{index+1}}</text>
					<text class="rank-item-name">{{item.name}}</text>
				</view>
				<text class="rank-item-money">￥{{item.money.toFixed(2)}}</text>
			</view>
		</view>
	</view>
</template>

<script setup>
	import { ref, onMounted } from 'vue';
	import {onShow} from '@dcloudio/uni-app'
	import {requestApi,getUid} from '@/api/request.js';
	const currentDate = ref("2024-04");
	const currentYear = ref("2024");
	const startDate = ref("2023-1");
	const endDate = ref("2050-1");
	const billnum = ref(125);
	const moneySum = ref(3546.72);
	const billmod = ref('month');
	const moneyList = ref([]);
	const ranklist = ref([])
	const chartDataRef = ref({});
	const optsRef = ref({
	  color: ["#00B0FF"],
	  padding: [15,15,0,5], // 上右下左
	  enableScroll: true,
	  legend: {},
	  xAxis: {
	    disableGrid: true,
		gridColor: "#00B0FF",
		itemCount: 6
	  },
	  yAxis: {
	    disableGrid: true,
		disabled: true,
		data: [
	      {
	        min: 0,
			max: 40
	      }
	    ],
		splitNumber: 4
	  },
	  extra: {
	    column: {
	      type: "group",
	      width: 20,
	      activeBgColor: "#000000",
	      activeBgOpacity: 0.08
	    }
	  }
	});
	onShow(() => {
		getMoneyListByMonth(currentDate.value);
		getRank10(currentDate.value);
	});
	function changeMod(tab) {
	  billmod.value = tab;
	  if(billmod.value === 'month') {
		  console.log("222");
		  getMoneyListByMonth(currentDate.value);
		  getRank10(currentDate.value);
	  }else if(billmod.value === 'year') {
		  console.log("111");
		  getMoneyListByYear(currentYear.value);
	  }
	};
	function dateChange(event) {
		if(billmod.value === 'month') {
			currentDate.value = event.detail.value;
			getMoneyListByMonth(currentDate.value);
			getRank10(currentDate.value);
		}else if(billmod.value === 'year') {
			currentYear.value = event.detail.value;
			getMoneyListByYear(currentYear.value);
		}
	}
	async function getMoneyListByMonth(dateStr) {
		const uid = getUid();
		const data = await requestApi("/bill/chart/month", {dateStr:dateStr,uid:uid});
		const moneylist = data.moneylist;
		billnum.value = data.billnum;
		moneySum.value = data.mmoney;
		const res = {
			categories: ["1月","2月","3月","4月","5月","6月", "7月", "8月", "9月", "10月", "11月", "12月"],
			series: [
				{
					name: "消费金额",
					data: data.moneylist
				},
			]
		};
		let maxValue = Math.max(...moneylist);
		let minValue = Math.min(...moneylist);
		optsRef.value.yAxis.data[0].max = maxValue;
		optsRef.value.yAxis.data[0].min = minValue;
		chartDataRef.value = res;
	}
	async function getRank10(dateStr) {
		const uid = getUid();
		const data = await requestApi("/bill/rank10", {dateStr:dateStr, uid:uid});
		ranklist.value = data.ranklist;
	}

	async function getMoneyListByYear(dateStr) {
		const uid = getUid();
		const data = await requestApi("/bill/chart/year", {dateStr:dateStr,uid:uid});
		const moneylist = data.moneylist;
		billnum.value = data.billnum;
		moneySum.value = data.mmoney;
		let yearsArray = []; 
		const year = Number(dateStr);
		for (let i = year - 4; i <= year + 7; i++) {
		        yearsArray.push(i);
		}
		console.log(yearsArray);
		const res = {
			categories: yearsArray,
			series: [
				{
					name: "消费金额",
					data: moneylist
				},
			]
		};
		let maxValue = Math.max(...moneylist);
		let minValue = Math.min(...moneylist);
		optsRef.value.yAxis.data[0].max = maxValue;
		optsRef.value.yAxis.data[0].min = minValue;
		chartDataRef.value = res;
	}
</script>

<style>
		
	.top {
		background-color: #00B0FF;
		font-size: 40rpx;
	}
	.body {
		border-top-right-radius: 100rpx;
		border-top-left-radius: 100rpx;
		padding: 20rpx 40rpx;
	} 
	.top-bar {
		color: #dee2e6;
		display: flex;
		justify-content: space-around;
		margin: 10rpx 90rpx;
		padding: 20rpx 0;
	}
	.bar-item {
		margin-top: 20rpx;
		padding-bottom: 20rpx;
	}
	.selected {
		color: white;
		border-bottom: 5rpx solid white;
	}
	.top-2 {
		color: white;
		display: flex;
		justify-content: space-between;
		padding: 20rpx 40rpx;
		margin-bottom: 12rpx;
	}
	.top-3 {
		padding: 10rpx 40rpx;
		color: #dee2e6;
		font-size: 32rpx;
	}

	.top-4 {
		color: white;
		font-size: 60rpx;
		padding: 10rpx 28rpx;
		margin-top: 20rpx;
		padding-bottom: 60rpx;
	}
	.top-2 image {
		width: 45rpx;
		height: 45rpx;
	}
	.top-2 picker {
		margin-right: 10rpx;
	}
	.top-2-1 {
		display: flex;
	}
	.body-2 {
	  width: 100%;
	  height: 400rpx;
	}
	.body-1 {
	}
	.rank-titke {
		
	}
	.rank-item {
		display: flex;
		align-items: center;
		justify-content: space-between;
		gap: 30rpx;
		margin-top: 40rpx;
	}
	.rank-item-money {
		font-weight: bold;
	}
	.rank-item-name {
		margin-left: 60rpx;
	}
</style>