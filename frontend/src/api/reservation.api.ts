import { httpApi } from './http.api';

export const createReservation = (
  totalPrice: number,
  startDate: Date | undefined,
  endDate: Date | undefined,
  listingId: string
) => {
  return httpApi.post('/reservation', {
    totalPrice,
    startDate,
    endDate,
    listingId,
  });
};

export const getReservations = () => {
  return httpApi.get('/reservation');
};
export const deleteReservation = (id: string) => {
  return httpApi.delete(`/reservation/${id}`);
};
