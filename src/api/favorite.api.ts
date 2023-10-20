import { httpApi } from "./http.api";


export function deleteFavorite(id : string){
    return httpApi.delete(`/favorite/${id}`);
}

export function saveFavorite(id : string){
    return httpApi.post(`/favorite/${id}`);
}