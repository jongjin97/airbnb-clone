import './App.css';
import Navbar from './components/navbar/Navbar';
import ToasterProvider from './providers/ToasterProvider';
import ModalsProvider from './providers/ModalsProvider';
import PageRoute from './router/PageRoute';
import { GoogleOAuthProvider } from '@react-oauth/google';
import ChatComponent from './test/ChatComponent';

function App() {
  return (
    <>
        <GoogleOAuthProvider clientId='97291905534-9160288hk39j0eoq22srmasgd4a9o19o.apps.googleusercontent.com' >
          <ToasterProvider />
          <ModalsProvider />
          <Navbar/>
          <div className="pb-20 pt-28"> 
            <PageRoute/>
            {/* <TestPage/> */}
          </div>
        </GoogleOAuthProvider>
    </>
  );
}

export default App;
