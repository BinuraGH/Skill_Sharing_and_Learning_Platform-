import ProgressUpdateItem from './ProgressUpdateItem';

const ProgressUpdateList = () => {
  const updates = [
    { id: 1, template: "Completed tutorial", description: "Just finished the official React hooks tutorial", date: "4/25/2025" },
    // Later you will fetch this from Spring Boot backend
  ];

  return (
    <div className="update-list">
      {updates.map((update) => (
        <ProgressUpdateItem key={update.id} update={update} />
      ))}
    </div>
  );
};

export default ProgressUpdateList;
