

import CreateProgressUpdate from '../Components/ProgressUpdate/CreateProgressUpdate';
import ProgressUpdateList from '../Components/ProgressUpdate/ProgressUpdateList';
import '../styles/main.css';

const ManageLearningPage = () => {
  return (
    <div className="container">
      <h1>Manage Your Learning</h1>
      <div className="tabs">
        <button className="tab active">Learning Plans</button>
        <button className="tab">Progress Updates</button>
      </div>

      <div className="section">
        <h2>Progress Updates</h2>
        <p>Track your learning journey by adding progress updates. These appear in the "Progress" tab of your profile.</p>
      </div>

      <CreateProgressUpdate />
      <ProgressUpdateList />
    </div>
  );
};

export default ManageLearningPage;
