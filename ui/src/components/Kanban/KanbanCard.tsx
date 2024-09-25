import React from 'react';
import { KanbanCardProps } from './KanbanTypes';

export const KanbanCard: React.FC<KanbanCardProps> = (card) => {
  return (
    <div className="kanban-card">
      <h3>{card.title}</h3>
      <p>{card.description}</p>
    </div>
  );
};
