"use strict";
const common_vendor = require("../../common/vendor.js");
const api_request = require("../../api/request.js");
const _sfc_main = {
  __name: "bill",
  setup(__props) {
    const startDate = common_vendor.ref("2023-1");
    const endDate = common_vendor.ref("2050-1");
    const currentDate = common_vendor.ref("2024-04");
    const billdata = common_vendor.ref([]);
    const userId = common_vendor.ref("");
    common_vendor.onShow(() => {
      getData(currentDate.value);
      const userInfo = common_vendor.wx$1.getStorageSync("userInfo");
      userId.value = userInfo.user.id;
    });
    function dateChange(event) {
      currentDate.value = event.detail.value;
      console.log("currentDate", currentDate);
      getData(currentDate.value);
    }
    function getbill() {
      console.log("getbill!");
      common_vendor.wx$1.downloadFile({
        url: "http://localhost:8080/bill/getpdf?uid=" + userId.value,
        header: {
          "token": api_request.getToken()
        },
        success(res) {
          console.log(res);
          if (res.statusCode === 200) {
            console.log("下载文件成功");
            common_vendor.wx$1.openDocument({
              filePath: res.tempFilePath,
              success: function(res2) {
                console.log("打开文件成功");
              }
            });
          }
        }
      });
    }
    async function getData(date) {
      const userInfo = common_vendor.wx$1.getStorageSync("userInfo");
      userId.value = userInfo.user.id;
      const data = await api_request.requestApi("/bill/ym", { dateStr: date, uid: userId.value });
      billdata.value = data.bill;
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(getbill),
        b: common_vendor.t(currentDate.value),
        c: startDate.value,
        d: endDate.value,
        e: currentDate.value,
        f: common_vendor.o(dateChange),
        g: common_vendor.t(_ctx.money),
        h: common_vendor.f(billdata.value, (item, index, i0) => {
          return {
            a: common_vendor.t(item.name),
            b: common_vendor.t(item.year),
            c: common_vendor.t(item.month),
            d: common_vendor.t(item.day),
            e: common_vendor.t(item.hour),
            f: common_vendor.t(item.minute),
            g: common_vendor.t(item.money),
            h: index
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/index/bill.vue"]]);
wx.createPage(MiniProgramPage);
