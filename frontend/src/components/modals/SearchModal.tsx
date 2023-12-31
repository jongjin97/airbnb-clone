import {
  Suspense,
  lazy,
  useCallback,
  useEffect,
  useMemo,
  useState,
} from 'react';
import { Range } from 'react-date-range';
import Heading from '../Heading';
import Calendar from '../inputs/Calendar';
import Counter from '../inputs/Counter';
import Modal from './Modal';
import CountrySelect, { CountrySelectValue } from '../inputs/CountrySelect';
import { useAppDispatch, useAppSelector } from 'src/app/hooks';
import { closeSearchModal } from 'src/features/modal/SearchModalAction';
import { useNavigate, useSearchParams } from 'react-router-dom';
import qs from 'query-string';
import { formatISO } from 'date-fns';
const Map = lazy(() => import('../Map'));
enum STEPS {
  LOCATION = 0,
  DATE = 1,
  INFO = 2,
}

const SearchModal = () => {
  const router = useNavigate();
  const searchModal = useAppSelector((state) => state.search.isOpen);
  const [URLSearchParams, SetURLSearchParams] = useSearchParams();
  const dispatch = useAppDispatch();
  const [step, setStep] = useState(STEPS.LOCATION);
  const [location, setLocation] = useState<CountrySelectValue>();
  const [guestCount, setGuestCount] = useState(1);
  const [roomCount, setRoomCount] = useState(1);
  const [bathroomCount, setBathroomCount] = useState(1);
  const [mapKey, setMapKey] = useState(0);
  const [dateRange, setDateRange] = useState<Range>({
    startDate: new Date(),
    endDate: new Date(),
    key: 'selection',
  });

  useEffect(() => {
    // location 설정 로직
    // location이 변경될 때마다 mapKey 값을 업데이트하여 Map 컴포넌트를 새로 로딩하도록 함
    setMapKey((prevKey) => prevKey + 1);
  }, [location]);

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

    let currentQuery = {};

    if (URLSearchParams) {
      currentQuery = qs.parse(URLSearchParams.toString());
    }

    const updatedQuery: any = {
      ...currentQuery,
      locationValue: location?.value,
      guestCount,
      roomCount,
      bathroomCount,
    };

    if (dateRange.startDate) {
      updatedQuery.startDate = formatISO(dateRange.startDate);
    }

    if (dateRange.endDate) {
      updatedQuery.endDate = formatISO(dateRange.endDate);
    }

    const url = qs.stringifyUrl(
      {
        url: '/',
        query: updatedQuery,
      },
      { skipNull: true }
    );
    console.log(updatedQuery);
    setStep(STEPS.LOCATION);
    dispatch(closeSearchModal());
    router(url);
  }, [
    step,
    searchModal,
    location,
    router,
    guestCount,
    roomCount,
    dateRange,
    onNext,
    bathroomCount,
    URLSearchParams,
  ]);
  const handleCloseModal = useCallback(() => {
    dispatch(closeSearchModal());
  }, []);
  const actionLabel = useMemo(() => {
    if (step === STEPS.INFO) {
      return 'Search';
    }

    return 'Next';
  }, [step]);

  const secondaryActionLabel = useMemo(() => {
    if (step === STEPS.LOCATION) {
      return undefined;
    }

    return 'Back';
  }, [step]);

  let bodyContent = (
    <div className="flex flex-col gap-8">
      <Heading
        title="Where do you wanna go?"
        subtitle="Find the perfect location!"
      />
      <CountrySelect
        value={location}
        onChange={(value) => setLocation(value as CountrySelectValue)}
      />
      <hr />
      <Suspense>
        <Map key={mapKey} center={location?.latlng} />
      </Suspense>
      {/* {isMapLoaded && <Map center={location?.latlng} />} */}
    </div>
  );

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
    );
  }

  if (step === STEPS.INFO) {
    bodyContent = (
      <div className="flex flex-col gap-8">
        <Heading title="More information" subtitle="Find your perfect place!" />
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
            setBathroomCount(value);
          }}
          value={bathroomCount}
          title="Bathrooms"
          subtitle="How many bahtrooms do you need?"
        />
      </div>
    );
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
};

export default SearchModal;
