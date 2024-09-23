//! Model for a kanban board.

use crate::models::Identifiable;
use chrono::{DateTime, Utc};
use uuid::Uuid;

use super::Column;

/// Struct for representing a kanban board.
pub struct Board {
    id: Uuid,
    pub name: String,
    pub description: String,
    created_at: DateTime<Utc>,
    columns: Vec<Column>,
    //TODO: add columns vector
}

impl Identifiable for Board {
    fn id(&self) -> &Uuid {
        &self.id
    }
}

impl Board {
    pub fn new(name: &str, description: &str) -> Self {
        Board {
            id: Uuid::new_v4(),
            name: String::from(name),
            description: String::from(description),
            created_at: Utc::now(),
            columns: Vec::new(),
        }
    }
}



