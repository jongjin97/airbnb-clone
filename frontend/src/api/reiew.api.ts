import { FieldValues } from "react-hook-form";
import { httpApi } from "./http.api";

export function saveReview(id : string, data: FieldValues){
    return httpApi.post(`/review/${id}`, {...data});
}