import { Routes, Route } from "react-router-dom";
import Container from "src/components/Container";
import FavoritePage from "src/page/FavoritePage";
import HomePage from "src/page/HomePage";
import ListingPage from "src/page/ListingPage";
import Oauth from "src/page/Oauth";
import PropertiesPage from "src/page/PropertiesPage";
import ReservationsPage from "src/page/ReservationPage";
import TripsPage from "src/page/TripsPage";
import ChatComponent from "src/test/ChatComponent";

function PageRoute(){
    return (
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/listings/:id" element={<ListingPage/>} />
          <Route path="/favorites" element={<FavoritePage/>} />
          <Route path="/trips" element={<TripsPage/>} />
          <Route path="/reservations" element={<ReservationsPage/>} />
          <Route path="/properties" element={<PropertiesPage/>} />
          <Route path="/auth/login" element={<Oauth />} />
          <Route path="/messages/:id" element={<ChatComponent />} />
          <Route path="/messages" element={<ChatComponent />} />
        </Routes>
    );
};
export default PageRoute;