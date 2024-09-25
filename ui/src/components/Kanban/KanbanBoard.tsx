import React, { useState, useEffect } from 'react';
import { KanbanBoardProps } from './KanbanTypes';
import { fetchKanbanBoard } from './KanbanService';
import { KanbanColumn } from './KanbanColumn';

export const KanbanBoard: React.FC = () => {
  const [board, setBoard] = useState<KanbanBoardProps | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadBoard = async () => {
      try {
        const boardId = 'your-board-id'; // Replace with actual board ID or prop
        const data = await fetchKanbanBoard(boardId);
        setBoard(data);
      } catch (err) {
        setError('Failed to load Kanban board');
        console.error(err);
      }
    };

    loadBoard();
  }, []);

  if (error) return <div>Error: {error}</div>;
  if (!board) return <div>Loading...</div>;

  return (
    <div className="kanban-board">
      <h1>{board.title}</h1>
      <div className="kanban-columns">
        {board.columns.map(column => (
          <KanbanColumn key={column.id} {...column} />
        ))}
      </div>
    </div>
  );
};
