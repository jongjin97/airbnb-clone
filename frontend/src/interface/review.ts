import { ResponseUser } from "./auth";

export interface Review {
    rating: number;
    message: string;
    user: ResponseUser;
}