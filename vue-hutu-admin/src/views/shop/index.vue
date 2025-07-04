<template>
  <div class="app-container">
    <el-form ref="form" :model="query" :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input
          v-model="query.keyword"
          clearable
          placeholder="请输入关键字"
          size="small"
        />
      </el-form-item>
      <!-- <el-form-item label="删除状态">
        <el-select clearable v-model="query.delFlag" placeholder="请选择" size="small">
          <el-option label="全部" value=""></el-option>
          <el-option label="已删除" value="1"></el-option>
          <el-option label="未删除" value="0"></el-option>
        </el-select>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" size="small" @click="fetchData">查询</el-button>
        <el-button type="success" size="small" @click="editRow({})">新增门店</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="page.records"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="200">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="门店名称" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.mainShop === 1" size="small" type="success" style="margin-right:6px">主店</el-tag>
          <el-tag v-if="scope.row.open && scope.row.closeNow === 0" size="small" type="success">营业中</el-tag>
          <el-tag v-else size="small" type="info">店休</el-tag>
          {{ scope.row.shopName }}
        </template>
      </el-table-column>
      <el-table-column label="地址" width="300" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.address }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="营业时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.startBusinessTime }} - {{ scope.row.endBusinessTime }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="经纬度" width="200">
        <template slot-scope="scope">
          <span>{{ longitudeAndLatitude(scope.row.longitude, scope.row.latitude) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column align="center" label="封面图" width="200">
        <template slot-scope="scope">
          <img v-if="scope.row.coverUrl"
           :src="scope.row.coverUrl" alt="封面图" style="width: 100px; height: 60px; object-fit: cover;"/>
        </template>
      </el-table-column> -->
      <el-table-column align="center" label="创建时间" width="180">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="240">
        <template slot-scope="scope">
          <el-button
            :type="scope.row.closeNow === 0 && scope.row.open ? 'info' : 'success'"
            size="mini"
            @click="closeOrOnlineRow(scope.row, scope.row.closeNow === 0 ? 'close' : 'online')"
          >
            {{ scope.row.closeNow === 0 && scope.row.open ? '闭店' : '上线' }}
          </el-button>
          <el-button
            type="primary"
            size="mini"
            @click="editRow(scope.row)"
          >详情</el-button>
          <el-button
            type="danger"
            size="mini"
            @click="deleteRow(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination :page="page" />
    <shop-dialog
      :key="key"
      :obj="row"
      :visible.sync="edit"
      @refresh="fetchData"
      @close="row = null"
    />
  </div>
</template>
<script>
import { page, deleteById, update } from '@/api/shop'
import Pagination from '@/components/Pagination/index.vue'
import ShopDialog from './component/ShopDialog.vue'
export default {
  name: 'Shop',
  components: {
    Pagination, ShopDialog
  },
  data() {
    return {
      listLoading: true,
      page: { current: 1, size: 10 },
      query: {
        keyword: '',
        delFlag: ''
      },
      edit: false,
      row: null,
      key: new Date().getTime() // 用于强制刷新组件
    }
  },
  computed: {

  },
  watch: {
    'page.current': function(newVal, oldVal) {
      if (newVal !== oldVal) {
        this.fetchData()
      }
    },
    'page.size': function(newVal, oldVal) {
      if (newVal !== oldVal) {
        this.fetchData()
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      this.edit = false
      page({
        page: this.page.current,
        size: this.page.size,
        ...this.query
      }).then(response => {
        this.page = response.data
        this.listLoading = false
      })
    },
    longitudeAndLatitude(long, lat) {
      if (long && lat) {
        return `${long}, ${lat}`
      } else {
        return '未设置'
      }
    },
    deleteRow(row) {
      this.$confirm('此操作将永久删除该门店, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteById(row.id).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.fetchData()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    editRow(row) {
      this.edit = true
      this.row = row
      this.key = new Date().getTime() // 更新key以强制刷新组件
    },
    closeOrOnlineRow(row, type) {
      if (!row.open) {
        this.$message({
          type: 'warning',
          message: '门店未到营业时间，无法上线'
        })
        return
      }
      const opt = type === 'close' ? '关闭' : '上线'
      this.$confirm(`是否确认${opt}该门店?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'success'
      }).then(() => {
        row.closeNow = type === 'close' ? 1 : 0
        update(row).then(res => {
          this.$message({
            type: 'success',
            message: `${opt}成功!`
          })
          this.fetchData()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: `已取消${opt}`
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  .block {
    float: right;
  }
</style>
