import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./Pages/HomePage";
import ManagePlans from "./Pages/ManagePlans"; // ✅ Import the Manage Plans page

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/Home" element={<HomePage />} />
        <Route path="/plans" element={<ManagePlans />} /> {/* ✅ Add the /plans route */}
      </Routes>
    </Router>
  );
}

export default App;
