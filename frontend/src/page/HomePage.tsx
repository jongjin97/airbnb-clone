import { useEffect, useState } from "react";
import { useLocation, useParams, useSearchParams } from "react-router-dom";
import { getListings } from "src/api/listing.api";
import { useAppSelector } from "src/app/hooks";
import Container from "src/components/Container";
import EmptyState from "src/components/EmptyState";
import ListingCard from "src/components/listings/ListingCard";
import { IListingsParams, ResponseListing } from "src/interface/listing";

function convertSearchParamsToParamsObject(params: URLSearchParams): IListingsParams {
  const listingsParams: IListingsParams = {};

  if (params.has('guestCount')) {
    const guestCountParam = params.get('guestCount');
    listingsParams.guestCount = guestCountParam ? parseInt(guestCountParam, 10) : undefined;
  }

  if (params.has('roomCount')) {
    const roomCountParam = params.get('roomCount');
    listingsParams.roomCount = roomCountParam ? parseInt(roomCountParam, 10) : undefined;
  }

  if (params.has('bathroomCount')) {
    const bathroomCountParam = params.get('bathroomCount');
    listingsParams.bathroomCount = bathroomCountParam ? parseInt(bathroomCountParam, 10) : undefined;
  }
  if (params.has('locationValue')) {
    const locationValueParam = params.get('locationValue');
    listingsParams.locationValue = locationValueParam ? locationValueParam : undefined;
  }
  if (params.has('category')) {
    const categoryParam = params.get('category');
    listingsParams.category = categoryParam ? categoryParam : undefined;
  }
  if (params.has('startDate')) {
    const startDateParam = params.get('startDate');
    listingsParams.startDate = startDateParam ? startDateParam : undefined;
  }
  if (params.has('endDate')) {
    const endDateParam = params.get('endDate');
    listingsParams.endDate = endDateParam ? endDateParam : undefined;
  }
  return listingsParams;
}
 function Home(){
    const [URLSearchParams, SetURLSearchParams] = useSearchParams();
    const [listings, setListings] = useState<ResponseListing[]>();
    const currentUser = useAppSelector(state => state.auth.user);
    useEffect(() => {
      getListings(convertSearchParamsToParamsObject(URLSearchParams))
      .then(res => {
        console.log(res.data.response);
        setListings(res.data.response)})
      .catch(err => console.log(err));
    },[URLSearchParams]);

    if (listings?.length === 0) {
      return (
          <EmptyState showReset />
      );
    }
    return (
        <Container>
          <div 
            className="
              pt-24
              grid 
              grid-cols-1 
              sm:grid-cols-2 
              md:grid-cols-3 
              lg:grid-cols-4
              xl:grid-cols-5
              2xl:grid-cols-6
              gap-8
            "
          >
            {listings?.map((listing: any) => (
              <ListingCard
                currentUser={currentUser}
                key={listing.id}
                data={listing}
              />
            ))}
          </div>
        </Container>
    )
  }
  
  export default Home;