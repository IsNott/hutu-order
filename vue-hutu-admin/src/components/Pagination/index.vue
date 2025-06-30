<template>
  <div class="my-pagination">
    <el-pagination v-if="page.size && page.current"
      layout="total, sizes, prev, pager, next, jumper"
      :page-size="page.size"
      :current-page="page.current"
      :page-sizes="[10, 20, 30, 50]"
      :total="page.total"
      @size-change="handerSizeChange"
      @current-change="handerCurrentChange"
      @next-click="handerPageClick(1)"
      @prev-click="handerPageClick(-1)"
    />
  </div>
</template>

<script>
  export default {
  name: 'Pagination',
  components: {},
  props: {
    page: {
      type: Object,
      default: () => ({
        current: 1,
        size: 10,
        pages: 1,
        total: 0
      })
    }
  },
  data() {
   return {
    
   }
  },
  methods: {
    handerSizeChange (size) {
      this.page.size = size
      this.$emit('size', size)
    },
    handerCurrentChange(current) {
      this.page.current += current
      this.$emit('current', current)
    },
    handerPageClick() {
      this.page.current -= 1
      this.$emit('current', this.page.current)
    }
  },
  computed: {},
  watch: {},
  mounted() {},
  created() {},
  }
</script>

<style scoped>
.my-pagination {
  padding: 10px;
  background-color: #fff;
  border-radius: 4px;
  float: right;
}
</style>