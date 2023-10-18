import { ResponseUser } from "./auth";
import { Reservation } from "./reservation";

export interface Listing {
  id: number;
  title: string;
  description: string;
  imageSrc: string;
  createdAt: Date;
  category: string;
  roomCount: number;
  bathroomCount: number;
  guestCount: number;
  locationValue: string;
  userId: string;
  price: number;

  user: ResponseUser; // Assuming User interface is defined
  reservations?: Reservation[]; // Assuming Reservation interface is defined
}

export interface IListingsParams {
  userId?: string;
  guestCount?: number;
  roomCount?: number;
  bathroomCount?: number;
  startDate?: string;
  endDate?: string;
  locationValue?: string;
  category?: string;
}