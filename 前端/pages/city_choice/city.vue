<template>
	<view class="container">
		<uni-indexed-list class="indexed-list" 
			:options="citylist" 
			:show-select="false" 
			@click="bindClick" />
	</view>
</template>

<script setup>
	import {onLoad, onShow}	from '@dcloudio/uni-app';
	import {ref, getCurrentInstance} from 'vue';
	import {requestApi} from '@/api/request.js';
	const citylist = ref([]);
	const cityst = ref([]);
	let eventChannel;
	onLoad((option)=>{
		eventChannel = getCurrentInstance().proxy.getOpenerEventChannel();
		console.log("eventChannel",eventChannel);
	});			
	onShow(async()=>{
		console.log('请求城市数据');
		const cityData = await requestApi('/city');
		cityst.value = cityData.citylist;
		console.log('请求的城市数据',cityst);
		initCityList();
	});
	function initCityList() {
		let list = [];
		let categorizedData = {};
		cityst.value.forEach(item => {
			let firstletter = item.letter;
			if(!categorizedData[firstletter]) {
				categorizedData[firstletter] = 1;
				list.push({letter: firstletter, data: []});
			}
			list.forEach(item2 => {
				if(item2.letter == firstletter) {
					item2.data.push(item.name);
				}
			})
		});
		citylist.value = list;
		console.log(citylist);
	}
	function getCityId(name) {
		let id;
		cityst.value.forEach(item => {
			if(name == item.name) {
				id = item.id;
			}
		})
		console.log("id", id);
		return id;
	}
	function bindClick(obj) {
		let cname = obj.item.name;
		let cid = getCityId(cname);
		eventChannel.emit('acceptDataFromOpenedPage', {name: cname,city_id: cid});
		uni.navigateBack();
	}

</script>

<style>
	.container {
		/* display: flex;
		flex-direction: column; */
	}
	
	.indexed-list {
		font-size: 48rpx;
	}
</style>