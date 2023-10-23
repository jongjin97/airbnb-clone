import toast from "react-hot-toast";
import Container from "../Container";
import Heading from "../Heading";
import ListingCard from "../listings/ListingCard";
import { useNavigate } from "react-router-dom";
import { useCallback, useState } from "react";
import { Listing } from "src/interface/listing";
import { ResponseUser } from "src/interface/auth";
import { deleteListingsById } from "src/api/listing.api";

interface PropertiesClientProps {
    listings: Listing[],
    currentUser?: ResponseUser | null,
  }
  
  const PropertiesClient: React.FC<PropertiesClientProps> = ({
    listings,
    currentUser
  }) => {
    const router = useNavigate();
    const [deletingId, setDeletingId] = useState('');
  
    const onDelete = useCallback((id: string) => {
      setDeletingId(id);
  
      deleteListingsById(id)
      .then(() => {
        toast.success('Listing deleted');
        router(0);
      })
      .catch((error) => {
        toast.error(error?.response?.data?.error)
      })
      .finally(() => {
        setDeletingId('');
      })
    }, [router]);
  
  
    return ( 
      <Container>
        <Heading
          title="Properties"
          subtitle="List of your properties"
        />
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
          {listings.map((listing: any) => (
            <ListingCard
              key={listing.id}
              data={listing}
              actionId={listing.id}
              onAction={onDelete}
              disabled={deletingId === listing.id}
              actionLabel="Delete property"
              currentUser={currentUser}
            />
          ))}
        </div>
      </Container>
     );
  }
   
  export default PropertiesClient;