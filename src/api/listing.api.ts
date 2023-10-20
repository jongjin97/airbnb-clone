import { IListingsParams, ResponseListing } from "src/interface/listing";
import { httpApi } from "./http.api";

export default async function getListings(
    params: IListingsParams
  ):Promise<any>{
    return await httpApi.get('/accommodation/lists', {params}).then((result) => {
        return result;
    })
  }