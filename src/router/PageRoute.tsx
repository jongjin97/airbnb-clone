import { Routes, Route } from "react-router-dom";
import Container from "src/components/Container";
import HomePage from "src/page/HomePage";
import ListingPage from "src/page/ListingPage";


function PageRoute(){
    return (
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/listings/:id" element={<ListingPage/>} />
        </Routes>
    );
};
export default PageRoute;