import useCountries from "src/hooks/useCountries";
import { ResponseUser } from "src/interface/auth";
import Heading from "../Heading";
import HeartButton from "../HeartButton";
import { CountrySelectValue } from "../inputs/CountrySelect";

interface ListingHeadProps {
    title: string;
    locationValue: CountrySelectValue;
    imageSrc: string;
    id: string;
    currentUser?: ResponseUser | null
  }
  
  const ListingHead: React.FC<ListingHeadProps> = ({
    title,
    locationValue,
    imageSrc,
    id,
    currentUser
  }) => {
    const { getByValue } = useCountries();
  
  
    return ( 
      <>
        <Heading
          title={title}
          subtitle={`${locationValue?.region}, ${locationValue?.label}`}
        />
        <div className="
            w-full
            h-[60vh]
            overflow-hidden 
            rounded-xl
            relative
          "
        >
          <img
            src={imageSrc}
            //fill
            className="object-cover w-full"
            alt="Image"
          />
          <div
            className="
              absolute
              top-5
              right-5
            "
          >
            <HeartButton 
              listingId={id}
              currentUser={currentUser}
            />
          </div>
        </div>
      </>
     );
  }
   
  export default ListingHead;