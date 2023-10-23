
import axios from 'axios';
import { readToken } from '../service/localStorage.service'
import { useAppDispatch } from 'src/app/hooks';
import { doLogOut } from 'src/features/auth/authAction';
import { openLoginModal } from 'src/features/modal/LoginModalAction';

export const httpApi = axios.create({
    baseURL: 'http://localhost:8080/api',
    
})

httpApi.defaults.headers.common['Authorization'] = `Bearer ${readToken()}`;

httpApi.interceptors.response.use(response => {
    return response;
}, error => {
    if(error.response.status === 401){
        const dispatch = useAppDispatch();
        window.location.href = '/';
        dispatch(doLogOut());
        dispatch(openLoginModal());
    }
    return Promise.reject(error);
});

// httpApi.interceptors.request.use(config => {
//     const token = readToken();

//     if(token){
//     config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
//   });

  