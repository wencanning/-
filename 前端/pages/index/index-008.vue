<template>
	<input placeholder="请输入字母" v-model="value1">
	<text>转换为大写字母: {{res1}}</text>
	<input placeholder="请输入数字" v-model="value2">
	<text>计算结果: {{res2}}</text>
</template>

<script setup>
	import {ref, computed} from 'vue';
	const value1 = ref('');
	const value2 = ref(0);
	
	const res1 = computed(()=>{
		return value1.value.toUpperCase();
	})
	const res2 = computed(()=>{
		return value2.value * 2;
	})
</script>

<style>
	input {
		margin-bottom: 10px;
		background-color: antiquewhite;
	}
</style>