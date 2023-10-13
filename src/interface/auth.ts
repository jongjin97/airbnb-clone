export interface RequestSignUp{
    email: string,
    password: string,
    nickname: string,
}

export interface RequestSignIn{
    email: string,
    password: string,
}
//수정 필요
export interface ResponseLogin {
    email: string,
    id: number,
    nuckname: string,
    role: string,
}

export interface ResponseUser {
    email: string,
    id: number,
    nickname: string,
    role: string,
}