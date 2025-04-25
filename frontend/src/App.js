import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MyLearningPlans from "./Pages/MyLearningPlans";
import CourseDetailPage from "./Pages/CourseDetailPage";
import HomeTabs from "./Components/HomeTabs";
import ShowUserPosts from "./Components/ShowUserPosts";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomeTabs />} />
        <Route path="/showuserposts" element={<ShowUserPosts />} />
        <Route path="/plans" element={<MyLearningPlans />} />
        <Route path="/plans/:id" element={<CourseDetailPage />} />
      </Routes>
    </Router>
  );
}

export default App;
