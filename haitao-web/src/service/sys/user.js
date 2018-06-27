
import axios from './../util/axios.js'

export const queryUserList = (options) => axios.setAxiosGetPromise('sys/user/listUserByParams', options)

export const queryAllUser = (options) => axios.setAxiosGetPromise('sys/user/listAllUser')

export const getUser = (options) => axios.setAxiosGetPromise('sys/user/getUserInfo', options)

export const addUser = (options) => axios.setAxiosPostPromise('sys/user/createUser', options)

export const delUser = (options) => axios.setAxiosGetPromise('sys/user/deleteUser', options)

export const updateUser = (options) => axios.setAxiosPostPromise('sys/user/updateUser', options)

export const resetPass = (options) => axios.setAxiosGetPromise('sys/user/resetPassword', options)

export const countUser = (options) => axios.setAxiosGetPromise('sys/user/countUser', options)

export const getCertificateTypes = () => axios.setAxiosGetPromise('sys/enum/listEnumValues', { enumName: 'CertificateEnum' })

export const getEduTypes = () => axios.setAxiosGetPromise('sys/enum/listEnumValues', { enumName: 'EduEnum' })

export const getPoliticalTypes = () => axios.setAxiosGetPromise('sys/enum/listEnumValues', { enumName: 'PoliticalEnum' })

export const getSexTypes = () => axios.setAxiosGetPromise('sys/enum/listEnumValues', { enumName: 'SexEnum' })
