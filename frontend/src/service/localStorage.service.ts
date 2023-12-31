import { httpApi } from 'src/api/http.api';
import { ResponseUser } from '../interface/auth';

export const persistToken = (token: string) => {
  localStorage.setItem('accessToken', token.replace('Bearer ', ''));
  httpApi.defaults.headers.common['Authorization'] = `Bearer ${readToken()}`;
};

export const readToken = (): string | null => {
  return localStorage.getItem('accessToken');
};

export const deleteToken = () => {
  localStorage.removeItem('accessToken');
  httpApi.defaults.headers.common['Authorization'] = ``;
};

export const persistUser = (user: ResponseUser) => {
  localStorage.setItem('user', JSON.stringify(user));
};

export const readUser = (): ResponseUser | null => {
  const userStr = localStorage.getItem('user');
  return userStr ? JSON.parse(userStr) : null;
};

export const deleteUser = () => {
  localStorage.removeItem('user');
};
