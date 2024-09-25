//! Module for managing the cards in a kanban board.

use chrono::{DateTime, Utc};
use uuid::Uuid;

use crate::models::Identifiable;

/// Struct for representing a kanban card.
pub struct Card {
    id: Uuid,
    title: String,
    description: String,
    created_at: DateTime<Utc>,
    due_date: Option<DateTime<Utc>>,
    finished_at: Option<DateTime<Utc>>,
    labels: Vec<String>, //TODO: change to enum
    custom_fields: Vec<String>, //TODO: change to custom fields
    column_id: Uuid,
    position: u16,
}

impl Identifiable for Card {
    fn id(&self) -> &Uuid {
        &self.id
    }
}

impl Card {
    pub fn new(title: &str, description: &str, column_id: Uuid) -> Self {
        Card {
            id: Uuid::new_v4(),
            title: String::from(title),
            description: String::from(description),
            created_at: Utc::now(),
            due_date: None,
            finished_at: None,
            labels: Vec::new(),
            custom_fields: Vec::new(),
            column_id,
            position: 0,
        }
    }
}
