import './App.css';
import Navbar from './components/navbar/Navbar';
import { BrowserRouter } from 'react-router-dom';
import ToasterProvider from './providers/ToasterProvider';
import ModalsProvider from './providers/ModalsProvider';

function App() {
  return (
    <BrowserRouter>
      <ToasterProvider />
      <ModalsProvider />
      <Navbar/>
    </BrowserRouter>
  );
}

export default App;
