import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getListingsById } from 'src/api/listing.api';
import { useAppSelector } from 'src/app/hooks';
import EmptyState from 'src/components/EmptyState';
import ListingClient from 'src/components/clients/ListingClient';
import { ResponseUser } from 'src/interface/auth';
import { Listing } from 'src/interface/listing';
import { Reservation } from 'src/interface/reservation';

interface IParams {
  listingId?: string;
}

const ListingPage = () => {
  const { id } = useParams();
  const currentUser = useAppSelector((state) => state.auth.user);
  const [listing, setListings] = useState<Listing>();
  const [reservations, setReservations] = useState([]);
  useEffect(() => {
    if (id === undefined) {
      return;
    }
    getListingsById(id).then((res) => {
      setListings(res.data.response);
      setReservations(res.data.response.reservation);
    });
  }, []);
  if (listing === undefined) {
    return <EmptyState showReset />;
  }
  return (
    <ListingClient
      listing={listing}
      reservations={reservations}
      currentUser={currentUser}
    />
  );
};

export default ListingPage;
