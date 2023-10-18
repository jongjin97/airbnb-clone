import { useLocation, useParams, useSearchParams } from "react-router-dom";
import getListings from "src/api/listing.api";
import { useAppSelector } from "src/app/hooks";
import Container from "src/components/Container";
import EmptyState from "src/components/EmptyState";
import ListingCard from "src/components/listings/ListingCard";
import { IListingsParams } from "src/interface/listing";

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
  console.log(listingsParams);
  return listingsParams;
}
 function Home(){
    const [URLSearchParams, SetURLSearchParams] = useSearchParams();
    const listings = getListings(convertSearchParamsToParamsObject(URLSearchParams));
    if (URLSearchParams.size === 0) {
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
            {/* {listings.map((listing: any) => (
              <ListingCard
                currentUser={currentUser}
                key={listing.id}
                data={listing}
              />
            ))} */}
            <h1>test</h1>
          </div>
        </Container>
    )
  }
  
  export default Home;