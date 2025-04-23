import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MyLearningPlans from "./Pages/MyLearningPlans";
import CourseDetailPage from "./Pages/CourseDetailPage";
import AuthPage from "./Pages/AuthPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<AuthPage />} /> 
        <Route path="/plans" element={<MyLearningPlans />} />
        <Route path="/plans/:id" element={<CourseDetailPage />} />
      </Routes>
    </Router>
  );
}

export default App;
