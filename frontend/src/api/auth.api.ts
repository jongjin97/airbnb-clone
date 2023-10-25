import { FieldValues } from "react-hook-form";
import { RequestSignIn, RequestSignUp } from "../interface/auth";
import { httpApi } from "./http.api";

export const login = (requestSignIn: FieldValues): Promise<any> => 
    httpApi.post<any>('/user/signin', { ...requestSignIn }).then((result) => {
        return result;
    }); 

export const regist = (requestSignUp: FieldValues): Promise<any> =>
    httpApi.post<any>('/user/signup', {...requestSignUp}).then(({data}) => {
        return data;
    });
