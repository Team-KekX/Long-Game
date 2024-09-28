import React from 'react';
import { Droppable } from 'react-beautiful-dnd';
import { KanbanColumnProps } from './KanbanTypes';
import { KanbanCard } from './KanbanCard';

export const KanbanColumn: React.FC<KanbanColumnProps> = ({ id, title, cards }) => (
  <Droppable droppableId={id}>
    {(provided) => (
      <div
        ref={provided.innerRef}
        {...provided.droppableProps}
        className="kanban-column"
      >
        <h2 className="kanban-column-title">{title}</h2>
        <div className="kanban-column-content">
          {cards.map((card, index) => (
            <KanbanCard key={card.id} index={index} {...card} />
          ))}
          {provided.placeholder}
        </div>
      </div>
    )}
  </Droppable>
);
