export interface KanbanBoardProps {
  id: string;
  title: string;
  columns: KanbanColumnProps[];
}

export interface KanbanColumnProps {
  id: string;
  title: string;
  cards: KanbanCardProps[];
}

export interface KanbanCardProps {
  id: string;
  title: string;
  description: string;
}