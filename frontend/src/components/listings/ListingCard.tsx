import { format } from "date-fns";
import Button from "../Button";
import { useCallback, useMemo } from "react";
import useCountries from "src/hooks/useCountries";
import { ResponseUser } from "src/interface/auth";
import { useNavigate } from "react-router-dom";
import HeartButton from "../HeartButton";
import { Listing, ResponseListing } from "src/interface/listing";
import { Reservation } from "src/interface/reservation";

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
  
    const location = getByValue(data.location.value);
  
    const handleCancel = useCallback(
      (e: React.MouseEvent<HTMLButtonElement>) => {
      e.stopPropagation();
  
      if (disabled) {
        return;
      }
  
      onAction?.(actionId)
    }, [disabled, onAction, actionId]);
  
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
          <div className="font-semibold text-lg">
            {location?.region}, {location?.label}
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
          {onAction && actionLabel && (
            <Button
              disabled={disabled}
              small
              label={actionLabel} 
              onClick={handleCancel}
            />
          )}
        </div>
      </div>
     );
  }
   
  export default ListingCard;