import { useEffect } from 'react';
import { useLocation, useNavigate, useSearchParams } from 'react-router-dom';
import { useAppDispatch } from 'src/app/hooks';
import { setUser } from 'src/features/auth/authReducer';
import { persistToken } from 'src/service/localStorage.service';

const Oauth = () => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const token = queryParams.get('accessToken');
  const userString = queryParams.get('user');
  const userJson = userString ? decodeURIComponent(userString) : null;
  const user = userJson ? JSON.parse(userJson) : null;
  const dispatch = useAppDispatch();
  const router = useNavigate();
  useEffect(() => {
    if (token) persistToken(token);
    dispatch(setUser(user));
    router('/');
  }, []);

  return <></>;
};

export default Oauth;
