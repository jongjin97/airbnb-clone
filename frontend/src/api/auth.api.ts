import { FieldValues } from 'react-hook-form';
import { httpApi } from './http.api';

export const login = (requestSignIn: FieldValues): Promise<unknown> =>
  httpApi.post<any>('/user/signin', { ...requestSignIn }).then((result) => {
    return result;
  });

export const regist = (requestSignUp: FieldValues): Promise<any> =>
  httpApi.post<any>('/user/signup', { ...requestSignUp }).then(({ data }) => {
    return data;
  });
