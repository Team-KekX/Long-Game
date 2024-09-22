import React from 'react';
import './App.css';
import Layout from './components/Layout';

const App: React.FC = () => {
  return (
    <Layout>
      <div className="home-view">
        <h1>Welcome to the App</h1>
        <p>This is the Home view. Select other views from the sidebar.</p>
      </div>
    </Layout>
  );
}

export default App;
