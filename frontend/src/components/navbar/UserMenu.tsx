import { useNavigate } from 'react-router-dom';
import MenuItem from './MenuItem';
import { useCallback, useState } from 'react';
import { AiOutlineMenu } from 'react-icons/ai';
import { useAppDispatch, useAppSelector } from 'src/app/hooks';
import Avatar from '../Avatar';
import { openRegisterModal } from 'src/features/modal/RegisterModalAction';
import { openLoginModal } from 'src/features/modal/LoginModalAction';
import { openRentModal } from 'src/features/modal/RentModalAction';
import { doLogOut } from 'src/features/auth/authAction';

const UserMenu = () => {
  const router = useNavigate();
  const dispatch = useAppDispatch();
  const user = useAppSelector((state) => state.auth.user);
  const [isOpen, setIsOpen] = useState(false);
  const toggleOpen = useCallback(() => {
    setIsOpen((value) => !value);
  }, []);
  const handleRegisterModal = useCallback(() => {
    dispatch(openRegisterModal());
  }, []);
  const handleLoginModal = useCallback(() => {
    dispatch(openLoginModal());
  }, []);
  const handleRentModal = useCallback(() => {
    dispatch(openRentModal());
  }, []);
  const handleLogout = useCallback(() => {
    dispatch(doLogOut());
  }, []);
  return (
    <div className="relative">
      <div className="flex flex-row items-center gap-3">
        <div
          onClick={handleRentModal}
          className="
              hidden
              md:block
              text-sm 
              font-semibold 
              py-3 
              px-4 
              rounded-full 
              hover:bg-neutral-100 
              transition 
              cursor-pointer
            "
        >
          Airbnb your home
        </div>
        <div
          onClick={toggleOpen}
          className="
            p-4
            md:py-1
            md:px-2
            border-[1px] 
            border-neutral-200 
            flex 
            flex-row 
            items-center 
            gap-3 
            rounded-full 
            cursor-pointer 
            hover:shadow-md 
            transition
            "
        >
          <AiOutlineMenu />
          <div className="hidden md:block">
            <Avatar src={null} />
          </div>
        </div>
      </div>
      {isOpen && (
        <div
          className="
              absolute 
              rounded-xl 
              shadow-md
              w-[40vw]
              md:w-3/4 
              bg-white 
              overflow-hidden 
              right-0 
              top-12 
              text-sm
            "
        >
          <div className="flex flex-col cursor-pointer">
            {user ? (
              <>
                <MenuItem
                  label="My Messages"
                  onClick={() => router('/messages')}
                />
                <MenuItem label="My trips" onClick={() => router('/trips')} />
                <MenuItem
                  label="My favorites"
                  onClick={() => router('/favorites')}
                />
                <MenuItem
                  label="My reservations"
                  onClick={() => router('/reservations')}
                />
                <MenuItem
                  label="My properties"
                  onClick={() => router('/properties')}
                />
                <MenuItem label="Airbnb your home" onClick={() => null} />
                <hr />
                <MenuItem label="Logout" onClick={handleLogout} />
              </>
            ) : (
              <>
                <MenuItem label="Login" onClick={handleLoginModal} />
                <MenuItem label="Sign up" onClick={handleRegisterModal} />
              </>
            )}
          </div>
        </div>
      )}
    </div>
  );
};

export default UserMenu;
