use chrono::{DateTime, Utc};

use crate::models::Identifiable;
use uuid::Uuid;

/// Enum for representing the data type and value of custom field value.
pub enum CustomFieldValueType {
    Text(String),
    Number(f64),
    Date(DateTime<Utc>),
    Boolean(bool),
}
