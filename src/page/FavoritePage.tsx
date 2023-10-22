import { useEffect, useState } from "react";
import { getFavorite } from "src/api/favorite.api";
import { useAppSelector } from "src/app/hooks";
import EmptyState from "src/components/EmptyState";
import FavoritesClient from "src/components/clients/FavoritesClient";
import { Listing } from "src/interface/listing";

const FavoritePage = () => {
    const [listings, setListings] = useState<Listing[]>();
    const currentUser = useAppSelector((state) => state.auth.user);
    useEffect(() => {
        const response = getFavorite();
        response.then((data) => {
            setListings(data.data.response);
        })
    }, [])
    if (listings === undefined) {
      return (
          <EmptyState
            title="No favorites found"
            subtitle="Looks like you have no favorite listings."
          />
      );
    }
  
    return (
        <FavoritesClient
          listings={listings}
          currentUser={currentUser}
        />
    );
  }
   
  export default FavoritePage;