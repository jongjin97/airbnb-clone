import { createAsyncThunk } from "@reduxjs/toolkit";
import { RequestSignIn, RequestSignUp, ResponseUser } from "../../interface/auth";
import { login, regist } from "../../api/auth.api";
import { setUser } from "./authReducer";
import { deleteToken, deleteUser, persistToken } from "../../service/localStorage.service";
import { FieldValues } from "react-hook-form";
import toast from "react-hot-toast";
import { closeLoginModal, openLoginModal } from "../modal/LoginModalAction";
import { closeRegisterModal } from "../modal/RegisterModalAction";

export const doLogin = createAsyncThunk('auth/doLogin', async (requestSignIn: FieldValues, {dispatch}) =>
  login(requestSignIn).then((callback) => {
    persistToken(callback.headers.authorization);
    dispatch(setUser(callback.data.response));
    toast.success('Logged in');
    dispatch(closeLoginModal());
    
    return callback.data.response;
  }).catch((callback) => {
    console.log(callback);
    toast.error(callback.response.data.error.message);
    return callback;
  }));
  export const updateUser = createAsyncThunk('auth/doUpdate', async (responseUser: any, {dispatch}) =>
    dispatch(setUser(responseUser))
  );
// export const doSignUp = createAsyncThunk('auth/doSignUp', async (requestSignUp: RequestSignUp) =>
//   regist(requestSignUp));

export const doSignUp = createAsyncThunk('auth/doSignUp', async (requestSignUp: FieldValues, {dispatch}) => {
  regist(requestSignUp)
  .then((callback) => {
    toast.success('Registered!');
    dispatch(closeRegisterModal());
    dispatch(openLoginModal());
    console.log(callback);
    return callback.response;
  }).catch((callback) => {
    toast.error(callback.response.data.error.message);
    return callback;
  })});

export const doLogOut = createAsyncThunk('auth/doLogOut', (payload, {dispatch}) => {
  deleteToken();
  deleteUser();
  dispatch(setUser(null));

});