<template>
    <div class="contianer">
        <div class="query-form">
            <el-form ref="queryForm" :model="searchParam" label-width="80px">
                <el-row gutter="10">
                    <el-col :span="6">
                        <el-form-item label="创建时间">
                            <el-date-picker v-model="searchParam.createTime" placeholder="请输入创建时间" type="date" style="width: 100%"
                                            value-format="YYYY-MM-DD" />
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item>
                            <el-button type="primary" @click="handleAdd">新增</el-button>
                            <el-button type="primary" @click="query">查询</el-button>
                            <el-button @click="reset">重置</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <div class="list">
            <el-table :data="${featureName}List" stripe style="width: 100%" border v-loading="loading">
                <template #empty>
                    <img src="@/assets/img/empty.png" />
                    <p style="font-size: 20px">Nothing to load....</p>
                </template>
                <el-table-column type="index" width="50" align="center" />
                <#list fields as field>
                    <#if field.propertyName != "id" && field.propertyName != "createTime" && field.propertyName != "updateTime" && field.propertyName != "delFlag">
                        <el-table-column prop="${field.propertyName}" label="${field.comment}" width="120" align="center"></el-table-column>
                    </#if>
                </#list>
                <el-table-column label="操作" align="center">
                    <template #default="scope">
                        <el-button type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-pagination v-model:current-page="page.page" v-model:page-size="page.size" :page-sizes="[20, 50, 100, 200]"
                       background layout="total, sizes, prev, pager, next, jumper" :total="page.total" @size-change="handleSizeChange"
                       @current-change="query" />
        <EditForm :key="formKey" @close="handleClose" :title="title" :row-id="selectedId" :dialogVisible="dialogVisible" />
    </div>
</template>

<script setup>
    import { ref, reactive, onMounted, defineProps, defineEmits, watch, computed } from 'vue'
    import { ElMessage, ElMessageBox } from 'element-plus'
    import EditForm from './components/EditForm.vue'
    import { ${featureName}Api } from './api'
    // Data
    const emptySearchParam = {}
    const emptyPage = {
        page: 1,
        size: 10,
        total: 0
    }
    const searchParam = ref({})
    const ${featureName}List = ref([])
    const page = ref(emptyPage)
    const loading = ref(false)
    const queryForm = ref(null)
    const title = ref('新增${featureZhName}')
    const dialogVisible = ref(false)
    const selectedId = ref(null)
    const formKey = ref(0)
    // Computed

    // Emits
    const emit = defineEmits([

    ])

    // Props
    const props = defineProps({

    })

    // Lifecycle hooks
    onMounted(() => {
        query()
    })

    // Watchers

    // Methods
    const query = () => {
        loading.value = true
        ${featureName}Api.queryPage(searchParam.value, page.value.page, page.value.size).then(res => {
            if (res.code !== 200) {
                ElMessage.error(res.message)
                return
            }
            ${featureName}List.value = res.data.records
            page.value.total = res.data.total
            page.value.size = res.data.size
        }).finally(() => {
            loading.value = false
        })
    }

    const handleAdd = () => {
        title.value = '新增${featureZhName}'
        dialogVisible.value = true
        formKey.value = Math.random()
    }


    const handleSwitch = (row) => {
        ${featureName}Api.update(row).then(res => {
            if (res.code !== 200) {
                ElMessage.error(res.message)
                return
            }
            ElMessage.success(res.message)
            query()
        })
    }

    const handleSizeChange = (size) => {
        page.value.size = size
        query()
    }

    const reset = () => {
        searchParam.value = emptySearchParam
        queryForm.value.resetFields()
        page.value = emptyPage
        query()
    }

    const handleClose = () => {
        selectedId.value = null
        dialogVisible.value = false
        query()
    }
    const handleEdit = (row) => {
        title.value = '编辑${featureZhName}'
        dialogVisible.value = true
        selectedId.value = row.id
        formKey.value = Math.random()
    }
    const handleDelete = (row) => {
        ElMessageBox.confirm('确定要删除吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            ${featureName}Api.delete(row.id).then(res => {
                if (res.code !== 200) {
                    ElMessage.error(res.message)
                    return
                }
                ElMessage.success(res.message)
                query()
            })
        })
    }
</script>

<style scoped lang="scss">
    .contianer {
        .query-form {
            margin-bottom: 20px;
        }

        .el-table {
        // margin-bottom: 30px;
        }

        .el-pagination {
            display: flex;
            justify-content: flex-end;
        }

        .list {
            overflow-y: auto;
            max-height: calc(100vh - 340px);
            margin-bottom: 20px;
        }
    }
</style>