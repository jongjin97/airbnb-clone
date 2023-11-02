import { useCallback, useEffect, useMemo, useState } from "react";
import Container from "../Container";
import ListingHead from "../listings/ListingHead";
import ListingInfo from "../listings/ListingInfo";
import ListingReservation from "../listings/ListingReservation";
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
import { facilities } from '../Facilitie';
import { httpApi } from "src/api/http.api";
import { createReservation } from "src/api/reservation.api";
import { openMessageModal } from "src/features/modal/MessageModalAction";
import Avatar from "../Avatar";
import { list } from "@material-tailwind/react";
import StarRating from "../StarRating";
import { createChatRoom } from "src/api/chat.api";

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

    const handleMessage = useCallback(() => {
      createChatRoom(listing.user.id).then(() => router('/messages'));
      
    }, []);

    const disabledDates = useMemo(() => {
      let dates: Date[] = [];
      if(reservations === null){
        return dates;

      }
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
        setIsLoading(true);
        createReservation(totalPrice,
          dateRange.startDate,
          dateRange.endDate,
          listing?.id)
        .then(() => {
          toast.success('Listing reserved!');
          setDateRange(initialDateRange);
          router('/trips');
        })
        .catch(() => {
          toast.error('Something went wrong.');
        })
        .finally(() => {
          setIsLoading(false);
        })
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
          <div className="flex flex-col gap-6 mt-28">
            <ListingHead
              title={listing.title}
              imageSrc={`data:image/jpeg;base64,${listing.imageByte[0]}`}
              locationValue={listing.location}
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
                openMessage={handleMessage}
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
            <hr/>
            <div className="flex flex-col gap-6 mt-5">
              <div className="flex flex-row align-middle gap-1 text-2xl mt-1 h-20 items-center">
              <svg className="w-4 h-4 text-black mr-1" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 20">
                  <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"/>
              </svg>
                <p className="ml-2 text-2xl font-bold text-gray-900">{listing.average}</p>
                <span className="w-1 h-1 mx-1.5 bg-gray-500 rounded-full"></span>
                <a href="#" className="text-2xl font-bold text-gray-900 underline hover:no-underline ">{listing.review.length} reviews</a>
              </div>
              {listing.review.map((value) => (
                <article>
                  <div className="flex items-center mb-4 space-x-4">
                      <Avatar src={null} />
                      <div className="space-y-1 font-medium">
                          <p>{value.responseUser.name} </p>
                      </div>
                  </div>
                  <div className="flex items-center mb-1">
                      <StarRating reviewRating={value.rating}/>
                  </div>
                  <p className="mb-2 text-gray-500 dark:text-gray-400">{value.message}</p>
                  <hr/>
                </article>
              ))}
            </div>
          </div>
        </div>
      </Container>
     );
  }
   
  export default ListingClient;