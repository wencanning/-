<template>
	<button size="mini" :plain="true" @click="btn">点击显示张三</button>
	<text v-if="show">张三</text>
</template>

<script setup>
	import {ref} from 'vue';
	const show = ref(false);
	function btn() {
		show.value = true;
	}
</script>

<style>
</style>