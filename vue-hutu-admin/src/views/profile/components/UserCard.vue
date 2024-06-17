<template>
  <el-card style="margin-bottom:20px;">
    <div slot="header" class="clearfix">
      <span>账号资料</span>
    </div>

    <div class="user-profile">
      <div class="box-center">
        <pan-thumb :image="user.avatar" :height="'100px'" :width="'100px'" :hoverable="false">
          <div>Hello</div>
          {{ user.role }}
        </pan-thumb>
      </div>
      <div class="box-center">
        <div class="user-name text-center">用户名：{{ user.name }}</div>
        <div class="user-role text-center text-muted">角色：{{ user.role | uppercaseFirst }}</div>
      </div>

      <div class="user-button">
      <el-button size="small" type="primary">修改资料</el-button>
      <el-button size="small" type="info">注销</el-button>
    </div>
    </div>

   
  </el-card>
</template>

<script>
import PanThumb from '@/components/PanThumb'

export default {
  components: { PanThumb },
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: '',
          email: '',
          avatar: '',
          role: '',
          phone: ''
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

    .el-table {
      border-top: 1px solid #dfe6ec;
    }
  }

  .user-follow {
    padding-top: 20px;
  }

  .user-button{
    margin: 0 auto;
    text-align: center;
    padding-top: 20px
  }
}
</style>
