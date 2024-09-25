import React from 'react';
import './SideBar.css';
import HomeIcon from '@mui/icons-material/Home';
import ViewKanbanIcon from '@mui/icons-material/ViewKanban';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import SettingsIcon from '@mui/icons-material/Settings';
import FeedbackIcon from '@mui/icons-material/Feedback';
import RestaurantMenuIcon from '@mui/icons-material/RestaurantMenu';

interface SideBarProps {
  onViewChange: (view: 'home' | 'kanban' | 'shopping' | 'mealPlanner' | 'settings' | 'feedback') => void;
}

const SideBar: React.FC<SideBarProps> = ({ onViewChange }) => {
    const topItems = [
        { name: 'Home', icon: <HomeIcon />, view: 'home' },
        { name: 'Kanban', icon: <ViewKanbanIcon />, view: 'kanban' },
        { name: 'Shopping List', icon: <ShoppingCartIcon />, view: 'shopping' },
        //{ name: 'Calendar', icon: <CalendarIcon />, view: 'calendar' },
        { name: 'Meal Planner', icon: <RestaurantMenuIcon />, view: 'mealPlanner' }
    ];
    const bottomItems = [
        { name: 'Settings', icon: <SettingsIcon />, view: 'settings' },
        { name: 'Feedback', icon: <FeedbackIcon />, view: 'feedback' }
    ];

    return (
        <aside className="sidebar">
            <nav>
                <ul className="sidebar-list">
                    {topItems.map((item, index) => (
                        <li key={index}>
                            <button 
                              className="sidebar-button"
                              onClick={() => onViewChange(item.view as 'home' | 'kanban' | 'shopping' | 'mealPlanner')}
                            >
                                {item.icon && <span className="sidebar-icon">{item.icon}</span>}
                                {item.name}
                            </button>
                        </li>
                    ))}
                </ul>
            </nav>
            <nav>
                <ul className="sidebar-list">
                    {bottomItems.map((item, index) => (
                        <li key={index}>
                            <button 
                              className="sidebar-button"
                              onClick={() => onViewChange(item.view as 'settings' | 'feedback')}
                            >
                                {item.icon && <span className="sidebar-icon">{item.icon}</span>}
                                {item.name}
                            </button>
                        </li>
                    ))}
                </ul>
            </nav>
        </aside>
    );
};

export default SideBar;
