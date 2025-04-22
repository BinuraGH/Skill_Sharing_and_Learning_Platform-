import React, { useEffect, useState } from 'react';
import LearningPlanCard from '../Components/LearningPlanCard';
import './MyLearningPlans.css';

const BrowseCourses = () => {
  const [plans, setPlans] = useState([]);
  const [filteredPlans, setFilteredPlans] = useState([]);
  const [filter, setFilter] = useState('all');
  const [enrolledPlans, setEnrolledPlans] = useState([]);
  const userId = "user123"; // Replace with real user if needed

  useEffect(() => {
    const fetchAllPlans = async () => {
      try {
        const res = await fetch('http://localhost:8080/api/plans');
        const allPlans = await res.json();

        const enriched = await Promise.all(
          allPlans.map(async (plan) => {
            const res = await fetch(`http://localhost:8080/api/plans/${plan.id}/progress`);
            const data = await res.json();
            return { ...data, progress: data.progressPercentage };
          })
        );
        setPlans(enriched);
        setFilteredPlans(enriched);
      } catch (err) {
        console.error("❌ Error fetching courses:", err);
      }
    };

    fetchAllPlans();
  }, []);

  const applyFilter = (type) => {
    setFilter(type);
    if (type === 'free') {
      setFilteredPlans(plans.filter(plan => !plan.paid));
    } else if (type === 'paid') {
      setFilteredPlans(plans.filter(plan => plan.paid));
    } else {
      setFilteredPlans(plans);
    }
  };

  const handleEnroll = async (planId) => {
    const res = await fetch(`http://localhost:8080/api/enrollments?userId=${userId}&planId=${planId}`, {
      method: "POST"
    });

    if (res.ok) {
      alert("✅ Successfully enrolled!");
      setEnrolledPlans((prev) => [...prev, planId]); // ✅ Add to enrolled state
    } else {
      alert("❌ Already enrolled or failed.");
    }
  };

  return (
    <div className="p-8">
      <h1 className="text-3xl font-bold mb-4">Browse All Courses</h1>

      {/* Filter Buttons */}
      <div className="mb-6 space-x-3">
        <button onClick={() => applyFilter('all')} className={`px-4 py-1 rounded ${filter === 'all' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}>
          All
        </button>
        <button onClick={() => applyFilter('free')} className={`px-4 py-1 rounded ${filter === 'free' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}>
          Free
        </button>
        <button onClick={() => applyFilter('paid')} className={`px-4 py-1 rounded ${filter === 'paid' ? 'bg-blue-600 text-white' : 'bg-gray-200'}`}>
          Paid
        </button>
      </div>

      <div className="flex flex-wrap gap-6">
        {filteredPlans.length > 0 ? (
          filteredPlans.map((plan) => (
            <div key={plan.id} className="min-w-[280px] max-w-[300px]">
              <LearningPlanCard plan={plan} />
              {enrolledPlans.includes(plan.id) ? (
                <button
                  className="mt-2 bg-gray-400 text-white px-4 py-2 w-full rounded cursor-default"
                  disabled
                >
                  Enrolled
                </button>
              ) : (
                <button
                  onClick={() => handleEnroll(plan.id)}
                  className="mt-2 bg-green-600 text-white px-4 py-2 w-full rounded hover:bg-green-700"
                >
                  Enroll Now
                </button>
              )}
            </div>
          ))
        ) : (
          <p className="text-gray-500">No courses found for selected filter.</p>
        )}
      </div>
    </div>
  );
};

export default BrowseCourses;
