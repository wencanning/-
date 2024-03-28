<template>
	<view class="container">
		<uni-indexed-list class="indexed-list" 
			:options="citylist" 
			:show-select="false" 
			@click="bindClick" />
	</view>
</template>

<script setup>
	import {onLoad}	from '@dcloudio/uni-app';
	import {ref, getCurrentInstance} from 'vue';
	const citylist = ([{
							"letter": "A",
							"data": [
								"阿克苏机场",
								"阿拉山口机场",
								"阿勒泰机场",
								"阿里昆莎机场",
								"安庆天柱山机场",
								"澳门国际机场"
							]
						}, {
							"letter": "B",
							"data": [
								"保山机场",
								"包头机场",
								"北海福成机场",
								"北京南苑机场",
								"北京首都国际机场"
							]
						}]);
	let eventChannel;
	
	onLoad((option)=>{
		eventChannel = getCurrentInstance().proxy.getOpenerEventChannel();
		console.log(eventChannel);
	});			
	
	function bindClick(obj) {
		eventChannel.emit('acceptDataFromOpenedPage', { name: obj.item.name } );
		uni.navigateBack();
	}

</script>

<style>
	.container {
		/* display: flex;
		flex-direction: column; */
	}
	
	.indexed-list {
		/* margin-top: 100px; */
	}
</style>