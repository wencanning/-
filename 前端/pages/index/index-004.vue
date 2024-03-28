<template>
	<view class="nickname">
		<image src="/static/lufei.jpg" :mode="mod"></image>
		<text>昵称</text>
	</view>
	<button @click="btn">更改样式</button>
</template>

<script setup>
	import {ref} from 'vue';
	const mod = ref('aspectFit');
	
	function btn() {
		mod.value = 'bottom'; 
		log("111")
	}
</script>

<style>
	.nickname image {
		width: 50px;
		height: 50px;
		border-radius: 50%;
	}
</style>