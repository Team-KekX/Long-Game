use chrono::{DateTime, Utc};
use uuid::Uuid;
use crate::models::Identifiable;
#[derive(sqlx::FromRow)]
pub struct User {
    pub id: Uuid,
    pub username: String,
    pub email: String,
    pub password: String, // TODO: hash the password
    pub creation_date: DateTime<Utc>,
    pub version: i32,
}

impl Identifiable for User {
    fn id(&self) -> &Uuid {
        &self.id
    }
}

impl User {
    pub fn new(username: &str, email: &str, password: &str) -> Self {
        User {
            id: Uuid::new_v4(),
            username: String::from(username),
            email: String::from(email),
            password: String::from(password),
            creation_date: Utc::now(),
            version: 0,
        }
    }

    pub fn verify_password(&self, password: &str) -> bool {
        self.password == password
    }

    pub fn username(&self) -> &str {
        &self.username
    }

    pub fn email(&self) -> &str {
        &self.email
    }
}
