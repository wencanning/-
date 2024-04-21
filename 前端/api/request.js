const baseUrl = 'http://localhost:8080';

//发送请求

function requestApi(url,data={},method='GET') {
	console.log(data);
	return new Promise((resolev, reject)=>{
		wx.request({
			url:baseUrl+url,
			method:method,
			data:data,
			header:{
				'content-type': 'application/json' 
			},
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