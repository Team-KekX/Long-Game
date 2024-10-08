//! Module for managing the columns in a kanban board.

use chrono::{DateTime, Utc};
use uuid::Uuid;

use crate::models::Identifiable;

pub struct Column {
    id: Uuid,
    name: String,
    description: String,
    created_at: DateTime<Utc>,
    /// ID of the board the column is associated with.
    board_id: Uuid,
    /// Position of the column in the board.
    position: u8,
}

impl Identifiable for Column {
    fn id(&self) -> &Uuid {
        &self.id
    }
}

impl Column {
    pub fn new(name: &str, description: &str, board_id: Uuid) -> Self {
        Column {
            id: Uuid::new_v4(),
            name: String::from(name),
            description: String::from(description),
            created_at: Utc::now(),
            board_id,
            position: 0,
        }
    }
}

