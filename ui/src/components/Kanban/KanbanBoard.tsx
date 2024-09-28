import React, { useState, useEffect } from 'react';
import { KanbanBoardProps } from './KanbanTypes';
import { fetchKanbanBoard } from './KanbanService';
import { KanbanColumn } from './KanbanColumn';

// Placeholder data
const placeholderBoard: KanbanBoardProps = {
  id: 'test-board',
  title: 'Test Kanban Board',
  columns: [
    {
      id: 'col1',
      title: 'To Do',
      cards: [
        { id: 'card1', title: 'Task 1', description: 'Description for task 1' },
        { id: 'card2', title: 'Task 2', description: 'Description for task 2' },
      ],
    },
    {
      id: 'col2',
      title: 'In Progress',
      cards: [
        { id: 'card3', title: 'Task 3', description: 'Description for task 3' },
      ],
    },
    {
      id: 'col3',
      title: 'Done',
      cards: [
        { id: 'card4', title: 'Task 4', description: 'Description for task 4' },
        { id: 'card5', title: 'Task 5', description: 'Description for task 5' },
      ],
    },
  ],
};

export const KanbanBoard: React.FC = () => {
  const [board, setBoard] = useState<KanbanBoardProps | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadBoard = async () => {
      try {
        // Uncomment the following lines when backend is ready
        // const boardId = 'your-board-id';
        // const data = await fetchKanbanBoard(boardId);
        // setBoard(data);

        // For now, use placeholder data
        setBoard(placeholderBoard);
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
