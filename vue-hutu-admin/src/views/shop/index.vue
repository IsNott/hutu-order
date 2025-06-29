<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
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
      <el-table-column label="门店名称" width="200">
        <template slot-scope="scope">
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
      <el-table-column class-name="status-col" label="营业时间" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.startBusinessTime }} - {{ scope.row.endBusinessTime }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否关闭" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.closeNow }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="经纬度" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.longitude }}, {{ scope.row.latitude }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="封面图" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.coverUrl }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" fixed="right">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="editRow(scope.row)"
          >编辑</el-button>
          <el-button
            type="danger"
            size="mini"
            @click="deleteRow(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        layout="prev, pager, next"
        :total="totalPage"
      />
    </div>
  </div>
</template>
<script>
import { shopPage } from '@/api/shop'
export default {
  name: '',
  data() {
    return {
      list: [],
      listLoading: true,
      page: 1,
      size: 10,
      totalPage: 0
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      shopPage({
        page: this.page,
        size: this.size
      }).then(response => {
        this.list = response.data.records
        this.totalPage = response.data.pages
        this.listLoading = false
      })
    },
    deleteRow(row) {

    },
    editRow(row) {
      // Implement edit functionality here
      console.log('Editing row:', row)
    }
  }
}
</script>
<style lang="scss" scoped>
  .block {
    float: right;
  }
</style>
