import { useState, useCallback } from 'react';
import { useForm, FieldValues, SubmitHandler } from 'react-hook-form';
import { AiFillGithub } from 'react-icons/ai';
import { FcGoogle } from 'react-icons/fc';
import Input from '../inputs/Input';
import Modal from './Modal';
import Button from '../Button';
import Heading from '../Heading';
import { useAppDispatch, useAppSelector } from 'src/app/hooks';
import { closeRegisterModal } from 'src/features/modal/RegisterModalAction';
import { RootState } from 'src/app/store';
import { openLoginModal } from 'src/features/modal/LoginModalAction';
import { doSignUp } from 'src/features/auth/authAction';

const RegisterModal = () => {
  const registerModal = useAppSelector(
    (state: RootState) => state.register.isOpen
  );
  const loginModal = useAppSelector((state: RootState) => state.login.isOpen);
  const dispatch = useAppDispatch();
  const [isLoading, setIsLoading] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FieldValues>({
    defaultValues: {
      name: '',
      email: '',
      password: '',
    },
  });
  const handleCloseModal = useCallback(() => {
    dispatch(closeRegisterModal());
  }, []);
  const onSubmit: SubmitHandler<FieldValues> = (data) => {
    console.log(data);
    setIsLoading(true);
    dispatch(doSignUp(data));
    setIsLoading(false);
  };

  const onToggle = useCallback(() => {
    dispatch(closeRegisterModal());
    dispatch(openLoginModal());
  }, [registerModal, loginModal]);
  const bodyContent = (
    <div className="flex flex-col gap-4">
      <Heading title="Welcome to Airbnb" subtitle="Create an account!" />
      <Input
        id="email"
        label="Email"
        disabled={isLoading}
        register={register}
        errors={errors}
        required
      />
      <Input
        id="name"
        label="Name"
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
        onClick={() => null}
      />
      <Button
        outline
        label="Continue with Github"
        icon={AiFillGithub}
        //onClick={() => signIn('github')}
        onClick={() => null}
      />
      <div
        className="
            text-neutral-500 
            text-center 
            mt-4 
            font-light
          "
      >
        <p>
          Already have an account?
          <span
            onClick={onToggle}
            className="
                text-neutral-800
                cursor-pointer 
                hover:underline
              "
          >
            {' '}
            Log in
          </span>
        </p>
      </div>
    </div>
  );

  return (
    <Modal
      disabled={isLoading}
      isOpen={registerModal}
      title="Register"
      actionLabel="Continue"
      onClose={handleCloseModal}
      onSubmit={handleSubmit(onSubmit)}
      body={bodyContent}
      footer={footerContent}
    />
  );
};

export default RegisterModal;
