import './App.css';
import Navbar from './components/navbar/Navbar';
import ToasterProvider from './providers/ToasterProvider';
import ModalsProvider from './providers/ModalsProvider';
import PageRoute from './router/PageRoute';

function App() {
  return (
    <>
        <ToasterProvider />
        <ModalsProvider />
        <Navbar/>
        <div className="pb-20 pt-28"> 
          <PageRoute/>
          {/* <TestPage/> */}
        </div>
    </>
  );
}

export default App;
