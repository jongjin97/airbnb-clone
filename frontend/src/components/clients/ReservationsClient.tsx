import toast from 'react-hot-toast';
import Container from '../Container';
import Heading from '../Heading';
import ListingCard from '../listings/ListingCard';
import { useNavigate } from 'react-router-dom';
import { Reservation } from 'src/interface/reservation';
import { ResponseUser } from 'src/interface/auth';
import { useCallback, useState } from 'react';
import { deleteReservation } from 'src/api/reservation.api';

interface ReservationsClientProps {
  reservations: Reservation[];
  currentUser?: ResponseUser | null;
}

const ReservationsClient: React.FC<ReservationsClientProps> = ({
  reservations,
  currentUser,
}) => {
  const router = useNavigate();
  const [deletingId, setDeletingId] = useState('');

  const onCancel = useCallback(
    (id: string) => {
      setDeletingId(id);

      deleteReservation(id)
        .then(() => {
          toast.success('Reservation cancelled');
          router(0);
        })
        .catch(() => {
          toast.error('Something went wrong.');
        })
        .finally(() => {
          setDeletingId('');
        });
    },
    [router]
  );

  return (
    <Container>
      <Heading title="Reservations" subtitle="Bookings on your properties" />
      <div
        className="
            mt-10
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
        {reservations.map((reservation: any) => (
          <ListingCard
            key={reservation.id}
            data={reservation.listing}
            reservation={reservation}
            actionId={reservation.id}
            onAction={onCancel}
            disabled={deletingId === reservation.id}
            actionLabel="Cancel guest reservation"
            currentUser={currentUser}
          />
        ))}
      </div>
    </Container>
  );
};

export default ReservationsClient;
