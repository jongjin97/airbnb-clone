import LoginModal from "src/components/modals/LoginModal";
import RegisterModal from "../components/modals/RegisterModal";
import SearchModal from "src/components/modals/SearchModal";

const ModalsProvider = () => {
  return ( 
    <>
      <RegisterModal />
      <LoginModal />
      <SearchModal />
    </>
   );
}
 
export default ModalsProvider;