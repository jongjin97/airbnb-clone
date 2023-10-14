import { useNavigate } from "react-router-dom";
import MenuItem from "./MenuItem";
import { Avatar } from "antd";
import { useCallback, useState } from "react";
import { AiOutlineMenu } from "react-icons/ai";
import { useAppSelector } from "src/app/hooks";

const UserMenu = () => {
    const router = useNavigate();
    const user = useAppSelector((state) => state.auth.user);
    const [isOpen, setIsOpen] = useState(false);
    const toggleOpen = useCallback(() => {
      setIsOpen((value) => !value);
    }, []);
  
    return ( 
      <div className="relative">
        <div className="flex flex-row items-center gap-3">
          <div 
           // onClick={onRent}
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
              <Avatar src={user?.img} />
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
                    label="My trips" 
                    onClick={() => router('/trips')}
                  />
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
                  <MenuItem 
                    label="Airbnb your home" 
                    onClick={() => (null)}
                  />
                  <hr />
                  <MenuItem 
                    label="Logout" 
                    onClick={() => (null)}
                  />
                </>
              ) : (
                <>
                  <MenuItem 
                    label="Login" 
                    onClick={() => (null)}
                  />
                  <MenuItem 
                    label="Sign up" 
                    onClick={() => (null)}
                  />
                </>
              )}
            </div>
          </div>
        )}
      </div>
     );
  }
   
  export default UserMenu;