import './App.css';
import Navbar from './components/navbar/Navbar';
import ToasterProvider from './providers/ToasterProvider';
import ModalsProvider from './providers/ModalsProvider';
import PageRoute from './router/PageRoute';
import { GoogleOAuthProvider } from '@react-oauth/google';

function App() {
  const key: string = process.env.REACT_APP_GOOGLE_CLIENT_ID as string;
  return (
    <>
      <GoogleOAuthProvider clientId={key}>
        <ToasterProvider />
        <ModalsProvider />
        <Navbar />
        <div className="pb-20 pt-28">
          <PageRoute />
        </div>
      </GoogleOAuthProvider>
    </>
  );
}

export default App;
