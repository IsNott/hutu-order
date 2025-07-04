<template>
  <el-dialog
    :title="obj && obj.id ? '编辑' : '新增'"
    :visible.sync="visible"
    :before-close="close"
    width="60%"
  >
    <el-form
      ref="form"
      :model="obj"
      :rules="rules"
      label-width="100px"
      class="dialog-form"
    >
      <el-form-item label="门店名称" prop="shopName">
        <el-input
          v-model="obj.shopName"
          placeholder="请输入门店名称"
          size="small"
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="obj.address" placeholder="请输入地址" size="small" />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input
          v-model="obj.phone"
          placeholder="请输入联系电话"
          size="small"
        />
      </el-form-item>
      <el-form-item label="营业时间" prop="startBusinessTime endBusinessTime">
        <el-time-picker
          v-model="time"
          is-range
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          placeholder="选择时间范围"
          format="HH:mm"
          size="small"
        />
      </el-form-item>
      <el-form-item label="经纬度" prop="longitude latitude">
        <el-input
          v-model.number="obj.longitude"
          placeholder="经度"
          size="small"
        />
        <el-input
          v-model.number="obj.latitude"
          placeholder="纬度"
          size="small"
        />
      </el-form-item>
      <el-form-item label="轮播图">
        <template v-if="swipeImage">
          <el-image
            v-for="(item, index) in obj.swipeImage"
            :key="index"
            :src="item"
            style="
              width: 100px;
              height: 60px;
              object-fit: cover;
              margin-right: 10px;
            "
          />
        </template>
        <template v-else>
          <upload v-model="obj.swipeImage" :data="{ bizId: obj.id ? obj.id : '' }" :disabled="disabled" />
        </template>

      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { update, save } from '@/api/shop'
import Upload from '@/components/Upload/index.vue'
export default {
  name: 'ShopDialog',
  components: { Upload },
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
        latitude: 0,
        swipeImage: []
      })
    },
    visible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      time: [],
      disabled: false,
      rules: {
        shopName: [
          { required: true, message: '请输入门店名称', trigger: 'blur' },
          { min: 6, message: '长度至少 6 个字符', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' },
          { min: 10, message: '长度至少 10 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          {
            pattern: /^1[3-9]\d{9}$/,
            message: '请输入正确的手机号码',
            trigger: 'blur'
          }
        ],
        startBusinessTime: [
          { required: true, message: '请选择开始营业时间', trigger: 'change' }
        ],
        endBusinessTime: [
          { required: true, message: '请选择结束营业时间', trigger: 'change' }
        ],
        // 经纬度验证
        longitude: [
          { required: false, message: '请输入经度', trigger: 'blur' },
          { type: 'number', message: '经度必须是数字', trigger: 'blur' }
        ],
        latitude: [
          { required: false, message: '请输入纬度', trigger: 'blur' },
          { type: 'number', message: '纬度必须是数字', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    // time: {
    //   get () {
    //     if(!this.obj.startBusinessTime || !this.obj.endBusinessTime) {
    //       return [new Date(), new Date()]; // Default to current time if not set
    //     }
    //     const [hours, minutes] = this.obj.startBusinessTime.split(":").map(Number); // Split and convert to numbers
    //     const [endHours, endMinutes] = this.obj.endBusinessTime.split(":").map(Number);
    //     return [new Date(0, 0, 0, hours, minutes), new Date(0, 0, 0, endHours, endMinutes)];
    //   },
    //   set (value) {
    //     console.log('value', value);
    //     if(value && value.length === 2) {
    //       const start = value[0];
    //       const end = value[1];
    //       this.obj.startBusinessTime = `${start.getHours()}:${start.getMinutes()}`;
    //       this.obj.endBusinessTime = `${end.getHours()}:${end.getMinutes()}`;
    //     } else {
    //       this.obj.startBusinessTime = '';
    //       this.obj.endBusinessTime = '';
    //     }
    //   }
    // }
  },
  watch: {},
  mounted() {},
  created() {},
  methods: {
    submit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          const api = this.obj.id ? update : save
          api(this.obj)
            .then(() => {
              this.$message.success('操作成功')
              this.$emit('refresh')
            })
            .catch((error) => {
              this.$message.error(error.message || '操作失败')
            })
        }
      })
    },
    close() {
      this.$emit('refresh')
    }
  }
}
</script>

<style scoped></style>
