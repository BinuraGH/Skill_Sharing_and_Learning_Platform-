import React, { useEffect, useState } from 'react';
import LearningPlanCard from '../Components/LearningPlanCard';
import './MyLearningPlans.css';

const MyLearningPlans = () => {
  const [plansWithProgress, setPlansWithProgress] = useState([]);
  const userId = "user123"; // Replace this later with your auth user

  useEffect(() => {
    const fetchEnrolledPlans = async () => {
      try {
        // Step 1: Get all enrollments for this user
        const enrollmentsRes = await fetch(`http://localhost:8080/api/enrollments/${userId}`);
        const enrollments = await enrollmentsRes.json();

        // Step 2: For each enrollment, fetch the plan and progress
        const enrichedPlans = await Promise.all(
          enrollments.map(async (enrollment) => {
            const planRes = await fetch(`http://localhost:8080/api/plans/${enrollment.planId}/progress`);
            const planData = await planRes.json();

            return {
              ...planData,
              progress: planData.progressPercentage,
            };
          })
        );

        setPlansWithProgress(enrichedPlans);
      } catch (err) {
        console.error("❌ Error fetching enrolled plans:", err);
      }
    };

    fetchEnrolledPlans();
  }, []);
  return (
    <div className="p-8">
      <h1 className="text-3xl font-bold mb-6">My Enrolled Learning Plans</h1>

      <div className="horizontal-scroll">
        {plansWithProgress.length > 0 ? (
          plansWithProgress.map((plan) => (
            <div className="scroll-card" key={plan.id}>
              <LearningPlanCard plan={plan} />
            </div>
          ))
        ) : (
          <p className="text-gray-500">You have not enrolled in any learning plans yet.</p>
        )}
      </div>
    </div>
  );
};

export default MyLearningPlans;
