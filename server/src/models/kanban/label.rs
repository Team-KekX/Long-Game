//! Module for managing the labels in a kanban board.

use chrono::{DateTime, Utc};
use uuid::Uuid;

use crate::models::Identifiable;

/// Struct for representing a label for a card in a kanban board.
pub struct Label {
    id: Uuid,
    name: String,
    /// The color code of the label. Stored as a hex color code.
    color: String,
    created_at: DateTime<Utc>,
    board_id: Uuid,
}

impl Identifiable for Label {
    fn id(&self) -> &Uuid {
        &self.id
    }
}

impl Label {
    pub fn new(name: &str, color: &str, board_id: Uuid) -> Self {
        Label {
            id: Uuid::new_v4(),
            name: String::from(name),
            color: String::from(color),
            created_at: Utc::now(),
            board_id,
        }
    }
}

