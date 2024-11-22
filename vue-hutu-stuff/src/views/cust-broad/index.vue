<template>
  <div class="cust-broad">
    <div class="header">
      <div class="header-info">
        <div class="title">
          <div class="main-title">备餐中</div>
          <div class="sub-title">PREPARING</div>
        </div>
        <div class="desc">
          <div class="desc-info">
            共 {{ preparps.length }} 单 10 杯 预计等待30分钟
          </div>
          <el-progress
            :show-text="false"
            :text-inside="true"
            :stroke-width="20"
            :percentage="50"
          />
        </div>
      </div>
      <div class="header-info">
        <div class="title">
          <div class="main-title">请取餐</div>
          <div class="sub-title">READYS</div>
        </div>
        <div class="desc">
          为不影响口感，请您及时取餐，您可打开小程序查看当前是否制作完成
        </div>
      </div>
    </div>
    <div class="boby">
      <div class="aside-table">
        <order-no-table :orders="total" />
      </div>
      <div class="aside-table">
        <order-no-table :orders="total" />
      </div>
    </div>
  </div>
</template>
<script>
const empty = [
  {
    orderNo: "H001",
    pickType: 0,
    itemPiece: 2,
  },
  {
    orderNo: "H002",
    pickType: 1,
    itemPiece: 2,
  },
  {
    orderNo: "H003",
    pickType: 0,
    itemPiece: 1,
  },
  {
    orderNo: "H004",
    pickType: 0,
    itemPiece: 1,
  },
  {
    orderNo: "H005",
    pickType: 0,
    itemPiece: 1,
  },
  {
    orderNo: "H006",
    pickType: 0,
    itemPiece: 1,
  },
  {
    orderNo: "H007",
    pickType: 0,
    itemPiece: 1,
  },
  {
    orderNo: "H008",
    pickType: 0,
    itemPiece: 1,
  },
  {
    orderNo: "H009",
    pickType: 0,
    itemPiece: 1,
  },
  {
    orderNo: "H010",
    pickType: 0,
    itemPiece: 1,
  },
]
const empty4Ready = [
  {
    orderNo: "H020",
    pickType: 0,
    itemPiece: 2,
  },
]
import OrderNoTable from "./componet/OrderNoTable.vue"
export default {
  name: "OrderBroad",
  components: { OrderNoTable },
  data() {
    return {
      pageTitle: "叫号大屏",
      preparps: empty,
      readys: [],
      total: empty,
    }
  },
  created() {},
  methods: {
    getPickTypeVal(type) {
      switch (type) {
        case 0:
          return "堂食"
        case 1:
          return "外带"
      }
    },
  },
  computed: {},
}
</script>

<style scoped>
.cust-broad {
  display: flex;
  flex-direction: column;
}

.header {
  width: 100%;
  height: 30%;
  padding: 26px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.header-info {
  width: 50%;
}

.boby {
  width: 100%;
  height: 70%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.desc {
  width: 60%;
  margin: 30px 0px;
}

.title {
  width: 50%;
  display: flex;
  flex-direction: row;
  font-size: 36px;
}

.desc-info {
  margin-bottom: 16px;
}

.table {
  margin-left: 26px;
  display: flex;
  flex-direction: row;
  width: 60%;
  justify-content: space-between;
  flex-wrap: wrap;
}

.aside-table {
  width: 50%;
}

.table-row {
  display: flex;
  flex-direction: row;
  font-size: 50px;
  margin: 12px 0px;
  justify-content: space-between;
}

.item {
  width: 50%;
  font-size: 50px;
  margin: 12px 0px;
}

.sub-title {
  margin-left: 30px;
}
</style>
