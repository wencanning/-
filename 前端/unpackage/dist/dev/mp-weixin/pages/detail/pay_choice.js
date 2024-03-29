"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  __name: "pay_choice",
  setup(__props) {
    const company = common_vendor.ref([
      {
        name: "武汉燃气有限公司"
      },
      {
        name: "汉口燃气有限公司"
      },
      {
        name: "武昌燃气有限公司"
      }
    ]);
    function toPay() {
      common_vendor.index.navigateTo({
        url: "/pages/detail/pay"
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(company.value, (item, index, i0) => {
          return {
            a: common_vendor.t(item.name),
            b: index,
            c: common_vendor.o(($event) => toPay(), index)
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/detail/pay_choice.vue"]]);
wx.createPage(MiniProgramPage);
