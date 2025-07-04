<template>
  <div>
    <el-upload
      :action="uploadPath"
      list-type="picture-card"
      :file-list="fileList"
      :auto-upload="autoUpload"
      :on-success="handleSuccess"
      :on-remove="handleRemove"
      :http-request="httpRequest"
    >
      <i slot="default" class="el-icon-plus" />
      <div slot="file" slot-scope="{ file }">
        <img
          class="el-upload-list__item-thumbnail"
          :src="file.path"
          :alt="file.fileName"
        >

        <span class="el-upload-list__item-actions">
          <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
          >
            <i class="el-icon-zoom-in" />
          </span>
          <!-- <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleDownload(file)"
          >
            <i class="el-icon-download" />
          </span> -->
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
          >
            <i class="el-icon-delete" />
          </span>
        </span>
      </div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
const uploadPath = 'hutu-oss/oss/upload'
import { upload } from '@/api/oss'
export default {
  name: '',
  components: {},
  props: {
    disabled: {
      type: Boolean,
      default: false
    },
    uploadPath: {
      type: String,
      default: uploadPath
    },
    headers: {
      type: Object,
      default: () => ({
        'Content-Type': 'form-data'
      })
    },
    name: {
      type: String,
      default: 'file'
    },
    data: {
      type: Object,
      default: () => ({})
    },
    multiple: {
      type: Boolean,
      default: false
    },
    drag: {
      type: Boolean,
      default: false
    },
    accept: {
      type: String,
      default: null
    },
    autoUpload: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      fileList: []
    }
  },
  computed: {},
  watch: {},
  mounted() {},
  created() {},
  methods: {
    handleRemove(file) {
      this.$emit('remove', file)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleDownload(file) {
      console.log(file)
    },
    handleSuccess(resp, file, fileList) {
      this.$emit('success', resp.data.path)
    },
    httpRequest() {
      const data = {
        file: this.fileList[0],
        ...this.data
      }
      upload(data)
        .then((response) => {
          this.handleSuccess(response, this.fileList[0], this.fileList)
        })
        .catch((error) => {
          this.$message.error('上传失败')
          console.error(error)
        })
    }
  }
}
</script>

<style scoped>
</style>
