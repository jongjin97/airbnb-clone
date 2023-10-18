import { useParams, useSearchParams } from "react-router-dom";
import { useAppSelector } from "src/app/hooks";
import Container from "src/components/Container";
import EmptyState from "src/components/EmptyState";
import ListingCard from "src/components/listings/ListingCard";
import { IListingsParams } from "src/interface/listing";


 function Home(){
    const {searchParams} = useParams();
    const currentUser = useAppSelector((state) => state.auth.user);
    //const listing = await getListingById(params);
    console.log(searchParams);
    if (searchParams === null) {
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