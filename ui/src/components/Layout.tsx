import React, { useState } from 'react';
import SideBar from './SideBar';
import KanbanBoard from './Kanban/KanbanBoard';
import './Layout.css';

const Layout: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [currentView, setCurrentView] = useState<'home' | 'kanban' | 'shopping' | 'mealPlanner' | 'settings' | 'feedback'>('home');

    const renderView = () => {
        switch (currentView) {
            case 'kanban':
                return <KanbanBoard />;
            case 'home':
                return children;
            case 'shopping':
                return <div>Shopping List View</div>;
            case 'mealPlanner':
                return <div>Meal Planner View</div>;
            case 'settings':
                return <div>Settings View</div>;
            case 'feedback':
                return <div>Feedback View</div>;
            default:
                return children;
        }
    };

    return (
        <div className="layout">
            <SideBar onViewChange={setCurrentView} />
            <main className="main-content">
                {renderView()}
            </main>
        </div>
    );
}

export default Layout;
