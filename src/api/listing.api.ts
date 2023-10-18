import { IListingsParams } from "src/interface/listing";

export default async function getListings(
    params: IListingsParams
  ) {
    try {
      const {
        userId,
        roomCount, 
        guestCount, 
        bathroomCount, 
        locationValue,
        startDate,
        endDate,
        category,
      } = params;
  
      let query: any = {};
  
      if (userId) {
        query.userId = userId;
      }
  
      if (category) {
        query.category = category;
      }
  
      if (roomCount) {
        query.roomCount = {
          gte: +roomCount
        }
      }
  
      if (guestCount) {
        query.guestCount = {
          gte: +guestCount
        }
      }
  
      if (bathroomCount) {
        query.bathroomCount = {
          gte: +bathroomCount
        }
      }
  
      if (locationValue) {
        query.locationValue = locationValue;
      }
  
      if (startDate && endDate) {
        query.NOT = {
          reservations: {
            some: {
              OR: [
                {
                  endDate: { gte: startDate },
                  startDate: { lte: startDate }
                },
                {
                  startDate: { lte: endDate },
                  endDate: { gte: endDate }
                }
              ]
            }
          }
        }
      }
      console.log(query);
    } catch (error) {

    }
}
