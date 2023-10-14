import LoginModal from "src/components/modals/LoginModal";
import RegisterModal from "../components/modals/RegisterModal";

const ModalsProvider = () => {
  return ( 
    <>
      <RegisterModal />
      <LoginModal />
    </>
   );
}
 
export default ModalsProvider;