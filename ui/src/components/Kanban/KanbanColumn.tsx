import React from 'react';
import { KanbanColumnProps } from './KanbanTypes';
import { KanbanCard } from './KanbanCard';

export const KanbanColumn: React.FC<KanbanColumnProps> = ({ title, cards }) => (
  <div className="kanban-column">
    <h2>{title}</h2>
    {cards.map(card => <KanbanCard key={card.id} {...card} />)}
  </div>
);
