import React from 'react';
import { Draggable } from 'react-beautiful-dnd';
import { KanbanCardProps } from './KanbanTypes';

interface ExtendedKanbanCardProps extends KanbanCardProps {
  index: number;
}

export const KanbanCard: React.FC<ExtendedKanbanCardProps> = ({ id, title, description, index }) => (
  <Draggable draggableId={id} index={index}>
    {(provided) => (
      <div
        ref={provided.innerRef}
        {...provided.draggableProps}
        {...provided.dragHandleProps}
        className="kanban-card"
      >
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    )}
  </Draggable>
);
