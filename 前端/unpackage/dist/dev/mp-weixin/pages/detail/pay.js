"use strict";
const common_vendor = require("../../common/vendor.js");
const api_request = require("../../api/request.js");
const _sfc_main = {
  __name: "pay",
  setup(__props) {
    const servId = common_vendor.ref(1);
    const cityId = common_vendor.ref(1);
    const compId = common_vendor.ref(1);
    const serv = common_vendor.ref();
    const city = common_vendor.ref();
    const comp = common_vendor.ref();
    let household_number = "";
    common_vendor.onLoad((option) => {
      console.log("option", option);
      servId.value = Number(option.servId);
      cityId.value = Number(option.cityId);
      compId.value = Number(option.compId);
    });
    common_vendor.onShow(async () => {
      const compdata = await api_request.requestApi("/company/id", { id: compId.value });
      const servdata = await api_request.requestApi("/serv/id", { id: servId.value });
      const citydata = await api_request.requestApi("/city/" + cityId.value);
      city.value = citydata.city;
      serv.value = servdata.serv;
      comp.value = compdata.company;
    });
    function onInputOfMess(e) {
      household_number = e.detail;
    }
    async function btnToPay() {
      const data = await api_request.requestApi("/bill/check", { usernum: household_number.value });
      const flag = data.flag;
      if (flag === true) {
        api_request.navigateTo("/pay/index?servId=" + servId.value + "&compId=" + compId.value + "&household_number=" + household_number.value);
      } else {
        api_request.showToast("请输入正确的户号");
      }
    }
    return (_ctx, _cache) => {
      return {
        a: serv.value.img_url,
        b: common_vendor.t(serv.value.name),
        c: common_vendor.t(city.value.name),
        d: common_vendor.t(comp.value.name),
        e: common_vendor.o(onInputOfMess),
        f: common_vendor.o(btnToPay)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/detail/pay.vue"]]);
wx.createPage(MiniProgramPage);
