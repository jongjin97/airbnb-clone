import { IListingsParams } from 'src/interface/listing';
import { httpApi } from './http.api';

export async function getMyListings(): Promise<any> {
  return await httpApi.get('/accommodation');
}

export async function getListings(params: IListingsParams): Promise<any> {
  return await httpApi.get('/accommodation/lists', { params });
}

export async function getListingsById(id: string): Promise<any> {
  return await httpApi.get(`/accommodation/${id}`);
}

export async function deleteListingsById(id: string): Promise<any> {
  return await httpApi.delete(`/accommodation/${id}`);
}
