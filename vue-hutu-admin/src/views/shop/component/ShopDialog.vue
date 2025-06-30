<template>
  <el-dialog
    :title="obj.id ? '编辑' : '新增'"
    :visible.sync="visible"
    width="60%">
    <el-form :model="obj" ref="form" label-width="100px" class="dialog-form">
      <el-form-item label="门店名称">
        <el-input v-model="obj.shopName" placeholder="请输入门店名称" size="small" />
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="obj.address" placeholder="请输入地址" size="small" />
      </el-form-item>
      <el-form-item label="联系电话">
        <el-input v-model="obj.phone" placeholder="请输入联系电话" size="small" />
      </el-form-item>
      <el-form-item label="营业时间">
        <el-input v-model="obj.startBusinessTime" placeholder="开始时间" size="small" />
        <el-input v-model="obj.endBusinessTime" placeholder="结束时间" size="small" />
      </el-form-item>
      <el-form-item label="经纬度">
        <el-input v-model.number="obj.longitude" placeholder="经度" size="small"/>
        <el-input v-model.number="obj.latitude" placeholder="纬度" size="small"/>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { update,save } from '@/api/shop';
  export default {
  name: 'ShopDialog',
  components: {},
  props: {
    obj: {
      type: Object,
      default: () => ({
        id: null,
        shopName: '',
        address: '',
        phone: '',
        startBusinessTime: '',
        endBusinessTime: '',
        longitude: 0,
        latitude: 0
      })
    },
    visible: {
      type: Boolean,
      default: false
    }
  },
  data() {
   return {
    
   }
  },
  methods: {
    submit () { 
      this.$refs.form.validate(valid => {
        if (valid) {
          const api = this.obj.id ? update : save;
          api(this.obj).then(() => {
            this.$message.success('操作成功');
            this.$emit('refresh');
            this.visible = false;
          }).catch(error => {
            this.$message.error(error.message || '操作失败');
          });
        }
      });
    }
  },
  computed: {},
  watch: {},
  mounted() {},
  created() {},
  }
</script>

<style scoped>
</style>