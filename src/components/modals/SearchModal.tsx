import { useCallback, useEffect, useMemo, useState } from 'react';
import { Range } from 'react-date-range';
import Heading from '../Heading';
import Calendar from '../inputs/Calendar';
import Counter from '../inputs/Counter';
import Modal from './Modal';
import CountrySelect, { CountrySelectValue } from '../inputs/CountrySelect';
import { useAppDispatch, useAppSelector } from 'src/app/hooks';
import { closeSearchModal } from 'src/features/modal/SearchModalAction';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { MapProps } from '../Map';
import Map from '../Map';

enum STEPS {
    LOCATION = 0,
    DATE = 1,
    INFO = 2,
  }
  
  const SearchModal = () => {
    const router = useNavigate();
    const searchModal = useAppSelector((state) => state.search.isOpen);
    const params = useSearchParams();
    const dispatch = useAppDispatch();
    const [step, setStep] = useState(STEPS.LOCATION);
    const [location, setLocation] = useState<CountrySelectValue>();
    const [guestCount, setGuestCount] = useState(1);
    const [roomCount, setRoomCount] = useState(1);
    const [bathroomCount, setBathroomCount] = useState(1);
    const [dateRange, setDateRange] = useState<Range>({
      startDate: new Date(),
      endDate: new Date(),
      key: 'selection'
    });
  
    // const Map = useMemo(() => dynamic(() => import('../Map'), { 
    //   ssr: false 
    // }), [location]);

    // useEffect(() => {
    //     const importMap = async () => {
    //       await import('../Map');
    //       setIsMapLoaded(true);
    //     };
    
    //     importMap();
    //   }, []);

    const onBack = useCallback(() => {
      setStep((value) => value - 1);
    }, []);
  
    const onNext = useCallback(() => {
      setStep((value) => value + 1);
    }, []);
  
    const onSubmit = useCallback(async () => {
      if (step !== STEPS.INFO) {
        return onNext();
      }
  
    //   let currentQuery = {};
  
    //   if (params) {
    //     currentQuery = qs.parse(params.toString())
    //   }
  
    //   const updatedQuery: any = {
    //     ...currentQuery,
    //     locationValue: location?.value,
    //     guestCount,
    //     roomCount,
    //     bathroomCount
    //   };
  
    //   if (dateRange.startDate) {
    //     updatedQuery.startDate = formatISO(dateRange.startDate);
    //   }
  
    //   if (dateRange.endDate) {
    //     updatedQuery.endDate = formatISO(dateRange.endDate);
    //   }
  
    //   const url = qs.stringifyUrl({
    //     url: '/',
    //     query: updatedQuery,
    //   }, { skipNull: true });
  
      setStep(STEPS.LOCATION);
      dispatch(closeSearchModal());
      //router.push(url);
    }, 
    [
      step, 
      searchModal, 
      location, 
      router, 
      guestCount, 
      roomCount,
      dateRange,
      onNext,
      bathroomCount,
      params
    ]);
    const handleCloseModal = useCallback(() => {
        dispatch(closeSearchModal());
      }, []);
    const actionLabel = useMemo(() => {
      if (step === STEPS.INFO) {
        return 'Search'
      }
  
      return 'Next'
    }, [step]);
  
    const secondaryActionLabel = useMemo(() => {
      if (step === STEPS.LOCATION) {
        return undefined
      }
  
      return 'Back'
    }, [step]);
  
    let bodyContent = (
      <div className="flex flex-col gap-8">
        <Heading
          title="Where do you wanna go?"
          subtitle="Find the perfect location!"
        />
        <CountrySelect 
          value={location} 
          onChange={(value) => 
            setLocation(value as CountrySelectValue)} 
        />
        <hr />
        <Map center={location?.latlng } />
        {/* {isMapLoaded && <Map center={location?.latlng} />} */}
      </div>
    )
  
    if (step === STEPS.DATE) {
      bodyContent = (
        <div className="flex flex-col gap-8">
          <Heading
            title="When do you plan to go?"
            subtitle="Make sure everyone is free!"
          />
          <Calendar
            onChange={(value) => setDateRange(value.selection)}
            value={dateRange}
          />
        </div>
      )
    }
  
    if (step === STEPS.INFO) {
      bodyContent = (
        <div className="flex flex-col gap-8">
          <Heading
            title="More information"
            subtitle="Find your perfect place!"
          />
          <Counter 
            onChange={(value) => setGuestCount(value)}
            value={guestCount}
            title="Guests" 
            subtitle="How many guests are coming?"
          />
          <hr />
          <Counter 
            onChange={(value) => setRoomCount(value)}
            value={roomCount}
            title="Rooms" 
            subtitle="How many rooms do you need?"
          />        
          <hr />
          <Counter 
            onChange={(value) => {
              setBathroomCount(value)
            }}
            value={bathroomCount}
            title="Bathrooms"
            subtitle="How many bahtrooms do you need?"
          />
        </div>
      )
    }
  
    return (
      <Modal
        isOpen={searchModal}
        title="Filters"
        actionLabel={actionLabel}
        onSubmit={onSubmit}
        secondaryActionLabel={secondaryActionLabel}
        secondaryAction={step === STEPS.LOCATION ? undefined : onBack}
        onClose={handleCloseModal}
        body={bodyContent}
      />
    );
  }
  
  export default SearchModal;