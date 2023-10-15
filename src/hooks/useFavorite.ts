import { useCallback, useMemo } from "react";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { ResponseUser } from "src/interface/auth";
import { openLoginModal } from "src/features/modal/LoginModalAction";
import { useAppDispatch, useAppSelector } from "src/app/hooks";

interface IUseFavorite {
    listingId: number;
    currentUser?: ResponseUser | null
  }
  
  const useFavorite = ({ listingId, currentUser }: IUseFavorite) => {
    const router = useNavigate();
    const dispatch = useAppDispatch();
    const loginModal = useAppSelector((state) => state.login);
    const hasFavorited = useMemo(() => {
      const list = currentUser?.favoriteIds || [];
  
      return list.includes(listingId);
    }, [currentUser, listingId]);
  
    const toggleFavorite = useCallback(async (e: React.MouseEvent<HTMLDivElement>) => {
      e.stopPropagation();
  
      if (!currentUser) {
        return dispatch(openLoginModal());
      }
  
      try {
        let request;
  
        if (hasFavorited) {
          request = () => null;
        } else {
          request = () => null;
        }
  
        await request();
        router(0);
        toast.success('Success');
      } catch (error) {
        toast.error('Something went wrong.');
      }
    }, 
    [
      currentUser, 
      hasFavorited, 
      listingId, 
      loginModal,
      router
    ]);
  
    return {
      hasFavorited,
      toggleFavorite,
    }
  }
  
  export default useFavorite;