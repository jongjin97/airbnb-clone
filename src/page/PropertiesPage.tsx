import { useEffect, useState } from "react";
import { getListings, getMyListings } from "src/api/listing.api";
import { useAppSelector } from "src/app/hooks";
import EmptyState from "src/components/EmptyState";
import PropertiesClient from "src/components/clients/PropertiesClient";
import { Listing } from "src/interface/listing";

const PropertiesPage = () => {
    const currentUser = useAppSelector(state => state.auth.user)
    const [listings, setListings] = useState<Listing[]>();

    useEffect(() => {
        getMyListings().then(res => {
        setListings(res.data.response);
      })
    }, [])
    if (!currentUser) {
      return <EmptyState
        title="Unauthorized"
        subtitle="Please login"
      />
    }
  
  
    if (listings  === undefined || listings.length === 0) {
      return (
          <EmptyState
            title="No properties found"
            subtitle="Looks like you have no properties."
          />
      );
    }
  
    return (
        <PropertiesClient
          listings={listings}
          currentUser={currentUser}
        />
    );
  }
   
  export default PropertiesPage;