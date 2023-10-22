import { useEffect, useState } from "react";
import { getReservations } from "src/api/reservation.api";
import { useAppSelector } from "src/app/hooks";
import EmptyState from "src/components/EmptyState";
import ReservationsClient from "src/components/clients/ReservationsClient";
import { Listing } from "src/interface/listing";
import { Reservation } from "src/interface/reservation";

const ReservationsPage = () => {
    const currentUser = useAppSelector((state) => state.auth.user);
    const [reservations, setReservations] = useState<Reservation[]>();
    useEffect(() => {
        const response = getReservations();
        response.then((data) => {
            setReservations(data.data.response);
        });
    },[]);
    if (!currentUser) {
      return (
          <EmptyState
            title="Unauthorized"
            subtitle="Please login"
          />
      )
    }
    if (reservations === undefined || reservations.length === 0) {
      return (
          <EmptyState
            title="No reservations found"
            subtitle="Looks like you have no reservations on your properties."
          />
      );
    }
  
    return (
        <ReservationsClient
          reservations={reservations}
          currentUser={currentUser}
        />
    );
  }
   
  export default ReservationsPage;