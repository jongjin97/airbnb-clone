import { useCallback, useEffect, useMemo, useState } from "react";
import Container from "../Container";
import ListingHead from "./ListingHead";
import ListingInfo from "./ListingInfo";
import ListingReservation from "./ListingReservation";
import { differenceInDays, eachDayOfInterval } from "date-fns";
import toast from "react-hot-toast";
import { categories } from "../navbar/Categories";
import { useAppDispatch, useAppSelector } from "src/app/hooks";
import { Range } from "react-date-range";
import { useNavigate } from "react-router-dom";
import { openLoginModal } from "src/features/modal/LoginModalAction";
import { ResponseUser } from "src/interface/auth";
import { Listing } from "src/interface/listing";
import { Reservation } from "src/interface/reservation";
import { facilities } from './../Facilitie';

const initialDateRange = {
    startDate: new Date(),
    endDate: new Date(),
    key: 'selection'
  };
  
  interface ListingClientProps {
    reservations?: Reservation[];
    listing: Listing & {
      user: ResponseUser;
    };
    currentUser?: ResponseUser | null;
  }
  
  const ListingClient: React.FC<ListingClientProps> = ({
    listing,
    reservations = [],
    currentUser
  }) => {
    const loginModal = useAppSelector((state) => state.login.isOpen);
    const router = useNavigate();
    const dispatch = useAppDispatch();
    const disabledDates = useMemo(() => {
      let dates: Date[] = [];
  
      reservations.forEach((reservation: any) => {
        const range = eachDayOfInterval({
          start: new Date(reservation.startDate),
          end: new Date(reservation.endDate)
        });
  
        dates = [...dates, ...range];
      });
  
      return dates;
    }, [reservations]);
  
    const category = useMemo(() => {
       return categories.find((items) => 
        items.label === listing.category);
    }, [listing.category]);

    const facility = useMemo(() => {
      return listing.facility.map((facilityLabel) =>
      facilities.find((item) => item.label === facilityLabel));
    }, [listing.facility]);

    const [isLoading, setIsLoading] = useState(false);
    const [totalPrice, setTotalPrice] = useState(listing.price);
    const [dateRange, setDateRange] = useState<Range>(initialDateRange);

    const onCreateReservation = useCallback(() => {
        if (!currentUser) {
          return dispatch(openLoginModal());
        }
        // setIsLoading(true);
  
        // axios.post('/api/reservations', {
        //   totalPrice,
        //   startDate: dateRange.startDate,
        //   endDate: dateRange.endDate,
        //   listingId: listing?.id
        // })
        // .then(() => {
        //   toast.success('Listing reserved!');
        //   setDateRange(initialDateRange);
        //   router('/trips');
        // })
        // .catch(() => {
        //   toast.error('Something went wrong.');
        // })
        // .finally(() => {
        //   setIsLoading(false);
        // })
    },
    [
      totalPrice, 
      dateRange, 
      listing?.id,
      router,
      currentUser,
      loginModal
    ]);
  
    useEffect(() => {
      if (dateRange.startDate && dateRange.endDate) {
        const dayCount = differenceInDays(
          dateRange.endDate, 
          dateRange.startDate
        );
        
        if (dayCount && listing.price) {
          setTotalPrice(dayCount * listing.price);
        } else {
          setTotalPrice(listing.price);
        }
      }
    }, [dateRange, listing.price]);
  
    return ( 
      <Container>
        <div 
          className="
            max-w-screen-lg 
            mx-auto
          "
        >
          <div className="flex flex-col gap-6">
            <ListingHead
              title={listing.title}
              imageSrc={`data:image/jpeg;base64,${listing.imageByte}`}
              locationValue={listing.locationValue}
              id={listing.id}
              currentUser={currentUser}
            />
            <div 
              className="
                grid 
                grid-cols-1 
                md:grid-cols-7 
                md:gap-10 
                mt-6
              "
            >
              <ListingInfo
                user={listing.user}
                category={category}
                description={listing.description}
                roomCount={listing.roomCount}
                guestCount={listing.guestCount}
                bathroomCount={listing.bathroomCount}
                locationValue={listing.location.value}
                facility={facility}
              />
              <div 
                className="
                  order-first 
                  mb-10 
                  md:order-last 
                  md:col-span-3
                "
              >
                <ListingReservation
                  price={listing.price}
                  totalPrice={totalPrice}
                  onChangeDate={(value) => setDateRange(value)}
                  dateRange={dateRange}
                  onSubmit={onCreateReservation}
                  disabled={isLoading}
                  disabledDates={disabledDates}
                />
              </div>
            </div>
          </div>
        </div>
      </Container>
     );
  }
   
  export default ListingClient;