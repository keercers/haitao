import axios from '../util/axios'

export const queryCompany = (options) => axios.setAxiosGetPromise('sys/company/queryByComId', options)

export const queryCompanyList = (options) => axios.setAxiosGetPromise('sys/company/queryCompanyList', options)

export const queryAllCompany = (options) => axios.setAxiosGetPromise('sys/company/listAllCompany', options)

export const addCompany = (data) => axios.setAxiosPostPromise('sys/company/saveCompany', data)

export const delCompany = (options) => axios.setAxiosGetPromise('sys/company/deleteCompany', options)

export const updateCompany = (company) => axios.setAxiosPostPromise('sys/company/updateCompany', company)
