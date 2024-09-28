import React, { useState, useEffect } from 'react';
import { DragDropContext, DropResult, DragStart } from 'react-beautiful-dnd';
import { KanbanBoardProps } from './KanbanTypes';
import { KanbanColumn } from './KanbanColumn';
import './KanbanBoard.css';

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
    console.log('Board data:', board);
  }, [board]);

  const onDragStart = (start: DragStart) => {
    console.log('Drag started', start);
  };

	const onDragEnd = (result: DropResult) => {
		console.log('Drag ended:', result);
		if (!result.destination || !board) return;

		const { source, destination } = result;
		const newColumns = Array.from(board.columns);
		const sourceColumn = newColumns.find(col => col.id === source.droppableId);
		const destColumn = newColumns.find(col => col.id === destination.droppableId);

		if (sourceColumn && destColumn) {
			const sourceCards = Array.from(sourceColumn.cards);
			const destCards = source.droppableId === destination.droppableId ? sourceCards : Array.from(destColumn.cards);
			const [movedCard] = sourceCards.splice(source.index, 1);
			destCards.splice(destination.index, 0, movedCard);

			const updatedColumns = newColumns.map(col => {
				if (col.id === source.droppableId) {
					return { ...col, cards: sourceCards };
				}
				if (col.id === destination.droppableId) {
					return { ...col, cards: destCards };
				}
				return col;
			});

			setBoard({ ...board, columns: updatedColumns });
		}
	};

	if (!board) return <div>Loading...</div>;

	return (
		<DragDropContext onDragEnd={onDragEnd}>
			<div className="kanban-board">
				<h1>{board.title}</h1>
				<div className="kanban-board-columns">
					{board.columns.map(column => (
						<KanbanColumn key={column.id} {...column} />
					))}
				</div>
			</div>
		</DragDropContext>
	);
};
