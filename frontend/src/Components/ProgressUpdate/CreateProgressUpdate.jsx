import { useState } from 'react';
import '../../styles/main.css'; // make sure this path is correct!

const CreateProgressUpdate = () => {
  const [userId, setUserId] = useState('');
  const [title, setTitle] = useState('');
  const [caption, setCaption] = useState('');
  const [status, setStatus] = useState('Draft');
  const [imgLink, setImgLink] = useState('');
  const [likedBy, setLikedBy] = useState('');
  const [template, setTemplate] = useState('Completed tutorial');
  const [description, setDescription] = useState('');

  const handleCreate = () => {
    console.log('Creating:', {
      userId,
      title,
      caption,
      status,
      imgLink,
      likedBy,
      template,
      description
    });
    // Here you would call your Spring Boot backend POST API
  };

  return (
    <div className="container">
      <div className="card">
        <h3>Create Progress Update</h3>

        <label>User ID</label>
        <input 
          type="text"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          placeholder="Enter your user ID"
        />

        <label>Title</label>
        <input 
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Enter title"
        />

        <label>Caption</label>
        <textarea 
          value={caption}
          onChange={(e) => setCaption(e.target.value)}
          placeholder="Enter a caption"
        />

        <label>Status</label>
        <select value={status} onChange={(e) => setStatus(e.target.value)}>
          <option value="Draft">Draft</option>
          <option value="Published">Published</option>
        </select>

        <label>Image Link</label>
        <input 
          type="text"
          value={imgLink}
          onChange={(e) => setImgLink(e.target.value)}
          placeholder="https://example.com/image.png"
        />

        <label>Liked By (comma-separated user IDs)</label>
        <input 
          type="text"
          value={likedBy}
          onChange={(e) => setLikedBy(e.target.value)}
          placeholder="e.g., user1,user2,user3"
        />

        <label>Template</label>
        <select value={template} onChange={(e) => setTemplate(e.target.value)}>
          <option value="Completed tutorial">Completed tutorial</option>
          <option value="Started course">Started course</option>
          <option value="Completed project">Completed project</option>
        </select>

        <label>Description</label>
        <textarea 
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          placeholder="Just finished the tutorial..."
        />

        <div className="btn-group">
          <button className="btn primary" onClick={handleCreate}>Create</button>
          <button className="btn" onClick={() => {
            setUserId('');
            setTitle('');
            setCaption('');
            setStatus('Draft');
            setImgLink('');
            setLikedBy('');
            setTemplate('Completed tutorial');
            setDescription('');
          }}>Cancel</button>
        </div>
      </div>
    </div>
  );
};

export default CreateProgressUpdate;
