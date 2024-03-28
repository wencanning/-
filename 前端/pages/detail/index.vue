<template>
	<text>{{data[idx]}}</text>
</template>

<script setup>
	import {ref} from 'vue';
	import {onLoad}	from '@dcloudio/uni-app'
	
	const data = ref([
		'第一张图',
		'第二张图',
		'第三张图'
	]);
	const idx = ref(0);
	onLoad((event)=>{
		idx.value = Number(event.index);
	})
</script>

<style>
</style>