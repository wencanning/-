<template>
	<view class="nav">
		<text v-for="(item, index) in data" :key="index"
		@click="btn(index)"
		:class="[index == idx ? 'km' : 'hm']"
		>{{item}}</text>
	</view>
</template>

<script setup>	
	import {ref} from 'vue';
	const idx = ref(0);
	const data = ([
		'推荐', '穿搭', '美食', '彩妆', '影视', '职场'
	])
	function btn(index) {
		idx.value = index;
	}
</script>

<style>
	.nav {
		display: flex;
		align-items: center;
		justify-content: space-around;
	}
		
	.km {
		background-color: #e9ecef;
		font-weight: bold;
		border-radius: 40px;
		padding: 3px 8px;
	}
	.hm {
		padding: 3px 8px;
	}
</style>