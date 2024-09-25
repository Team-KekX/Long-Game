import config from '../../config';
import { KanbanBoardProps } from './KanbanTypes';

export const fetchKanbanBoard = async (boardId: string): Promise<KanbanBoardProps> => {
  const response = await fetch(`${config.API_BASE_URL}/api/kanban/${boardId}`);
  
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  
  const data = await response.json();
  
  if (isKanbanBoardProps(data)) {
    return data;
  } else {
    throw new Error('Invalid data structure received from server');
  }
};

// Type guard function
function isKanbanBoardProps(data: any): data is KanbanBoardProps {
  return (
    typeof data === 'object' &&
    data !== null &&
    typeof data.id === 'string' &&
    typeof data.title === 'string' &&
    Array.isArray(data.columns) &&
    data.columns.every((column: any) => 
      typeof column.id === 'string' &&
      typeof column.title === 'string' &&
      Array.isArray(column.cards) &&
      column.cards.every((card: any) => 
        typeof card.id === 'string' &&
        typeof card.title === 'string' &&
        typeof card.description === 'string'
      )
    )
  );
}
