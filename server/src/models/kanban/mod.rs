//! Module for managing kanban boards and their components.
//! 

mod board;
mod column;

/// Exporting the Board model.
pub use self::board::Board;
/// Exporting the Column model.
pub use self::column::Column;

