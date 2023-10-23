
import axios from 'axios';
import { readToken } from '../service/localStorage.service'

export const httpApi = axios.create({
    baseURL: 'http://localhost:8080/api',
    
})

httpApi.defaults.headers.common['Authorization'] = `Bearer ${readToken()}`;

// httpApi.interceptors.request.use(config => {
//     const token = readToken();

//     if(token){
//     config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
//   });

  