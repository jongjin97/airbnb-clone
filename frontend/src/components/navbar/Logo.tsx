import { useNavigate } from 'react-router-dom';

const Logo = () => {
  const router = useNavigate();
  return (
    <img
      // onClick={() => router("/")}
      className="hidden md:block cursor-pointer"
      onClick={() => router('/')}
      src="/logo.png"
      height="100"
      width="100"
      alt="Logo"
    />
  );
};

export default Logo;
