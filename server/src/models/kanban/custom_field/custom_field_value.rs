//! Module for managing the values of a custom field for a card in a kanban board.

use uuid::Uuid;

use super::CustomFieldValueType;

/// Struct for representing the value of a custom field for a card in a kanban board.
pub struct CustomFieldValue {
    custom_field_id: Uuid,
    card_id: Uuid,
    value: CustomFieldValueType,
}

impl CustomFieldValue {
    pub fn new(custom_field_id: Uuid, card_id: Uuid, value: CustomFieldValueType) -> Self {
        CustomFieldValue {
            custom_field_id,
            card_id,
            value,
        }
    }
}

