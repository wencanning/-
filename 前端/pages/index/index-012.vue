<template>
	<view class="card-view">
		<view v-for="(item, index) in data" :key="index">
			<image  :src="item.url" mode="aspectFill"></image>
			<text>{{item.name}}</text>
			<text>{{item.price}}</text>
			<text v-if="item.jd">京东物流</text>
		</view>
	</view>
</template>

<script setup>
	import {ref} from 'vue';
	const data = ([
		{
			url: '/static/lufei.jpg',
			name: '优衣库',
			price: '300',
			jd: false
		},
		{
			url: '/static/lufei.jpg',
			name: '优衣库',
			price: '300',
			jd: true
		},
		{
			url: '/static/lufei.jpg',
			name: '优衣库',
			price: '300',
			jd: false
		}
	])
</script>

<style>
	page {
		background-color: #f7f7f7;
	}
	text {
		display: block;
	}
	.card-view {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		padding: 0 10px;
	}
	.card-view image {
		width: 100%;
		height: 100px;
		border-radius: 10px;
	}
	.card-view view {
		margin: 10px 10px;
		background-color: #fff;
	}
</style>