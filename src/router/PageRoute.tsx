import { Routes, Route } from "react-router-dom";
import Container from "src/components/Container";
import FavoritePage from "src/page/FavoritePage";
import HomePage from "src/page/HomePage";
import ListingPage from "src/page/ListingPage";
import ReservationsPage from "src/page/ReservationPage";
import TripsPage from "src/page/TripsPage";


function PageRoute(){
    return (
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/listings/:id" element={<ListingPage/>} />
          <Route path="/favorites" element={<FavoritePage/>} />
          <Route path="/trips" element={<TripsPage/>} />
          <Route path="/reservations" element={<ReservationsPage/>} />
        </Routes>
    );
};
export default PageRoute;