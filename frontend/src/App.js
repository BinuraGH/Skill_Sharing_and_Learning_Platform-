import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MyLearningPlans from "./Pages/MyLearningPlans";
import CourseDetailPage from "./Pages/CourseDetailPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MyLearningPlans />} />
        <Route path="/plans/:id" element={<CourseDetailPage />} />
      </Routes>
    </Router>
  );
}

export default App;
