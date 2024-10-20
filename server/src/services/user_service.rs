use crate::models::user::User;
use crate::repositories::user_repository::UserRepository;
use uuid::Uuid;

pub struct UserService {
    user_repository: UserRepository,
}

impl UserService {
    pub fn new(user_repository: UserRepository) -> Self {
        UserService { user_repository }
    }

    pub async fn register_user(&self, username: &str, email: &str, password: &str) -> Result<User, String> {
        // Check if user already exists
        if let Ok(Some(_)) = self.user_repository.find_user_by_email(email).await {
            return Err("User with this email already exists".to_string());
        }

        // Create new user
        let user = User::new(username, email, password);

        // Save user to database
        match self.user_repository.save_user(&user).await {
            Ok(_) => Ok(user),
            Err(_) => Err("Failed to save user".to_string()),
        }
    }

    pub async fn login_user(&self, email: &str, password: &str) -> Result<User, String> {
        match self.user_repository.find_user_by_email(email).await {
            Ok(Some(user)) => {
                if user.verify_password(password) {
                    Ok(user)
                } else {
                    Err("Invalid password".to_string())
                }
            },
            Ok(None) => Err("User not found".to_string()),
            Err(_) => Err("Database error".to_string()),
        }
    }
}
