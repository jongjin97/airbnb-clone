import { FcGoogle } from "react-icons/fc";
import Button from "../Button";
import Modal from "./Modal";
import { AiFillGithub } from "react-icons/ai";
import Heading from "../Heading";
import Input from "../inputs/Input";
import { useCallback, useState } from "react";
import { FieldValues, SubmitHandler, useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "src/app/hooks";
import toast from "react-hot-toast";
import { closeLoginModal } from "src/features/modal/LoginModalAction";
import { openRegisterModal } from "src/features/modal/RegisterModalAction";
import { login } from "src/api/auth.api";
import { doLogin } from "src/features/auth/authAction";
import { closeMessageModal } from "src/features/modal/MessageModalAction";

const MessageModal = () => {
    const router = useNavigate();
    const messageModal = useAppSelector((state) => state.message.isOpen)
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
      }, []);
    
  
    const onToggle = useCallback(() => {
      dispatch(closeMessageModal());
    }, [messageModal])
  
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
        <hr />
        <Button 
          outline 
          label="Continue with Google"
          icon={FcGoogle}
          onClick={() => null}
        />
        <Button 
          outline 
          label="Continue with Github"
          icon={AiFillGithub}
          onClick={() => null}
        />
        <div className="
        text-neutral-500 text-center mt-4 font-light">
          <p>First time using Airbnb?
            <span 
              onClick={onToggle} 
              className="
                text-neutral-800
                cursor-pointer 
                hover:underline
              "
              > Create an account</span>
          </p>
        </div>
      </div>
    )
  
    return (
      <Modal
        disabled={isLoading}
        isOpen={messageModal}
        title="Mssage Send"
        actionLabel="Continue"
        onClose={handleCloseModal}
        onSubmit={handleSubmit(onSubmit)}
        body={bodyContent}
        footer={footerContent}
      />
    );
  }
  
  export default MessageModal;