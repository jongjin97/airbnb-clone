import { format } from "date-fns";
import Button from "../Button";
import { useCallback, useMemo } from "react";
import useCountries from "src/hooks/useCountries";
import { ResponseUser } from "src/interface/auth";
import { useNavigate } from "react-router-dom";
import HeartButton from "../HeartButton";
import { Listing, ResponseListing } from "src/interface/listing";
import { Reservation } from "src/interface/reservation";
import { useAppDispatch } from "src/app/hooks";
import { openReviewModal } from "src/features/modal/ReviewModalAction";

interface ListingCardProps {
    data: ResponseListing;
    reservation?: Reservation;
    onAction?: (id: string) => void;
    disabled?: boolean;
    actionLabel?: string;
    actionId?: string;
    currentUser?: ResponseUser | null
  };
  
  const ListingCard: React.FC<ListingCardProps> = ({
    data,
    reservation,
    onAction,
    disabled,
    actionLabel,
    actionId = '',
    currentUser,
  }) => {
    const router = useNavigate();
    const { getByValue } = useCountries();
    const currentDate = new Date();
    const location = getByValue(data.location.value);
    const dispatch = useAppDispatch();
    const handleCancel = useCallback(
      (e: React.MouseEvent<HTMLButtonElement>) => {
      e.stopPropagation();
  
      if (disabled) {
        return;
      }
  
      onAction?.(actionId)
    }, [disabled, onAction, actionId]);
    
    const handleReview = useCallback((e: React.MouseEvent<HTMLButtonElement>) => {
      e.stopPropagation();
      dispatch(openReviewModal(data.id));
      
    },[]);

    const price = useMemo(() => {
      if (reservation) {
        return reservation.totalPrice;
      }
  
      return data.price;
    }, [reservation, data.price]);
  
    const reservationDate = useMemo(() => {
      if (!reservation) {
        return null;
      }
    
      const start = new Date(reservation.startDate);
      const end = new Date(reservation.endDate);
  
      return `${format(start, 'PP')} - ${format(end, 'PP')}`;
    }, [reservation]);
  
    return (
      <div 
        onClick={() => router(`/listings/${data.id}`)} 
        className="col-span-1 cursor-pointer group"
      >
        <div className="flex flex-col gap-2 w-full">
          <div 
            className="
              aspect-square 
              w-full 
              relative 
              overflow-hidden 
              rounded-xl
            "
          >
            <img
             // fill
              className="
                object-cover 
                h-full 
                w-full 
                group-hover:scale-110 
                transition
              "
              src={`data:image/jpeg;base64,${data.imageByte[0]}`}
              alt="Listing"
            />
            <div className="
              absolute
              top-3
              right-3
            ">
              <HeartButton 
                listingId={data.id} 
                currentUser={currentUser}
              />
            </div>
          </div>
          <div className="font-semibold text-lg flex flex-row justify-between">
            <div>
              {location?.region}, {location?.label}
            </div>
            <div className="flex flex-row justify-center align-middle items-center">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 30 30" fill="black" stroke="black" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" className="w-6 h-6">
                <polygon points="12 2 15.09 8.45 22 9.82 17 14.14 18.18 21 12 17.32 5.82 21 7 14.14 2 9.82 8.91 8.45 12 2" />
              </svg>
              {data.average}
            </div>
          </div>
          <div className="font-light text-neutral-500">
            {reservationDate || data.category}
          </div>
          <div className="flex flex-row items-center gap-1">
            <div className="font-semibold">
              $ {price}
            </div>
            {!reservation && (
              <div className="font-light">night</div>
            )}
          </div>
          {onAction && actionLabel && reservation && new Date(reservation.endDate) > currentDate && (
            <Button
            disabled={disabled}
            small
            label={actionLabel} 
            onClick={handleCancel}
          />
          )}
          {reservation && new Date(reservation.endDate) < currentDate && (
            <Button
            disabled={disabled}
            small
            label= 'Write review'
            onClick={handleReview}
          />
          )}
        </div>
      </div>
     );
  }
   
  export default ListingCard;