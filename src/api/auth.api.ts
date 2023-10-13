import { RequestSignIn, RequestSignUp } from "../interface/auth";
import { httpApi } from "./http.api";

export const login = (requestSignIn: RequestSignIn): Promise<any> => 
    httpApi.post<any>('/user/signin', { ...requestSignIn }).then((result) => {
        return result;
    }); 

export const regist = (requestSignUp: RequestSignUp): Promise<any> =>
    httpApi.post<any>('/user/signup', {...requestSignUp}).then(({data}) => {
        return data;
    });
