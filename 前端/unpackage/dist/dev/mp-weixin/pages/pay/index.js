"use strict";
const common_vendor = require("../../common/vendor.js");
const api_request = require("../../api/request.js");
const _sfc_main = {
  __name: "index",
  setup(__props) {
    const servId = common_vendor.ref(0);
    const compId = common_vendor.ref(0);
    const household_number = common_vendor.ref("");
    const serv = common_vendor.ref();
    const comp = common_vendor.ref();
    const payItem = common_vendor.ref();
    let payNum = 0;
    common_vendor.onLoad((option) => {
      console.log("option", option);
      servId.value = Number(option.servId);
      compId.value = Number(option.compId);
      household_number.value = option.household_number;
    });
    common_vendor.onShow(async () => {
      const compdata = await api_request.requestApi("/company/id", { id: compId.value });
      const servdata = await api_request.requestApi("/serv/id", { id: servId.value });
      const paydata = await api_request.requestApi("/bill/getpay", { uid: api_request.getUid(), comid: compId.value });
      payItem.value = paydata.payItem;
      serv.value = servdata.serv;
      comp.value = compdata.company;
    });
    function onInputOfNum(e) {
      payNum = parseFloat(e.detail.value);
    }
    async function btnPay() {
      const data = await api_request.requestApi("/bill/pay", { uid: api_request.getUid(), compid: compId.value, paynum: payNum }, "POST");
      console.log(data.flag);
      if (data.flag === true) {
        api_request.navigateTo("/pay/success");
      } else {
        api_request.showToast("支付失败");
      }
    }
    return (_ctx, _cache) => {
      return {
        a: serv.value.img_url,
        b: common_vendor.t(payItem.value.amount_due.toFixed(2)),
        c: common_vendor.t(payItem.value.current_balance.toFixed(2)),
        d: common_vendor.t(payItem.value.account_name),
        e: common_vendor.t(household_number.value),
        f: common_vendor.t(comp.value.name),
        g: common_vendor.o(onInputOfNum),
        h: common_vendor.o(btnPay)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/pay/index.vue"]]);
wx.createPage(MiniProgramPage);
