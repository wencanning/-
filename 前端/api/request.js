// import {resolve} from "node:path/posix";
const baseUrl = 'http://localhost:8080';

//发送请求

function requestApi(url,data={},method='GET') {
	return new Promise((resolev, reject)=>{
		wx.request({
			url:baseUrl+url,
			method,
			data,
			success:(res)=>{
				resolev(res.data);
			},
			fail:(err)=>{
				console.log(err);
				resolve("fail");
			}
		})
	})
}

export{requestApi}