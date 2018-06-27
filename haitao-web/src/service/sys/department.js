import axios from './../util/axios.js'

// 所有部门
export const getDepartmentAllData = () => axios.setAxiosGetPromise('sys/department/getDepartmentAllData')

// 根据用户ID获取部门树
export const getDepartmentTreeData = () => axios.setAxiosGetPromise('sys/department/getDeptTreeByUserId')

// 新增部门
export const addDepartmentData = (data) => axios.setAxiosPostPromise('sys/department/addDepartment', data)

// 初始化部门
export const initDepartmentData = (id) => axios.setAxiosGetPromise('sys/department/getByDeptId', id)

// 编辑部门
export const updateDepartment = (data) => axios.setAxiosPostPromise('sys/department/updateDepartment', data)

// 删除部门
export const deleteDepartment = (id) => axios.setAxiosGetPromise('sys/department/delDepartment', id)
