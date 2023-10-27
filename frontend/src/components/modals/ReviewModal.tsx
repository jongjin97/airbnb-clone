import { useState, useCallback } from "react";
import { useForm, FieldValues, SubmitHandler } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { useAppSelector, useAppDispatch } from "src/app/hooks";
import { closeMessageModal } from "src/features/modal/MessageModalAction";
import Modal from "./Modal";

const ReviewModal = () => {
    const router = useNavigate();
    const reviewModal = useAppSelector((state) => state.review.isOpen)
    const dispatch = useAppDispatch();
    const [isLoading, setIsLoading] = useState(false);
    const { 
        handleSubmit,
        formState: {
          errors,
        },
      } = useForm<FieldValues>({
        defaultValues: {
          email: '',
          password: ''
        },
      });
    
    const onSubmit: SubmitHandler<FieldValues> = 
    (data) => {
      setIsLoading(true);
      //dispatch(doLogin(data));
      setIsLoading(false);
    }
    const handleCloseModal = useCallback(() => {
        dispatch(closeMessageModal());
        setIsLoading(false);
      }, []);
    
  
    const onToggle = useCallback(() => {
      dispatch(closeMessageModal());
    }, [reviewModal])
  
    const bodyContent = (
      <div className="flex flex-col gap-4">
        <section className="py-8 pb-16 mt-20">
                <div className="text-center">
                    <h2 className="text-xl font-bold">Carmela님에게 연락하기</h2>
                </div>
                <div className="flex items-center justify-center mt-8">
                    <img
                        src="https://a0.muscache.com/im/pictures/user/acd97bc9-42f7-45cf-84d4-076add17e41a.jpg?im_w=240"
                        alt="호스트 Carmela님에 대해 자세히 알아보세요."
                        className="w-16 h-16 rounded-full"
                    />
                </div>
            </section>
            <div className="
                w-3/4
                mx-auto
            ">
                <textarea className="w-full h-32 p-4 border border-gray-300 rounded-md"></textarea>
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