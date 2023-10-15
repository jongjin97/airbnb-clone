import { FieldValue, FieldValues } from "react-hook-form";
import { httpApi } from "./http.api";

export const createRent = (reqeustAccommodate: FieldValues) => {
    return httpApi.post<any>('/accommodation', {...reqeustAccommodate}).then((result) => {
        return result;
    })
}

export const login = (requestSignIn: FieldValues): Promise<any> => 
    httpApi.post<any>('/user/signin', { ...requestSignIn }).then((result) => {
        return result;
    }); 