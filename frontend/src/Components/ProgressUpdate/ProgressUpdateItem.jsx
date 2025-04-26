const ProgressUpdateItem = ({ update }) => {
    return (
      <div className="card">
        <h4>{update.template}</h4>
        <small>{update.date}</small>
        <p>{update.description}</p>
        <div className="btn-group">
          <button className="btn">Edit</button>
          <button className="btn danger">Delete</button>
        </div>
      </div>
    );
  };
  
  export default ProgressUpdateItem;
  