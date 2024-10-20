//! Module for managing the domain models in the application.

use uuid::Uuid;

pub mod kanban;
pub mod user;

/// Trait for entities that have an identifier.
pub trait Identifiable {
    fn id(&self) -> &Uuid;
}

