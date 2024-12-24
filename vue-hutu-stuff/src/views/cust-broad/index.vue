<template>
  <div class="cust-broad">
    <div class="header">
      <div class="header-info">
        <div class="title">
          <div class="main-title">备餐中</div>
          <div class="sub-title">PREPARING</div>
        </div>
        <div class="desc" v-if="preparps.length > 0">
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
import OrderNoTable from "./componet/OrderNoTable.vue"
import { OrderMessage } from "@/enums/OrderMessageEnum.js"
const { ipcRenderer } = require("electron")
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
      readys: [],
      tips: "为不影响餐品口感，请您及时取餐，您可打开小程序查看当前是否制作完成",
      ipcRenderer: ipcRenderer,
    }
  },
  created() {
    this.setupWebSocket()
    console.log(new Date().getTime())
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
      this.websocket = this.webSocket
        ? this.webSocket
        : new WebSocket("ws://localhost:2999/order?shopId=593630146225766400") // 创建WebSocket连接
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
      if (event.data) {
        if (event.data === "pong") {
          return
        }
        console.log(event)
        const message = JSON.parse(event.data)
        const type = message.messageType
        switch (type) {
          default:
            break
          case OrderMessage.IN:
            this.push2List(message)
            // this.handlePrint()
            break
          case OrderMessage.FINISH:
            this.removeFromPreparedAndFinished(
              this.preparps,
              message,
              this.readys
            )
            break
          case OrderMessage.TAKED:
            this.removeFromPreparedAndFinished(this.readys, message, null)
            break
        }
      }
    },
    handlePrint() {
      const data = {}
      ipcRenderer.send("print-request", data)
    },
    push2List(order) {
      this.preparps.push(order)
    },
    removeFromPreparedAndFinished(list, order, toList) {
      list.splice(
        list.findIndex((item) => (item.id = order.id)),
        1
      )
      if (toList) {
        toList.push(order)
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
      this.preparps.forEach((item) => (total += item.itemPiece))
      return total
    },
    orderTotalTime() {
      var total = 0
      this.preparps.forEach((item) => (total += item.waitTime))
      return total
    },
    processing() {
      return (this.readys.length / this.preparps.length) * 100
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
