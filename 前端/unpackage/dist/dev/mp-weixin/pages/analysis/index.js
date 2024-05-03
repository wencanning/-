"use strict";
const common_vendor = require("../../common/vendor.js");
const api_request = require("../../api/request.js");
if (!Array) {
  const _easycom_qiun_data_charts2 = common_vendor.resolveComponent("qiun-data-charts");
  _easycom_qiun_data_charts2();
}
const _easycom_qiun_data_charts = () => "../../uni_modules/qiun-data-charts/components/qiun-data-charts/qiun-data-charts.js";
if (!Math) {
  _easycom_qiun_data_charts();
}
const _sfc_main = {
  __name: "index",
  setup(__props) {
    const currentDate = common_vendor.ref("2024-04");
    const currentYear = common_vendor.ref("2024");
    const startDate = common_vendor.ref("2023-1");
    const endDate = common_vendor.ref("2050-1");
    const billnum = common_vendor.ref(125);
    const moneySum = common_vendor.ref(3546.72);
    const billmod = common_vendor.ref("month");
    common_vendor.ref([]);
    const ranklist = common_vendor.ref([]);
    const chartDataRef = common_vendor.ref({});
    const optsRef = common_vendor.ref({
      color: ["#00B0FF"],
      padding: [15, 15, 0, 5],
      // 上右下左
      enableScroll: true,
      legend: {},
      xAxis: {
        disableGrid: true,
        gridColor: "#00B0FF",
        itemCount: 6
      },
      yAxis: {
        disableGrid: true,
        disabled: true,
        data: [
          {
            min: 0,
            max: 40
          }
        ],
        splitNumber: 4
      },
      extra: {
        column: {
          type: "group",
          width: 20,
          activeBgColor: "#000000",
          activeBgOpacity: 0.08
        }
      }
    });
    common_vendor.onShow(() => {
      getMoneyListByMonth(currentDate.value);
      getRank10(currentDate.value);
    });
    function changeMod(tab) {
      billmod.value = tab;
      if (billmod.value === "month") {
        console.log("222");
        getMoneyListByMonth(currentDate.value);
        getRank10(currentDate.value);
      } else if (billmod.value === "year") {
        console.log("111");
        getMoneyListByYear(currentYear.value);
      }
    }
    function dateChange(event) {
      if (billmod.value === "month") {
        currentDate.value = event.detail.value;
        getMoneyListByMonth(currentDate.value);
        getRank10(currentDate.value);
      } else if (billmod.value === "year") {
        currentYear.value = event.detail.value;
        getMoneyListByYear(currentYear.value);
      }
    }
    async function getMoneyListByMonth(dateStr) {
      const uid = api_request.getUid();
      const data = await api_request.requestApi("/bill/chart/month", { dateStr, uid });
      const moneylist = data.moneylist;
      billnum.value = data.billnum;
      moneySum.value = data.mmoney;
      const res = {
        categories: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
        series: [
          {
            name: "消费金额",
            data: data.moneylist
          }
        ]
      };
      let maxValue = Math.max(...moneylist);
      let minValue = Math.min(...moneylist);
      optsRef.value.yAxis.data[0].max = maxValue;
      optsRef.value.yAxis.data[0].min = minValue;
      chartDataRef.value = res;
    }
    async function getRank10(dateStr) {
      const uid = api_request.getUid();
      const data = await api_request.requestApi("/bill/rank10", { dateStr, uid });
      ranklist.value = data.ranklist;
    }
    async function getMoneyListByYear(dateStr) {
      const uid = api_request.getUid();
      const data = await api_request.requestApi("/bill/chart/year", { dateStr, uid });
      const moneylist = data.moneylist;
      billnum.value = data.billnum;
      moneySum.value = data.mmoney;
      let yearsArray = [];
      const year = Number(dateStr);
      for (let i = year - 4; i <= year + 7; i++) {
        yearsArray.push(i);
      }
      console.log(yearsArray);
      const res = {
        categories: yearsArray,
        series: [
          {
            name: "消费金额",
            data: moneylist
          }
        ]
      };
      let maxValue = Math.max(...moneylist);
      let minValue = Math.min(...moneylist);
      optsRef.value.yAxis.data[0].max = maxValue;
      optsRef.value.yAxis.data[0].min = minValue;
      chartDataRef.value = res;
    }
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: billmod.value === "month" ? 1 : "",
        b: common_vendor.o(($event) => changeMod("month")),
        c: billmod.value === "year" ? 1 : "",
        d: common_vendor.o(($event) => changeMod("year")),
        e: billmod.value === "month"
      }, billmod.value === "month" ? {
        f: common_vendor.t(currentDate.value),
        g: startDate.value,
        h: endDate.value,
        i: currentDate.value,
        j: common_vendor.o(dateChange)
      } : {}, {
        k: billmod.value === "year"
      }, billmod.value === "year" ? {
        l: common_vendor.t(currentYear.value),
        m: currentYear.value,
        n: common_vendor.o(dateChange)
      } : {}, {
        o: common_vendor.t(billnum.value),
        p: common_vendor.t(moneySum.value.toFixed(2)),
        q: common_vendor.p({
          type: "column",
          opts: optsRef.value,
          ["chart-data"]: chartDataRef.value,
          ontouch: true
        }),
        r: billmod.value === "month"
      }, billmod.value === "month" ? {} : {}, {
        s: billmod.value === "month"
      }, billmod.value === "month" ? {
        t: common_vendor.f(ranklist.value, (item, index, i0) => {
          return {
            a: common_vendor.t(index + 1),
            b: common_vendor.t(item.name),
            c: common_vendor.t(item.money.toFixed(2)),
            d: index
          };
        })
      } : {});
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/analysis/index.vue"]]);
wx.createPage(MiniProgramPage);
