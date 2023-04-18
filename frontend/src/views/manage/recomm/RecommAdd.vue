<template>
  <a-modal v-model="show" title="新增课程推荐" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose"> 取消 </a-button>
      <a-button
        key="submit"
        type="primary"
        :loading="loading"
        @click="handleSubmit"
      >
        提交
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label="专业类型" v-bind="formItemLayout">
            <a-input
              v-decorator="[
                'discipline',
                { rules: [{ required: true, message: '请输入专业类型!' }] },
              ]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="推荐课程" v-bind="formItemLayout">
            <div>
              <div :style="{ borderBottom: '1px solid #E9E9E9' }">
                <a-checkbox
                  :indeterminate="indeterminate"
                  :checked="checkAll"
                  @change="onCheckAllChange"
                >
                  Check all
                </a-checkbox>
              </div>
              <br />
              <a-checkbox-group
                v-model="checkedList"
                :options="plainOptions"
                @change="onChange"
              >
                <span slot="label" slot-scope="{ value }" style="color: red">{{
                  value
                }}</span>
              </a-checkbox-group>
            </div>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="备注" v-bind="formItemLayout">
            <a-textarea
              :rows="6"
              v-decorator="[
                'remark',
                { rules: [{ required: true, message: '请输入备注!' }] },
              ]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12"></a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import { mapState } from "vuex";
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 },
};
export default {
  name: "recommAdd",
  props: {
    recommAddVisiable: {
      default: false,
    },
  },
  computed: {
    ...mapState({
      currentUser: (state) => state.account.user,
    }),
    show: {
      get: function () {
        return this.recommAddVisiable;
      },
      set: function () {},
    },
  },
  data() {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      courseList: [],
      checkedList: [],
      indeterminate: true,
      checkAll: false,
      plainOptions: [],
    };
  },
  mounted() {
    this.selectCourseList();
  },
  methods: {
    selectCourseList() {
      this.$get("/cos/course-info/list").then((r) => {
        this.plainOptions = r.data.data;
      });
    },
    onChange(checkedList) {
      this.indeterminate =
        !!checkedList.length && checkedList.length < this.plainOptions.length;
      this.checkAll = checkedList.length === this.plainOptions.length;
    },
    onCheckAllChange(e) {
      Object.assign(this, {
        checkedList: e.target.checked ? this.plainOptions : [],
        indeterminate: false,
        checkAll: e.target.checked,
      });
    },
    reset() {
      this.loading = false;
      this.form.resetFields();
    },
    onClose() {
      this.reset();
      this.$emit("close");
    },
    handleCancel() {
      this.previewVisible = false;
    },
    handleSubmit() {
      this.form.validateFields((err, values) => {
        if (!err) {
          values.courseIds =
            this.checkedList.length > 0 ? this.checkedList.join(",") : null;
          this.loading = true;
          this.$post("/cos/course-recomm", {
            ...values,
          })
            .then((r) => {
              this.reset();
              this.$emit("success");
            })
            .catch(() => {
              this.loading = false;
            });
        }
      });
    },
  },
};
</script>

<style scoped>
</style>
