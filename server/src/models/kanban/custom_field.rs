//! Module for managing the custom fields of a card in a kanban board.

use chrono::{DateTime, Utc};
use uuid::Uuid;

use crate::models::Identifiable;

/// Struct for representing a custom field of a card in a kanban board.
pub struct CustomField {
    id: Uuid,
    name: String,
    description: String,
    created_at: DateTime<Utc>,
    data_type: String, //TODO: change to CustomFieldType enum
    board_id: Uuid,
}

impl Identifiable for CustomField {
    fn id(&self) -> &Uuid {
        &self.id
    }
}

impl CustomField {
    pub fn new(name: &str, description: &str, data_type: &str, board_id: Uuid) -> Self {
        CustomField {
            id: Uuid::new_v4(),
            name: String::from(name),
            description: String::from(description),
            created_at: Utc::now(),
            data_type: String::from(data_type),
            board_id,
        }
    }
}
