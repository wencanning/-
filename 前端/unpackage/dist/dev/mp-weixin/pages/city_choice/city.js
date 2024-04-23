"use strict";
const common_vendor = require("../../common/vendor.js");
const api_request = require("../../api/request.js");
if (!Array) {
  const _easycom_uni_indexed_list2 = common_vendor.resolveComponent("uni-indexed-list");
  _easycom_uni_indexed_list2();
}
const _easycom_uni_indexed_list = () => "../../uni_modules/uni-indexed-list/components/uni-indexed-list/uni-indexed-list.js";
if (!Math) {
  _easycom_uni_indexed_list();
}
const _sfc_main = {
  __name: "city",
  setup(__props) {
    const citylist = common_vendor.ref([]);
    const cityst = common_vendor.ref([]);
    let eventChannel;
    common_vendor.onLoad((option) => {
      eventChannel = common_vendor.getCurrentInstance().proxy.getOpenerEventChannel();
      console.log("eventChannel", eventChannel);
    });
    common_vendor.onShow(async () => {
      console.log("请求城市数据");
      const cityData = await api_request.requestApi("/city");
      cityst.value = cityData.citylist;
      console.log("请求的城市数据", cityst);
      initCityList();
    });
    function initCityList() {
      let list = [];
      let categorizedData = {};
      cityst.value.forEach((item) => {
        let firstletter = item.letter;
        if (!categorizedData[firstletter]) {
          categorizedData[firstletter] = 1;
          list.push({ letter: firstletter, data: [] });
        }
        list.forEach((item2) => {
          if (item2.letter == firstletter) {
            item2.data.push(item.name);
          }
        });
      });
      citylist.value = list;
      console.log(citylist);
    }
    function getCityId(name) {
      let id;
      cityst.value.forEach((item) => {
        if (name == item.name) {
          id = item.id;
        }
      });
      console.log("id", id);
      return id;
    }
    function bindClick(obj) {
      let cname = obj.item.name;
      let cid = getCityId(cname);
      eventChannel.emit("acceptDataFromOpenedPage", { name: cname, city_id: cid });
      common_vendor.index.navigateBack();
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(bindClick),
        b: common_vendor.p({
          options: citylist.value,
          ["show-select"]: false
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/city_choice/city.vue"]]);
wx.createPage(MiniProgramPage);
