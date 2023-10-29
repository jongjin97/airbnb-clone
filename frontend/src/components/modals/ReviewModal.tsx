import { useState, useCallback } from "react";
import { useForm, FieldValues, SubmitHandler, RegisterOptions, UseFormRegisterReturn } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { useAppSelector, useAppDispatch } from "src/app/hooks";
import { closeMessageModal } from "src/features/modal/MessageModalAction";
import Modal from "./Modal";
import { closeReviewModal } from "src/features/modal/ReviewModalAction";
import { Rating } from "react-simple-star-rating";
import ColModal from "./ColModal";
import StarRating from "../StarRating";
import { saveReview } from "src/api/reiew.api";
import toast from "react-hot-toast";

const ReviewModal = () => {
    const router = useNavigate();
    const reviewModal = useAppSelector((state) => state.review.isOpen);
    const listingId = useAppSelector((state) => state.review.listingId);
    const dispatch = useAppDispatch();
    const [isLoading, setIsLoading] = useState(false);

    const {
      register,
      handleSubmit,
      formState: { errors },
      watch,
    } = useForm<FieldValues>({
      defaultValues: {
        rating: 0,
        message: '',
      },
    });
    const onSubmit: SubmitHandler<FieldValues> = 
    (RequestReview) => {
      setIsLoading(true);
      
      saveReview(listingId, RequestReview).then(() => {
        dispatch(closeReviewModal());
        router(`/listings/${listingId}`);
      }).catch(() =>{
        toast.error("리뷰 저장에 실패했습니다.");
      });
      //dispatch(doLogin(data));
      setIsLoading(false);
    }
    const handleCloseModal = useCallback(() => {
        dispatch(closeReviewModal());
        setIsLoading(false);
      }, []);
    
  
    const onToggle = useCallback(() => {
      dispatch(closeReviewModal());
    }, [reviewModal])
  
    const bodyContent = (
          <div className="
            flex flex-col gap-4
          ">
            <div className="flex flex-auto gap-4">
              <span className="text-lg
              font-semibold">별점</span>
              <StarRating name="rating" register={register}/>
            </div>
            <div className="w-full h-48">
              <textarea className="w-full rounded outline-red-100 outline h-full resize-none"
                {...register('message')}
              ></textarea>
            </div>
          </div>
    )
  
    const footerContent = (
      <div className="flex flex-col gap-4 mt-3">
        
      </div>
    )
  
    return (
      <Modal
        disabled={isLoading}
        isOpen={reviewModal}
        title="Review Write"
        actionLabel="Write"
        onClose={handleCloseModal}
        onSubmit={handleSubmit(onSubmit)}
        body={bodyContent}
        footer={footerContent}
      />
    );
  }
  
  export default ReviewModal;