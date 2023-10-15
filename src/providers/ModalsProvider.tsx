import LoginModal from "src/components/modals/LoginModal";
import RegisterModal from "../components/modals/RegisterModal";
import SearchModal from "src/components/modals/SearchModal";
import RentModal from "src/components/modals/RentModal";

const ModalsProvider = () => {
  return ( 
    <>
      <RegisterModal />
      <LoginModal />
      <SearchModal />
      <RentModal />
    </>
   );
}
 
export default ModalsProvider;