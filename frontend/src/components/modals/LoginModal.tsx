import { FcGoogle } from 'react-icons/fc';
import Button from '../Button';
import Modal from './Modal';
import { AiFillGithub } from 'react-icons/ai';
import Heading from '../Heading';
import Input from '../inputs/Input';
import { useCallback, useState } from 'react';
import { FieldValues, SubmitHandler, useForm } from 'react-hook-form';
import { useAppDispatch, useAppSelector } from 'src/app/hooks';
import { closeLoginModal } from 'src/features/modal/LoginModalAction';
import { openRegisterModal } from 'src/features/modal/RegisterModalAction';
import { doLogin } from 'src/features/auth/authAction';

const LoginModal = () => {
  const loginModal = useAppSelector((state) => state.login.isOpen);
  const registerModal = useAppSelector((state) => state.register.isOpen);
  const dispatch = useAppDispatch();
  const [isLoading, setIsLoading] = useState(false);
  const handleGoogleLogin = async () => {
    const authUrl = `http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:3000`;
    try {
      // 구글 로그인 URL로 리다이렉트
      window.location.href = authUrl;
    } catch (error) {
      console.error('구글 로그인 에러:', error);
    }
  };
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FieldValues>({
    defaultValues: {
      email: '',
      password: '',
    },
  });

  const onSubmit: SubmitHandler<FieldValues> = (data) => {
    setIsLoading(true);
    dispatch(doLogin(data));
    setIsLoading(false);
  };
  const handleCloseModal = useCallback(() => {
    dispatch(closeLoginModal());
  }, []);

  const onToggle = useCallback(() => {
    dispatch(closeLoginModal());
    dispatch(openRegisterModal());
  }, [loginModal, registerModal]);

  const bodyContent = (
    <div className="flex flex-col gap-4">
      <Heading title="Welcome back" subtitle="Login to your account!" />
      <Input
        id="email"
        label="Email"
        disabled={isLoading}
        register={register}
        errors={errors}
        required
      />
      <Input
        id="password"
        label="Password"
        type="password"
        disabled={isLoading}
        register={register}
        errors={errors}
        required
      />
    </div>
  );

  const footerContent = (
    <div className="flex flex-col gap-4 mt-3">
      <hr />
      <Button
        outline
        label="Continue with Google"
        icon={FcGoogle}
        onClick={handleGoogleLogin}
      />
      <Button
        outline
        label="Continue with Github"
        icon={AiFillGithub}
        onClick={() => null}
      />
      <div
        className="
        text-neutral-500 text-center mt-4 font-light"
      >
        <p>
          First time using Airbnb?
          <span
            onClick={onToggle}
            className="
                text-neutral-800
                cursor-pointer 
                hover:underline
              "
          >
            {' '}
            Create an account
          </span>
        </p>
      </div>
    </div>
  );

  return (
    <Modal
      disabled={isLoading}
      isOpen={loginModal}
      title="Login"
      actionLabel="Continue"
      onClose={handleCloseModal}
      onSubmit={handleSubmit(onSubmit)}
      body={bodyContent}
      footer={footerContent}
    />
  );
};

export default LoginModal;
