import { Suspense, lazy, useCallback, useEffect, useMemo, useState } from 'react';
import Heading from '../Heading';
import Counter from '../inputs/Counter';
import CountrySelect from '../inputs/CountrySelect';
import Input from '../inputs/Input';
import Modal from './Modal';
import { FieldValues, SubmitHandler, useForm } from 'react-hook-form';
import { useAppDispatch, useAppSelector } from 'src/app/hooks';
import { useNavigate } from 'react-router-dom';
import CategoryInput from '../inputs/CategoryInput';
import { categories } from '../navbar/Categories';
import ImageUpload from '../inputs/ImageUpload';
import { closeRentModal } from 'src/features/modal/RentModalAction';
import { facilities } from '../Facilitie';
import FacilitiInput from '../inputs/FacilitieInput';
import { createRent } from 'src/api/rent.api';
import toast from 'react-hot-toast';
const Map = lazy(() => import('../Map'));
enum STEPS {
    CATEGORY = 0,
    FACILITIES = 1,
    LOCATION = 2,
    INFO = 3,
    IMAGES = 4,
    DESCRIPTION = 5,
    PRICE = 6,
  }
  
  const RentModal = () => {
    const router = useNavigate();
    const rentModal = useAppSelector((state) => state.rent.isOpen);
    const dispatch = useAppDispatch();
    const [isLoading, setIsLoading] = useState(false);
    const [step, setStep] = useState(STEPS.CATEGORY);
    const [mapKey, setMapKey] = useState(0);
    const { 
      register, 
      handleSubmit,
      setValue,
      watch,
      formState: {
        errors,
      },
      reset,
    } = useForm<FieldValues>({
      defaultValues: {
        category: '',
        facility: [],
        location: null,
        guestCount: 1,
        roomCount: 1,
        bathroomCount: 1,
        imageSrc: [],
        price: 1,
        title: '',
        description: '',
      }
    });
  
    const location = watch('location');
    const category = watch('category');
    const facility = watch('facility');
    const guestCount = watch('guestCount');
    const roomCount = watch('roomCount');
    const bathroomCount = watch('bathroomCount');
    const imageSrc = watch('imageSrc');
      useEffect(() => {
        // location 설정 로직
        // location이 변경될 때마다 mapKey 값을 업데이트하여 Map 컴포넌트를 새로 로딩하도록 함
        setMapKey((prevKey) => prevKey + 1);
      }, [location])
    const setCustomValue = (id: string, value: any) => {
      setValue(id, value, {
        shouldDirty: true,
        shouldTouch: true,
        shouldValidate: true
      })
    }
    const setArrayValue = (id: string, value: any) => {
      setValue(id, [...watch(id), value], {
        shouldDirty: true,
        shouldTouch: true,
        shouldValidate: true
      })
    }
    const onBack = () => {
      setStep((value) => value - 1);
    }
  
    const onNext = () => {
      setStep((value) => value + 1);
    }
    const handleCloseModal = useCallback(() => {
        dispatch(closeRentModal());
      }, []);
    const onSubmit: SubmitHandler<FieldValues> = (data) => {
      console.log(data);
      if (step !== STEPS.PRICE) {
        return onNext();
      }
      console.log(data);
      setIsLoading(true);
      createRent(data).then(() => {
        toast.success('Listing created!');
        router(0);
        reset();
        setStep(STEPS.CATEGORY)
        dispatch(closeRentModal());
      }).finally(() => {
        setIsLoading(false);
      });
    //   axios.post('/api/listings', data)
    //   .then(() => {
    //     toast.success('Listing created!');
    //     router.refresh();
    //     reset();
    //     setStep(STEPS.CATEGORY)
    //     rentModal.onClose();
    //   })
    //   .catch(() => {
    //     toast.error('Something went wrong.');
    //   })
    //   .finally(() => {
    //     setIsLoading(false);
    //   })
    }
  
    const actionLabel = useMemo(() => {
      if (step === STEPS.PRICE) {
        return 'Create'
      }
  
      return 'Next'
    }, [step]);
  
    const secondaryActionLabel = useMemo(() => {
      if (step === STEPS.CATEGORY) {
        return undefined
      }
  
      return 'Back'
    }, [step]);
  
    let bodyContent = (
      <div className="flex flex-col gap-8">
        <Heading
          title="Which of these best describes your place?"
          subtitle="Pick a category"
        />
        <div 
          className="
            grid 
            grid-cols-1 
            md:grid-cols-2 
            gap-3
            max-h-[50vh]
            overflow-y-auto
          "
        >
          {categories.map((item) => (
            <div key={item.label} className="col-span-1">
              <CategoryInput
                onClick={(category) => 
                  setCustomValue('category', category)}
                selected={category === item.label}
                label={item.label}
                icon={item.icon}
              />
            </div>
          ))}
        </div>
      </div>
    )
    if (step === STEPS.FACILITIES) {
      bodyContent = (
        <div className="flex flex-col gap-8">
          <Heading
            title="Which of these best describes your place?"
            subtitle="Pick a facility"
          />
          <div 
            className="
              grid 
              grid-cols-1 
              md:grid-cols-2 
              gap-3
              max-h-[50vh]
              overflow-y-auto
            "
          >
            {facilities.map((item) => (
              <div key={item.label} className="col-span-1">
                <FacilitiInput
                  onClick={(facility) => 
                  setArrayValue('facility', facility)}
                  //selected={facility === item.label}
                  selected={facility.includes(item.label)}
                  label={item.label}
                  icon={item.icon}
                />
              </div>
            ))}
          </div>
        </div>
      );
    }
    if (step === STEPS.LOCATION) {
      bodyContent = (
        <div className="flex flex-col gap-8">
          <Heading
            title="Where is your place located?"
            subtitle="Help guests find you!"
          />
          <CountrySelect 
            value={location} 
            onChange={(value) => setCustomValue('location', value)} 
          />
          <Suspense>
            <Map key = {mapKey} center={location?.latlng}/>
          </Suspense>
        </div>
      );
    }
  
    if (step === STEPS.INFO) {
      bodyContent = (
        <div className="flex flex-col gap-8">
          <Heading
            title="Share some basics about your place"
            subtitle="What amenitis do you have?"
          />
          <Counter 
            onChange={(value) => setCustomValue('guestCount', value)}
            value={guestCount}
            title="Guests" 
            subtitle="How many guests do you allow?"
          />
          <hr />
          <Counter 
            onChange={(value) => setCustomValue('roomCount', value)}
            value={roomCount}
            title="Rooms" 
            subtitle="How many rooms do you have?"
          />
          <hr />
          <Counter 
            onChange={(value) => setCustomValue('bathroomCount', value)}
            value={bathroomCount}
            title="Bathrooms" 
            subtitle="How many bathrooms do you have?"
          />
        </div>
      )
    }
  
    if (step === STEPS.IMAGES) {
      bodyContent = (
        <div className="flex flex-col gap-8">
          <Heading
            title="Add a photo of your place"
            subtitle="Show guests what your place looks like!"
          />
          <ImageUpload
            onChange={(value) => setArrayValue('imageSrc', value)}
            value={imageSrc}
          />
        </div>
      )
    }
  
    if (step === STEPS.DESCRIPTION) {
      bodyContent = (
        <div className="flex flex-col gap-8">
          <Heading
            title="How would you describe your place?"
            subtitle="Short and sweet works best!"
          />
          <Input
            id="title"
            label="Title"
            disabled={isLoading}
            register={register}
            errors={errors}
            required
          />
          <hr />
          <Input
            id="description"
            label="Description"
            disabled={isLoading}
            register={register}
            errors={errors}
            required
          />
        </div>
      )
    }
  
    if (step === STEPS.PRICE) {
      bodyContent = (
        <div className="flex flex-col gap-8">
          <Heading
            title="Now, set your price"
            subtitle="How much do you charge per night?"
          />
          <Input
            id="price"
            label="Price"
            formatPrice 
            type="number" 
            disabled={isLoading}
            register={register}
            errors={errors}
            required
          />
        </div>
      )
    }
  
    return (
      <Modal
        disabled={isLoading}
        isOpen={rentModal}
        title="Airbnb your home!"
        actionLabel={actionLabel}
        onSubmit={handleSubmit(onSubmit)}
        secondaryActionLabel={secondaryActionLabel}
        secondaryAction={step === STEPS.CATEGORY ? undefined : onBack}
        onClose={handleCloseModal}
        body={bodyContent}
      />
    );
  }
  
  export default RentModal;