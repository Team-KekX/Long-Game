use serde::{Deserialize, Serialize};

#[derive(Deserialize, Debug)]
pub struct RegisterUserRequest {
    pub username: String,
    pub email: String,
    pub password: String,
}

#[derive(Deserialize, Debug)]
pub struct LoginUserRequest {
    pub email: String,
    pub password: String,
}

#[derive(Serialize, Debug)]
pub struct UserResponse {
    pub id: Option<String>,
    pub username: Option<String>,
    pub email: Option<String>,
    pub error: Option<String>,
}

impl UserResponse {
    pub fn error(message: &str) -> Self {
        UserResponse {
            id: None,
            username: None,
            email: None,
            error: Some(message.to_string()),
        }
    }
}
