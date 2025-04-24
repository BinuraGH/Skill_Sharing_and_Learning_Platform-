import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MyLearningPlans from "./Pages/MyLearningPlans";
import CourseDetailPage from "./Pages/CourseDetailPage";
import BrowseCourses from './Pages/BrowseCourses';
import HomePage from "./Pages/HomePage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MyLearningPlans />} />
        <Route path="/plans/:id" element={<CourseDetailPage />} />
        <Route path="/browse" element={<BrowseCourses />} />
        <Route path="/Home" element={<HomePage />} />
      </Routes>
    </Router>
  );
}
export default App;
