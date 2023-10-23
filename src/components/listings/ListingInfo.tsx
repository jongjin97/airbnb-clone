import useCountries from "src/hooks/useCountries";
import Avatar from "../Avatar";
import { ResponseUser } from "src/interface/auth";
import { IconType } from "react-icons";
import ListingCategory from "./ListingCategory";
import { MouseEvent, Suspense, lazy } from "react";
import { facilities } from "src/interface/facility";
import Button from "../Button";
const Map = lazy(() => import('../Map'));

interface ListingInfoProps {
    user: ResponseUser,
    description: string;
    guestCount: number;
    roomCount: number;
    bathroomCount: number;
    category: {
      icon: IconType,
      label: string;
      description: string;
    } | undefined
    locationValue: string;
    facility: ({
      label: string;
      icon: IconType;
      description: string;
  } | undefined)[];
      openMessage: (event: MouseEvent<HTMLButtonElement>) => void;
  }
  
  const ListingInfo: React.FC<ListingInfoProps> = ({
    user,
    description,
    guestCount,
    roomCount,
    bathroomCount,
    category,
    locationValue,
    facility,
    openMessage,
  }) => {
    const { getByValue } = useCountries();
  
    const coordinates = getByValue(locationValue)?.latlng
  
    return ( 
      <div className="col-span-4 flex flex-col gap-8">
        <div className="flex flex-col gap-2">
          <div 
            className="
              text-xl 
              font-semibold 
              flex 
              flex-row 
              items-center
              gap-2
            "
          >
            <div>Hosted by {user?.name}</div>
            <Avatar src={null} />
            <button className="
              bg-slate-600
              rounded-xl
              w-24
              flex
              justify-center
              items-center
              text-white
              hover:bg-gray-700
            " onClick={openMessage}>Message</button>
          </div>
          <div className="
              flex 
              flex-row 
              items-center 
              gap-4 
              font-light
              text-neutral-500
            "
          >
            <div>
              {guestCount} guests
            </div>
            <div>
              {roomCount} rooms
            </div>
            <div>
              {bathroomCount} bathrooms
            </div>
          </div>
        </div>
        <hr />
        {category && (
          <ListingCategory
            icon={category.icon} 
            label={category?.label}
            description={category?.description} 
          />
        )}
        <hr />
        <div className="
        text-lg font-light text-neutral-500">
          {description}
        </div>
        <hr />
        <div >
          <div className="
          text-xl 
          font-semibold 
          flex 
          flex-row 
          items-center
          gap-2">Facilities</div>
          {facility && facility.map((item) => (
            item && (
              <ListingCategory 
                icon={item.icon}
                label={item.label}
                description={item.description}
              />
            )
          ))}
        </div>
        <hr />
        <Suspense>
          <Map center={coordinates} />
        </Suspense>
      </div>
     );
  }
   
  export default ListingInfo;