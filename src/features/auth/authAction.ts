import { createAsyncThunk } from "@reduxjs/toolkit";
import { RequestSignIn, RequestSignUp } from "../../interface/auth";
import { login, regist } from "../../api/auth.api";
import { setUser } from "./authReducer";
import { deleteToken, deleteUser, persistToken } from "../../service/localStorage.service";

export const doLogin = createAsyncThunk('auth/doLogin', async (requestSignIn: RequestSignIn, {dispatch}) =>
  login(requestSignIn).then((result) => {
    console.log(result);
    persistToken(result.headers.authorization);
    //persistUser(result.data);
    dispatch(setUser(result.data));
    return result.data;
  }));

// export const doSignUp = createAsyncThunk('auth/doSignUp', async (requestSignUp: RequestSignUp) =>
//   regist(requestSignUp));

export const doSignUp = createAsyncThunk('auth/doSignUp', async (requestSignUp: RequestSignUp) => {
  const response = await regist(requestSignUp);
  console.log(response);
  return response.data; 
});

export const doLogOut = createAsyncThunk('auth/doLogOut', (payload, {dispatch}) => {
  deleteToken();
  deleteUser();
  dispatch(setUser(null));

});