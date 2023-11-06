import { FieldValues } from 'react-hook-form';
import { httpApi } from './http.api';

export const createRent = (reqeustAccommodate: FieldValues) => {
  return httpApi
    .post<any>('/accommodation', { ...reqeustAccommodate })
    .then((result) => {
      return result;
    });
};
