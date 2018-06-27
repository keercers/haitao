import axios from './../util/axios.js'

// 连接后台进行查询角色列表

export const searchRoleList = (options) => axios.setAxiosGetPromise('sys/role/getRoleList', options)

// 查询所有角色
export const searchAllRole = (options) => axios.setAxiosGetPromise('sys/role/findAllRole')

// 新增角色
export const addRole = (position) => axios.setAxiosPostPromise('sys/role/addRole', position)

// 删除角色
export const delRole = (roleId) => axios.setAxiosGetPromise('sys/role/deleteRole', roleId)

// 编辑角色
export const updateRole = (position) => axios.setAxiosPostPromise('sys/role/updateRole', position)

// 查找角色
export const getRole = (roleId) => axios.setAxiosGetPromise('sys/role/getByRoleId', roleId)

// 加载角色类型列表
export const getRoleTypes = () => axios.setAxiosGetPromise('sys/enum/listEnumValues', {
  enumName: 'RoleTypeEnum'
})
