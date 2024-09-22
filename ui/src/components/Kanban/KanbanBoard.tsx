import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './KanbanBoard.css';

interface KanbanCard {
  id: string;
  title: string;
  status: 'Backlog' | 'In Progress' | 'Done';
}

const KanbanBoard: React.FC = () => {
  const [cards, setCards] = useState<KanbanCard[]>([]);

  useEffect(() => {
    const fetchCards = async () => {
      try {
        const response = await axios.get('/api/kanban');
        setCards(response.data);
      } catch (error) {
        console.error('Error fetching Kanban cards:', error);
      }
    };

    fetchCards();
  }, []);

  const columns = ['Backlog', 'In Progress', 'Done'];

  return (
    <div className="kanban-board">
      {columns.map(column => (
        <div key={column} className="kanban-column">
          <h2>{column}</h2>
          {cards
            .filter(card => card.status === column)
            .map(card => (
              <div key={card.id} className="kanban-card">
                {card.title}
              </div>
            ))
          }
        </div>
      ))}
    </div>
  );
};

export default KanbanBoard;
