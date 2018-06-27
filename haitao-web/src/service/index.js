import * as login from './login'
import * as department from './sys/department'
import * as position from './sys/position'
import * as moudle from './sys/moudle'
import * as company from './sys/company'
import * as config from './sys/config'
import * as role from './sys/role'
import * as user from './sys/user'
import * as roleMoudle from './sys/rolemoudle'
import * as enumType from './sys/enumType'
import * as buttomRegister from './sys/buttomRegister'
import * as sysRegister from './sys/sysRegister'
import * as menu from './sys/menu'
import * as dictionary from './sys/dictionary'
import * as reportData from './monitoring/reportData'

export default {
  // 登录操作
  basicPlatform_login: login.basicPlatformLogin,
  basicPlatform_logout: login.basicPlatformLogout,
  basicPlatform_updatePwd: login.updatePwd,

  // 模块操作
  moudle_queryTopList: moudle.queryTopList,
  moudle_querySideTree: moudle.querySideTree,
  getForbid: moudle.getForbid,

  // 菜单操作
  menu_List: menu.List,
  menu_Del: menu.Del,
  menu_All: menu.All,
  menu_One: menu.One,
  menu_Add: menu.Add,
  menu_Update: menu.Update,

  // 枚举类型
  enumType_list: enumType.enumTypeList,

  // 按钮操作
  buttomRegisterList_List: buttomRegister.List,
  buttomRegister_Del: buttomRegister.Del,
  buttomRegister_One: buttomRegister.One,
  buttomRegister_update: buttomRegister.update,
  buttomRegister_add: buttomRegister.add,

  // 系统注册
  sysRegisterList_List: sysRegister.List,
  sysRegister_Del: sysRegister.Del,
  sysRegister_One: sysRegister.One,
  sysRegister_update: sysRegister.update,
  sysRegister_add: sysRegister.add,

  // 部门操作
  dep_getDepartmentAll: department.getDepartmentAllData,
  dep_getDepartmentTree: department.getDepartmentTreeData,
  dep_addDepartment: department.addDepartmentData,
  dep_initDepartment: department.initDepartmentData,
  dep_updateDepartment: department.updateDepartment,
  dep_deleteDepartment: department.deleteDepartment,

  // 岗位操作
  pos_getPositionList: position.searchPositionList,
  pos_addPosition: position.addPosition,
  pos_delPosition: position.delPosition,
  pos_updatePosition: position.updatePosition,
  pos_getPosition: position.getPosition,
  pos_getPositionTypes: position.getPositionTypes,
  pos_queryAllPosition: position.queryAllPosition,

  // 单位操作
  com_queryCompanyList: company.queryCompanyList,
  com_findAllCompany: company.queryAllCompany,
  com_queryCompany: company.queryCompany,
  com_addCompany: company.addCompany,
  com_delCompany: company.delCompany,
  com_updateCompany: company.updateCompany,

  // 用户操作
  user_queryUserList: user.queryUserList,
  user_addUser: user.addUser,
  user_delUser: user.delUser,
  user_updateUser: user.updateUser,
  user_getUser: user.getUser,
  user_countUserPage: user.countUser,
  user_getCertificateTypes: user.getCertificateTypes,
  user_getEduTypes: user.getEduTypes,
  user_getPoliticalTypes: user.getPoliticalTypes,
  user_getSexTypes: user.getSexTypes,
  user_queryAllUser: user.queryAllUser,
  user_resetPass: user.resetPass,

  // 配置操作
  conf_querySysParamList: config.querySysParamList,
  conf_addConfig: config.addConfig,
  conf_delConfig: config.delConfig,
  conf_updateConfig: config.updateConfig,
  conf_getConfig: config.getConfig,
  conf_getConfTypes: config.getConfTypes,

  // 角色操作
  role_getRoleList: role.searchRoleList,
  role_addRole: role.addRole,
  role_delRole: role.delRole,
  role_updateRole: role.updateRole,
  role_getRole: role.getRole,
  role_getRoleTypes: role.getRoleTypes,
  role_searchAllRole: role.searchAllRole,

  // 角色权限
  roleMoudle_getList: roleMoudle.queryListRoleMoudle,
  saveRoleMoudel: roleMoudle.saveRoleMoudel,

  // 系统字典Group
  dictionary_getDictionaryGroupList: dictionary.queryDictionaryGroupList,

  // 系统字典Item
  dictionary_getDictionaryItemList: dictionary.queryDictionaryItemList,
  dictionary_addDictionaryItem: dictionary.addSysDictionaryItem,
  dictionary_updateDictionaryItem: dictionary.updateSysDictionaryItem,
  dictionary_queryByItemId: dictionary.queryBySysDictionaryItemId,

  // 报表
  monitoring_getReportData: reportData.getReportData,
  operation_getReportData: reportData.getOprReportData,
  transport_queryMonitorPointListByMonitorType: reportData.queryMonitorPointListByMonitorType
}
