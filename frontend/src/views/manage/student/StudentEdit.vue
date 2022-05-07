<template>
  <a-modal v-model="show" title="修改学生信息" @cancel="onClose" :width="800">
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
          <a-form-item label='学生姓名' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入学生姓名!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='学号' v-bind="formItemLayout">
            <a-input v-decorator="[
            'studentId',
            { rules: [{ required: true, message: '请输入学号!' }] }
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
        <a-col :span="12">
          <a-form-item label='专业' v-bind="formItemLayout">
            <a-input v-decorator="[
            'discipline',
            { rules: [{ required: true, message: '请输入专业!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='班级' v-bind="formItemLayout">
            <a-input v-decorator="[
            'adminClass',
            { rules: [{ required: true, message: '请输入班级!' }] }
            ]"/>
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
  name: 'StudentEdit',
  props: {
    studentEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.studentEditVisiable
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
    setFormValues ({...student}) {
      this.rowId = student.id
      let fields = ['name', 'studentId', 'sex', 'discipline', 'adminClass']
      let obj = {}
      Object.keys(student).forEach((key) => {
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = student[key]
        }
        if (key === 'sex') {
          obj[key] = student[key].toString()
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
        this.$get('/cos/student-info/repeat', {studentId: values.studentId, tid: values.id}).then((r) => {
          if (r.data.data === 0) {
            if (!err) {
              this.loading = true
              this.$put('/cos/student-info', {
                ...values
              }).then((r) => {
                this.reset()
                this.$emit('success')
              }).catch(() => {
                this.loading = false
              })
            }
          } else {
            this.$message.warning('该学号已经存在！')
          }
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
