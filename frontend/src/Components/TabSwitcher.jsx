import React from 'react';

const TabSwitcher = ({ activeTab, setActiveTab }) => {
  return (
    <div className="tab-switcher">
      <button
        className={activeTab === 'login' ? 'active' : ''}
        onClick={() => setActiveTab('login')}
      >
        Login
      </button>
      <button
        className={activeTab === 'signup' ? 'active' : ''}
        onClick={() => setActiveTab('signup')}
      >
        Sign Up
      </button>
    </div>
  );
};

export default TabSwitcher;
