"use strict";
Object.defineProperty(exports, Symbol.toStringTag, { value: "Module" });
const common_vendor = require("./common/vendor.js");
if (!Math) {
  "./pages/index/index.js";
  "./pages/pay/success.js";
  "./pages/pay/index.js";
  "./pages/analysis/index.js";
  "./pages/index/user.js";
  "./pages/login/index.js";
  "./pages/login/login.js";
  "./pages/city_choice/city.js";
  "./pages/index/bill.js";
  "./pages/detail/pay_choice.js";
  "./pages/detail/pay.js";
}
const _sfc_main = {
  onLaunch: function() {
    console.log("App Launch");
  },
  onShow: function() {
    console.log("App Show");
  },
  onHide: function() {
    console.log("App Hide");
  }
};
const App = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/App.vue"]]);
function createApp() {
  const app = common_vendor.createSSRApp(App);
  return {
    app
  };
}
createApp().app.mount("#app");
exports.createApp = createApp;
