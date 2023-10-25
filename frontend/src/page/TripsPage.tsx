import { title } from "node:process";
import { useEffect, useState } from "react";
import { getReservations } from "src/api/reservation.api";
import { useAppSelector } from "src/app/hooks";
import EmptyState from "src/components/EmptyState";
import TripsClient from "src/components/clients/TripsClient";
import { Reservation } from "src/interface/reservation";

const TripsPage =  () => {
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
      );
    }
    
    if (reservations === undefined || reservations.length === 0) {
      return (
          <EmptyState
            title="No trips found"
            subtitle="Looks like you havent reserved any trips."
          />
      );
    }
  
    return (
        <TripsClient
          reservations={reservations}
          currentUser={currentUser}
        />
    );
  }
   
  export default TripsPage;