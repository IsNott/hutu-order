<template>
  <div class="cust-broad">
    <div class="header">
      <div class="header-info">
        <div class="title">
          <div class="main-title">备餐中</div>
          <div class="sub-title">PREPARING</div>
        </div>
        <div class="desc" v-if="total.length > 0">
          <div class="desc-info">
            共 {{ preparps.length }} 单 {{ orderTotalPiece }} 杯 预计等待
            {{ orderTotalTime }} 分钟
          </div>
          <el-progress
            :show-text="false"
            :text-inside="true"
            :stroke-width="20"
            :percentage="processing"
          />
        </div>
      </div>
      <div class="header-info">
        <div class="title">
          <div class="main-title">请取餐</div>
          <div class="sub-title">READYS</div>
        </div>
        <div class="desc">
          {{ tips }}
        </div>
      </div>
    </div>
    <div class="boby">
      <div class="aside-table">
        <order-no-table :orders="getPrepareOrder" />
      </div>
      <div class="aside-table">
        <order-no-table :orders="getReadys" />
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
    prepareTime: 2,
  },
  {
    orderNo: "H020",
    pickType: 0,
    itemPiece: 2,
    prepareTime: 2,
  },
  {
    orderNo: "H002",
    pickType: 1,
    itemPiece: 2,
    prepareTime: 2,
  },
  {
    orderNo: "H003",
    pickType: 0,
    itemPiece: 1,
    prepareTime: 2,
  },
  {
    orderNo: "H004",
    pickType: 0,
    itemPiece: 1,
    prepareTime: 2,
  },
  {
    orderNo: "H005",
    pickType: 0,
    itemPiece: 1,
    prepareTime: 2,
  },
  {
    orderNo: "H006",
    pickType: 0,
    itemPiece: 1,
    prepareTime: 2,
  },
  {
    orderNo: "H007",
    pickType: 0,
    itemPiece: 1,
    prepareTime: 2,
  },
  {
    orderNo: "H008",
    pickType: 0,
    itemPiece: 1,
    prepareTime: 2,
  },
  {
    orderNo: "H009",
    pickType: 0,
    itemPiece: 1,
    prepareTime: 2,
  },
  {
    orderNo: "H010",
    pickType: 0,
    itemPiece: 1,
    prepareTime: 2,
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
      webSocket: null,
      heartBeatMsg: "ping",
      heartBeatInterval: 10000,
      reconnectInterval: 20000,
      hbTimeinterval: null,
      pageTitle: "叫号大屏",
      preparps: [],
      readys: empty4Ready,
      total: [],
      tips: "为不影响口感，请您及时取餐，您可打开小程序查看当前是否制作完成",
    }
  },
  created() {
    this.setupWebSocket()
  },
  methods: {
    getPickTypeVal(type) {
      switch (type) {
        case 0:
          return "堂食"
        case 1:
          return "外带"
      }
    },
    setupWebSocket() {
      this.websocket = new WebSocket("ws://localhost:2999/order?shopId=593630146225766400") // 创建WebSocket连接
      this.websocket.onopen = this.onWebSocketOpen // WebSocket连接打开时的处理函数
      this.websocket.onmessage = this.onWebSocketMessage // 收到WebSocket消息时的处理函数
      this.websocket.onclose = this.onWebSocketClose // WebSocket连接关闭时的处理函数
    },
    onWebSocketOpen() {
      console.log("WebSocket connection is open")
      this.startHeartbeat()
    },
    sendMessage(message) {
      if (this.websocket && this.websocket.readyState === WebSocket.OPEN) {
        this.websocket.send(message) // 发送消息到WebSocket服务器
      }
    },
    onWebSocketMessage(event) {
      console.log(event)
      
      if (event.data) {
        const message = JSON.parse(event.data)
        console.log("WebSocket Message: ", message)
      }
    },
    onWebSocketClose() {
      console.log("WebSocket connection is closed")
      this.stopHeartbeat() // WebSocket连接关闭时，停止心跳检测
      setTimeout(this.setupWebSocket, this.reconnectInterval) // 在一定时间后重连WebSocket
    },
    startHeartbeat() {
      this.heartbeatInterval = setInterval(() => {
        if (this.websocket && this.websocket.readyState === WebSocket.OPEN) {
          this.websocket.send(this.heartBeatMsg) // 发送心跳消息
        }
      }, this.heartBeatInterval)
    },
    closeWebSocket() {
      if (this.websocket) {
        this.websocket.close() // 关闭WebSocket连接
      }
    },
    stopHeartbeat() {
      if (this.heartbeatInterval) {
        clearInterval(this.heartbeatInterval) // 停止心跳检测定时器
      }
    },
  },
  beforeDestroy() {
    this.closeWebSocket()
  },
  computed: {
    orderTotalPiece() {
      var total = 0
      this.total.forEach((item) => (total += item.itemPiece))
      return total
    },
    orderTotalTime() {
      var total = 0
      this.total.forEach((item) => (total += item.prepareTime))
      return total
    },
    processing() {
      return (this.readys.length / this.total.length) * 100
    },
    getPrepareOrder() {
      if (this.preparps.length > 12) {
        return this.preparps.splice(0, 12)
      } else {
        return this.preparps
      }
    },
    getReadys() {
      if (this.readys.length > 12) {
        return this.readys.splice(0, 12)
      } else {
        return this.readys
      }
    },
  },
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
  background-color: #f9f5ea;
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
