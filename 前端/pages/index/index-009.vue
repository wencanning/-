<template>
	<input placeholder="输入框">
	
	<Swiper :swiperData='swiperData'/>
</template>

<script setup>
	import {ref} from 'vue';
	import Swiper from '@/component/swiper.vue';
	
	const swiperData = ref([
		{
			url: "/static/logo.png"
		},
		{
			url: "/static/lufei.jpg"
		},
		{
			url: "/static/assqx-6ntff-001.ico"
		}
	]) 
</script>

<style>
	input {
		background-color: antiquewhite;
	}
</style>