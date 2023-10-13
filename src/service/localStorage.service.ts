import { ResponseLogin } from "../interface/auth";

export const persistToken = (token: string) => {
    localStorage.setItem('accessToken', token);
}

export const readToken = (): string | null => {
    return localStorage.getItem('accessToken');
}

export const deleteToken = () => {
    localStorage.removeItem('accessToken');
}


export const persistUser = (user: ResponseLogin) => {
    localStorage.setItem('user', JSON.stringify(user));
}

export const readUser = (): ResponseLogin | null => {
    const userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
}

export const deleteUser = () => {
    localStorage.removeItem('user');
}