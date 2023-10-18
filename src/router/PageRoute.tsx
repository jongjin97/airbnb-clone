import { Routes, Route } from "react-router-dom";
import Container from "src/components/Container";
import HomePage from "src/page/HomePage";


function PageRoute(){
    return (
        <Routes>
          <Route path="/" element={<HomePage/>} />
        </Routes>
    );
};
export default PageRoute;