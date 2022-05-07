<template>
  <a-modal v-model="show" title="修改教员信息" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        修改
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='教员姓名' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入教员姓名!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='工号' v-bind="formItemLayout">
            <a-input v-decorator="[
            'jobNumber',
            { rules: [{ required: true, message: '请输入工号!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='性别' v-bind="formItemLayout">
            <a-select v-decorator="[
            'sex',
            { rules: [{ required: true, message: '请输入性别!' }] }
            ]">
              <a-select-option value="1">男</a-select-option>
              <a-select-option value="2">女</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'TeacherEdit',
  props: {
    teacherEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.teacherEditVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false
    }
  },
  methods: {
    setFormValues ({...teacher}) {
      this.rowId = teacher.id
      let fields = ['name', 'jobNumber', 'sex']
      let obj = {}
      Object.keys(teacher).forEach((key) => {
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = teacher[key]
        }
        if (key === 'sex') {
          obj[key] = teacher[key].toString()
        }
      })
      this.form.setFieldsValue(obj)
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleCancel () {
      this.previewVisible = false
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        this.$get('/cos/teacher-info/repeat', {jobNumber: values.jobNumber, tid: values.id}).then((r) => {
          if (r.data.data === 0) {
            if (!err) {
              this.loading = true
              this.$put('/cos/teacher-info', {
                ...values
              }).then((r) => {
                this.reset()
                this.$emit('success')
              }).catch(() => {
                this.loading = false
              })
            }
          } else {
            this.$message.warning('该教员工号已经存在！')
          }
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
