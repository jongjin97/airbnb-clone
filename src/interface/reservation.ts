import { ResponseUser } from "./auth";
import { Listing } from "./listing";

export interface Reservation {
    id: string;
    userId: string;
    listingId: string;
    startDate: Date;
    endDate: Date;
    totalPrice: number;
    createdAt: Date;
  
    user: ResponseUser; // Assuming User interface is defined
    listing: Listing; // Assuming Listing interface is defined
  }
  