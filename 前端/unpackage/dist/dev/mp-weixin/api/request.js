"use strict";
const common_vendor = require("../common/vendor.js");
const baseUrl = "http://localhost:8080";
function requestApi(url, data = {}, method = "GET") {
  console.log(data);
  return new Promise((resolev, reject) => {
    common_vendor.wx$1.request({
      url: baseUrl + url,
      method,
      data,
      header: {
        "content-type": "application/json"
      },
      success: (res) => {
        resolev(res.data);
      },
      fail: (err) => {
        console.log(err);
        resolve("fail");
      }
    });
  });
}
exports.requestApi = requestApi;
