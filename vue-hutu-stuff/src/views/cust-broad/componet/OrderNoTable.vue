<template>
  <div class="table">
    <div class="item" v-for="item in orders" :key="item.orderNo">
      <i class="element-icons" style="font-size: 50px">{{
        getPickTypeIconStr(item.pickType)
      }}</i>
      {{ item.orderNo }}
    </div>
  </div>
</template>

<script>
import { getComponentKey } from "@/utils/CommonUtils"
export default {
  name: "OrderNoTable",
  components: {},
  props: {
    orders: {
      type: Array,
      default: [],
    },
  },
  data() {
    return {}
  },
  methods: {
    getPickTypeIconStr(pickType) {
      switch (pickType) {
        default:
          return "&#xe6cc;"
        case 0:
          return "&#xe62f;"
        case 1:
          return "&#xe6cc;"
      }
    },
  },
}
</script>

<style scoped>
.table {
  margin-left: 26px;
  display: flex;
  flex-direction: row;
  width: 60%;
  justify-content: space-between;
  flex-wrap: wrap;
}

.item {
  width: 50%;
  font-size: 50px;
  margin: 12px 0px;
}
</style>
