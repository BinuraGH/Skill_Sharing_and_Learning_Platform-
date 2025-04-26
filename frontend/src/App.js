import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MyLearningPlans from "./Pages/MyLearningPlans";
import CourseDetailPage from "./Pages/CourseDetailPage";
import ManageLearningPage from "./Pages/ManageLearningPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/MyLearning" element={<MyLearningPlans />} />
        <Route path="/plans/:id" element={<CourseDetailPage />} />
        <Route path="/" element={<ManageLearningPage />} />
      </Routes>
    </Router>
  );
}



export default App;
