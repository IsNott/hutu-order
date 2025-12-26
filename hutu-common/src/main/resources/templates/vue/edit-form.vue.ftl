<template>
    <el-dialog :title="title" :model-value="dialogVisible" width="600px" :before-close="handleClose">
        <el-form ref="editFormRef" :rules="rules" :model="editForm" label-width="100px">
            <#list fields as field>
                <#if field.propertyName != "id" && field.propertyName != "createTime" && field.propertyName != "updateTime" && field.propertyName != "delFlag">
                    <el-form-item label="${field.comment!}" prop="${field.propertyName}">
                        <el-input v-model="editForm.${field.propertyName}" :placeholder="'请输入${field.comment!}'" />
                    </el-form-item>
                </#if>
            </#list>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="submitEdit">确 定</el-button>
      </span>
        </template>
    </el-dialog>
</template>

<script setup>
    import { ref, defineEmits, defineProps, watch, onMounted } from 'vue'
    import { ElMessage } from 'element-plus'
    import { ${featureName}Api } from '../api'

    const props = defineProps({
        title: {
            type: String,
            default: '操作'
        },
        rowId: {
            type: Number,
            required: false,
        },
        dialogVisible: {
            type: Boolean,
            default: false
        }
    })

    const emit = defineEmits(['close', 'update:dialogVisible'])
    const empty = {}
    const editForm = ref(empty)
    const editFormRef = ref(null)

    const rules = {
        <#list fields as field>
            <#if field.propertyName != "id" && field.propertyName != "createTime" && field.propertyName != "updateTime" && field.propertyName != "delFlag">
        ${field.propertyName}: [
            { required: true, message: '请输入${field.comment!}', trigger: 'blur' }
        ],
            </#if>
        </#list>
    }

    onMounted(async () => {
        if (props.rowId) {
            ${featureName}Api.details(props.rowId).then(res => {
                if (res.code !== 200) {
                    ElMessage.error(res.message)
                    return
                }
                editForm.value = res.data
            })
        }
    })

    const submitEdit = () => {
        editFormRef.value?.validate(async (valid) => {
            if (!valid) {
                return
            }
            const apiCall = editForm.value.id ? ${featureName}Api.update : ${featureName}Api.add
            const res = await apiCall(editForm.value)
            if (res.code !== 200) {
                ElMessage.error(res.message)
                return
            }
            ElMessage.success(res.message)
            handleClose()
        })
    }

    const handleClose = () => {
        emit('update:dialogVisible', false)
        emit('close')
        editForm.value = { ...empty }
        editFormRef.value?.resetFields()
        editFormRef.value?.clearValidate()
    }
</script>

<style scoped lang="scss">
    ::v-deep(.el-dialog__footer) {
        text-align: center;
    }
</style>