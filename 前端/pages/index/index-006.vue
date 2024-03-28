<template>
	<swiper
	indicator-dots
	autoplay
	circular>
		<swiper-item v-for="(item, index) in data" :key="index">
			<image :src="item.url"></image>
		</swiper-item>
	</swiper>
</template>

<script setup>
	import {ref} from 'vue';
	const data = ([
		{
			url: '/static/logo.png'
		},
		{
			url: '/static/lufei.jpg'
		},
		{
			url: '/static/assqx-6ntff-001.ico'
		}
	])
</script>

<style>
</style>