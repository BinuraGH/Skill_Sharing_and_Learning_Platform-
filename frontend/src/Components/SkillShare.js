import React, { useState } from 'react';

const SkillShare = () => {
    const [comment, setComment] = useState("");

    return (
        <div className="max-w-3xl mx-auto p-4 space-y-6">
            {/* Post Input Box */}
            <div className="bg-white shadow-md rounded-lg p-4 space-y-4">
                <textarea
                    placeholder="Describe your post…"
                    className="w-full border border-gray-300 rounded px-3 py-2 resize-none"
                ></textarea>
                <div>
                    <label className="inline-block px-4 py-2 bg-gray-100 text-gray-700 rounded cursor-pointer hover:bg-gray-200">
                        Add Media Files
                        <input
                            type="file"
                            accept="image/*,video/*"
                            multiple
                            className="hidden"
                            onChange={(e) => console.log(e.target.files)}
                        />
                    </label>
                </div>
                <button className="bg-purple-500 hover:bg-purple-600 text-white px-6 py-2 rounded self-end">Post</button>
            </div>

            {/* Sample Post */}
            <div className="bg-white shadow-md rounded-lg p-4 space-y-4">
                {/* Header */}
                <div className="flex items-center space-x-3">
                    <img src="https://i.pravatar.cc/40" alt="User" className="w-10 h-10 rounded-full" />
                    <div>
                        <div className="font-semibold text-gray-800">Jane Cooper</div>
                        <div className="text-sm text-gray-500">Skill Share</div>
                    </div>
                </div>

                {/* Image */}
                <img
                    src="https://images.unsplash.com/photo-1517430816045-df4b7de11d1d?auto=format&fit=crop&w=900&q=60"
                    alt="Post"
                    className="rounded-lg w-full"
                />

                {/* Description */}
                <p className="text-gray-700">
                    Just built my first React component library! Check out the code and let me know what you think.
                    <span className="text-purple-600 font-medium"> #React #UI #Frontend</span>
                </p>

                {/* Tags */}
                <div className="flex space-x-2 text-sm text-purple-600">
                    <span className="bg-purple-100 px-2 py-1 rounded">#React</span>
                    <span className="bg-purple-100 px-2 py-1 rounded">#UI</span>
                    <span className="bg-purple-100 px-2 py-1 rounded">#Frontend</span>
                </div>

                {/* Reactions */}
                <div className="flex space-x-4 text-gray-500 text-sm">
                    <span>💬 12 comments</span>
                    <span>❤️ 234 likes</span>
                </div>

                {/* Comments */}
                <div className="space-y-2">
                    <div className="text-sm">
                        <strong>Robert Fox</strong>: Awesome work! Can you share the repo?
                    </div>
                    <div className="text-sm">
                        <strong>Jane Cooper</strong>: Congrats on shipping this. Looks neat.
                    </div>
                </div>

                {/* Add Comment */}
                <div className="flex space-x-2 mt-2">
                    <input
                        type="text"
                        placeholder="Add a comment..."
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                        className="flex-1 border border-gray-300 rounded px-3 py-2"
                    />
                    <button className="bg-purple-500 hover:bg-purple-600 text-white px-4 py-2 rounded">Reply</button>
                </div>
            </div>
        </div>
    );
};

export default SkillShare;
