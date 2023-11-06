import LoginModal from 'src/components/modals/LoginModal';
import SearchModal from 'src/components/modals/SearchModal';
import RentModal from 'src/components/modals/RentModal';
import MessageModal from 'src/components/modals/MessageModal';
import ReviewModal from 'src/components/modals/ReviewModal';
import RegisterModal from '../components/modals/RegisterModal';

function ModalsProvider() {
  return (
    <>
      <RegisterModal />
      <LoginModal />
      <SearchModal />
      <RentModal />
      <MessageModal />
      <ReviewModal />
    </>
  );
}

export default ModalsProvider;
