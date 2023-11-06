import { IconType } from 'react-icons';
import { ResponseUser } from './auth';
import { Reservation } from './reservation';
import { Review } from './review';

export interface Listing {
  id: string;
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
  imageByte: Uint8Array;
  location: CountrySelectValue;
  facility: string[];
  user: ResponseUser; // Assuming User interface is defined
  reservations?: Reservation[]; // Assuming Reservation interface is defined
  average: number;
  review: Review[];
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
export type CountrySelectValue = {
  flag: string;
  label: string;
  latlng: number[];
  region: string;
  value: string;
};
export interface ResponseListing {
  id: string;
  guestCount?: number;
  roomCount?: number;
  bathroomCount?: number;
  title: string;
  description: string;
  price: number;
  user: ResponseUser;
  location: CountrySelectValue;
  category: string;
  facility: string[];
  imageByte: Uint8Array;
  average: number;
  review: Review[];
}
