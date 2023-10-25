import { errorInterface } from "../../interface/error";

export const SET_ERROR = "SET_ERROR";
export const HIDE_ERROR = "HIDE_ERROR";

export function setError(error: any){
    const modifiedError: errorInterface = {
        code: error.code,
        message: error.message,
        name: error.name,
      };
 return {
 type: SET_ERROR,
 error: modifiedError
 }
}

export function hideError(){
 return {
 type: HIDE_ERROR
 }
}