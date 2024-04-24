"use strict";
const common_vendor = require("../../common/vendor.js");
const api_request = require("../../api/request.js");
const _sfc_main = {
  __name: "pay_choice",
  setup(__props) {
    const company = common_vendor.ref([]);
    const cityName = common_vendor.ref();
    const servId = common_vendor.ref(1);
    const cityId = common_vendor.ref(1);
    common_vendor.onLoad((option) => {
      console.log(option);
      servId.value = Number(option.servId);
      cityId.value = Number(option.cityId);
      cityName.value = option.cityName;
    });
    common_vendor.onShow(async () => {
      const data = await api_request.requestApi("/company", { s_id: servId.value, c_id: cityId.value });
      company.value = data.company;
      console.log(company.value);
    });
    function toPay(compId) {
      console.log("compId", compId);
      console.log("servId", servId.value);
      console.log("cityId", cityId.value);
      common_vendor.index.navigateTo({
        url: "/pages/detail/pay?compId=" + compId + "&servId=" + servId.value + "&cityId=" + cityId.value
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.t(cityName.value),
        b: common_vendor.f(company.value, (item, index, i0) => {
          return {
            a: common_vendor.t(item.name),
            b: index,
            c: common_vendor.o(($event) => toPay(item.id), index)
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/detail/pay_choice.vue"]]);
wx.createPage(MiniProgramPage);
