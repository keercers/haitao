<template>
  <div id="sdView" style="padding: 0px 20px 0px 20px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex">
        <el-col :span="4">
          <el-row>
            <span style="font-size: 18px; line-height: 36px">系统字典</span>
          </el-row>
        </el-col>
        <el-col :span="20">
          <el-row>
            <div style="float: right">
              <el-button type="primary" :disabled="showTrue" @click="handleAdd" icon="plus" >新增</el-button>
            </div>
            <div class="clear"></div>
          </el-row>
        </el-col>
      </el-row>
      <!-- <el-row>
        <hr style="color: #c7c5c5;"/>
      </el-row> -->
      <div>
        <div style="float: left; width: 17%; margin-top: 10px;">
          <el-table ref="dictGroupTable"
              style="border-right: 0px"
              :data="sideList"
              :height="tableHeightDynamic"
              highlight-current-row
              @current-change="handelSelect">
              <el-table-column prop="dictGroupName" label="字典种类"></el-table-column>
          </el-table>
        </div>
        <div style="float:right; width:83%; margin-top: 10px;">
          <el-table ref="dictItemTable"
                      :data="dictItemData" border style="width: 100%;background-color: white;"
                      :height="tableHeightDynamic"
                      highlight-current-row
                      @current-change="handleEdit">
              <el-table-column type="index" label="序号" width="80"></el-table-column>
              <el-table-column prop="dictItemKey" label="字典编号" width="200"></el-table-column>
              <el-table-column prop="dictItemValue" label="字典值" width="200"></el-table-column>
              <el-table-column prop="enabled" label="状态" width="100">
                <template slot-scope="scope">
                  <el-button v-if="scope.row.enabled == 0" type="danger" size="small" :disabled="showTrue">
                    禁用
                  </el-button>
                  <el-button v-else type="success" size="small" :disabled="showTrue">
                    启用
                  </el-button>
                </template>
              </el-table-column>
              <el-table-column></el-table-column>
          </el-table>
        </div>
        <div style="float: left">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentpage"
              :page-sizes="pagesizes"
              :page-size="pagesize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="pagesCount">
          </el-pagination>
        </div>
        <transition name="el-zoom-in-top">
          <el-card v-show="sideCardShow" class="box-card">
            <el-row type="flex" class="row-bg" justify="end">
                <el-button class="close_btn" @click="closeSideCard(false)" style="float: right;" type="primary" icon="close"></el-button>
            </el-row>
            <div style="height: 400px;">
              <el-form :model="result" :rules="rules2"  ref="result" label-height="100px">
                <el-form-item label="字典编号"
                    prop="dictItemKey">
                    <el-input id="tKey" ref="key" v-bind:disabled="keydisable" v-model="result.dictItemKey"></el-input>
                </el-form-item>
                <el-form-item label="字典值"
                    prop="dictItemValue">
                    <el-input id="tValue" ref="value" v-model="result.dictItemValue" :disabled="showTrue"></el-input>
                </el-form-item>
                <el-form-item label="状态" prop="enabled">
                    <el-switch ref="enabled" :disabled="showTrue" on-color="#13ce66" off-color="#ff4949" v-model="sideCard_enabled"></el-switch>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" :disabled="showTrue" @click="save('result')">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-card>
        </transition>
      </div>
    </el-col>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  
  export default {
    name: 'sysDictionary',
    data () {
      var checkDictItemKey = (rule, value, callback) => {
        setTimeout(() => {
          if (this.isSpace(value) || !value) {
            callback(new Error('字典编号不能为空值'))
          }
          callback()
        }, 300)
      }
      var checkDictItemValue = (rule, value, callback) => {
        setTimeout(() => {
          if (this.isSpace(value) || !value) {
            callback(new Error('字典值不能为空值'))
          }
          callback()
        }, 300)
      }
      return {
        tableHeightDynamic: document.body.clientHeight - window.config.topBarHeight - 170,
        showTrue: false,
        // 页眉设置参数
        currentpage: 1, // 当前页数默认是1
        pagesizes: [20, 30, 50], // 条数列
        pagesize: 20, // 行数
        activeNum: 0, // 默认起始行数
        endNum: 20, // 默认结束行数
        tableData: [], // 分页组件传回的分页后数据
        sideCardShow: false,
        rules2: {
          dictItemKey: [
            { validator: checkDictItemKey, trigger: 'bulr, change' }
          ],
          dictItemValue: [
            { validator: checkDictItemValue, trigger: 'bulr, change' }
          ]
        },
        keydisable: false,
        result: {},
        sideCard_enabled: false,
        dictionary: {},
        isUpdate: false
      }
    },
    created () {
      this.getGroupListData(0)
    },
    computed: mapGetters({
      sideList: 'dictionaryGroupList',
      dictItemData: 'dictionaryItemList',
      pagesCount: 'dictionaryItemNum'
    }),
    methods: {
      getGroupListData (i) {
        return this.$store.dispatch('getDictionaryGroupList').catch((err) => {
          this.$message({
            message: '系统字典项目查询失败:' + err,
            type: 'error'
          })
        }).then(() => {
          this.$refs.dictGroupTable.setCurrentRow(this.sideList[i])
        })
      },
      getItemListData () {
        return this.$store.dispatch('getDictionaryItemList', {pageIndex: this.currentpage, pageSize: this.pagesize, dictGroupId: this.$store.state.dictGroupId}).catch((err) => {
          this.$message({
            message: '系统字典列表查询失败:' + err,
            type: 'error'
          })
        })
      },
      handleSizeChange (val) {
        this.pagesize = val
        this.activeNum = (val * this.currentpage) - val
        this.endNum = (val * this.currentpage)
        this.getItemListData()
      },
      handleCurrentChange (val) {
        this.currentpage = val
        this.activeNum = (this.pagesize * val) - this.pagesize
        this.endNum = (this.pagesize * val)
        this.getItemListData()
      },
      closeSideCard (flag) {
        this.sideCardShow = flag
        this.$refs.dictItemTable.setCurrentRow()
      },
      doSideCard (flag) {
        this.sideCardShow = flag
      },
      handelSelect (val) {
        this.$store.state.dictGroupId = val.dictGroupId
        if (this.$store.state.dictGroupId === '122' || this.$store.state.dictGroupId === '123') {
          this.showTrue = true
        } else {
          this.showTrue = false
        }
        this.getItemListData()
        // 关闭面板
        this.sideCardShow = false
      },
      handleEdit (val) {
        if (this.result.dictItemKey) {
          this.$refs['result'].resetFields()
        }
        if (val) {
          this.doSideCard(true) // 打开面板
          this.keydisable = true
          // 数据绑定、赋初始值值
          let temp = JSON.stringify(val)
          this.result = JSON.parse(temp)
          this.sideCard_enabled = val.enabled === 1
          // 其他相关数据绑定
          this.dictionary = val
          // 确认是更新
          this.isUpdate = true
        }
      },
      handleAdd () {
        this.doSideCard(true) // 打开面板
        this.keydisable = false
        if (this.result.dictItemKey) {
          this.$refs['result'].resetFields()
        }
        // 清除选中，若已经选中了item，添加时应该清除选中效果，表示现在状态是新增一个item
        this.$refs.dictItemTable.setCurrentRow() // 这个方法会导致handleEdit()调用(只一次)，此时传入的val为空
        // 确认不是更新而是增加
        this.isUpdate = false
      },
      save (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.isSpace(this.$refs.key.value) || this.isSpace(this.$refs.value.value)) {
              this.$message({
                message: '不能储存空值',
                type: 'error'
              })
              return false
            }
            if (this.isUpdate) {
              // 进入update
              // 编辑之后，数据绑定
              this.dictionary.dictItemKey = this.result.dictItemKey
              this.dictionary.dictItemValue = this.result.dictItemValue
              this.dictionary.enabled = this.sideCard_enabled ? 1 : 0
              this.updateDictionary()
            } else {
              // 进入save
              var data = this.dictItemData
              for (var i = 0; i < data.length; i++) {
                if (this.result.dictItemKey === data[i].dictItemKey) {
                  this.$message({
                    message: '新增字典信息失败，输入的字典编号已存在！',
                    type: 'error'
                  })
                  return false
                }
              }
              this.saveDictionary()
            }
          } else {
            return false
          }
        })
      },
      saveDictionary () {
        // 赋值
        this.dictionary.dictGroupId = this.$store.state.dictGroupId
        this.dictionary.dictItemId = this.dictionary.dictGroupId + '-' + this.result.dictItemKey
        this.dictionary.dictItemFullKey = this.result.dictItemKey
        this.dictionary.dictItemFullValue = this.result.dictItemValue
        this.$store.dispatch('addDictionaryItem',
          {dictItemId: this.dictionary.dictItemId,
            dictItemKey: this.result.dictItemKey,
            dictItemValue: this.result.dictItemValue,
            dictItemFullKey: this.dictionary.dictItemFullKey,
            dictItemFullValue: this.dictionary.dictItemFullValue,
            parentItemId: null,
            dictGroupId: this.dictionary.dictGroupId,
            enabled: this.sideCard_enabled ? 1 : 0}
        ).then((reply) => {
          // 关闭面板
          this.sideCardShow = false
          for (var i = 0; i < this.sideList.length; i++) {
            if (this.sideList[i].dictGroupId === this.$store.state.dictGroupId) {
              this.getGroupListData(i) // 避免新增item，不刷新的时候出现空行的情况
              break
            }
          }
          this.$message({
            message: '新增字典信息成功！',
            type: 'success'
          })
        }).catch(_ => {
          this.$message({
            message: '新增字典信息失败！',
            type: 'error'
          })
        })
      },
      updateDictionary () {
        this.$store.dispatch('updateDictionaryItem',
          {dictItemId: this.dictionary.dictItemId,
            dictItemKey: this.dictionary.dictItemKey,
            dictItemValue: this.dictionary.dictItemValue,
            dictItemFullKey: this.dictionary.dictItemKey,
            dictItemFullValue: this.dictionary.dictItemValue,
            parentItemId: this.dictionary.parentItemId,
            dictGroupId: this.dictionary.dictGroupId,
            enabled: this.dictionary.enabled}
        ).then((reply) => {
          // 关闭面板
          this.sideCardShow = false
          // 清除选中
          this.$refs.dictItemTable.setCurrentRow()
          this.$message({
            message: '更新字典信息成功！',
            type: 'success'
          })
        }).catch(_ => {
          this.$message({
            message: '更新字典信息失败！',
            type: 'error'
          })
        })
      },
      isSpace (s) {
        s = s + ''
        s = s.trim()
        return s.length === 0
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  /*微菜单css*/
  .menu {
    width:180px;
  }
  .box-card {
    position: absolute;
    top: 10%;
    right: 0px;
    width: 340px;
    height: 72.1%;
    z-index: 999;
    overflow: auto;
    background-color: rgba(255, 255, 255, 1);
  }
  .close_btn {
    height: 30px;
    padding: 7px 7px;
    border-radius: 4px;
  }
</style>

<style>
  #sdView .el-input {
    width: 180px;
  }
  #sdView .el-input__inner {
    width: 180px;
  }
</style>
