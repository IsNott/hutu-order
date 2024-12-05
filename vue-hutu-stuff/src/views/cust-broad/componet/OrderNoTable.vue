<template>
  <div class="table">
    <div class="item" v-for="item in orders" :key="item.orderNo">
      <i class="element-icons" v-if="item.pickType == 0" style="font-size: 50px">&#xe62f</i>
      <i class="element-icons" v-if="item.pickType == 1" style="font-size: 50px">&#xe6cc</i>
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
    
  },
}
</script>

<style scoped>
.table {
  margin-left: 26px;
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-between;
  flex-wrap: wrap;
}

.item {
  width: 50%;
  font-size: 50px;
  margin: 12px 0px;
}
</style>
