export interface RequestSignUp{
    email: string,
    password: string,
    nickname: string,
}

export interface RequestSignIn{
    email: string,
    password: string,
}

export interface ResponseUser {
    email: string,
    id: number,
    nickname: string,
    role: string,
    img: Uint8Array,
    favoriteIds: number[],
}