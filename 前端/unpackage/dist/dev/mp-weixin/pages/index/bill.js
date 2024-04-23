"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  __name: "bill",
  setup(__props) {
    const startDate = common_vendor.ref("2023-1");
    const endDate = common_vendor.ref("2050-1");
    const currentDate = common_vendor.ref("2024-04");
    const data = common_vendor.ref([
      {
        name: "爱情麻辣烫",
        date: "3月20日 17:18",
        money: "24.00"
      },
      {
        name: "爱情麻辣烫",
        date: "3月20日 17:18",
        money: "24.00"
      },
      {
        name: "爱情麻辣烫",
        date: "3月20日 17:18",
        money: "24.00"
      },
      {
        name: "爱情麻辣烫",
        date: "3月20日 17:18",
        money: "24.00"
      }
    ]);
    function dateChange(event) {
      currentDate.value = event.detail.value;
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.t(currentDate.value),
        b: startDate.value,
        c: endDate.value,
        d: currentDate.value,
        e: common_vendor.o(dateChange),
        f: common_vendor.t(_ctx.money),
        g: common_vendor.f(data.value, (item, index, i0) => {
          return {
            a: common_vendor.t(item.name),
            b: common_vendor.t(item.date),
            c: common_vendor.t(item.money),
            d: index
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/index/bill.vue"]]);
wx.createPage(MiniProgramPage);
