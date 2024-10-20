use axum::{
    http::StatusCode, routing::{get, post}, Json, Router, extract::State
};
use crate::models::{user::User, Identifiable};
use crate::services::user_service::UserService;
use crate::presentation::dto::user_dto::{RegisterUserRequest, LoginUserRequest, UserResponse};
use std::sync::Arc;

#[derive(Clone)]
pub struct UserController {
    user_service: Arc<UserService>,
}

impl UserController {
    pub fn new(user_service: UserService) -> Self {
        UserController { user_service: Arc::new(user_service) }
    }

    pub fn routes(&self) -> Router {
        Router::new()
            .route("/register", post(Self::register_user))
            .route("/login", post(Self::login_user))
            .with_state(Arc::new((*self).clone()))
    }

    async fn register_user(
        state: State<Arc<Self>>,
        Json(payload): Json<RegisterUserRequest>
    ) -> (StatusCode, Json<UserResponse>) {
        let self_ref = state.0;
        println!("Incoming register-request with payload: {:?}", payload);
        
        match self_ref.user_service.register_user(&payload.username, &payload.email, &payload.password).await {
            Ok(user) => (
                StatusCode::CREATED,
                Json(UserResponse {
                    id: Some(user.id().to_string()),
                    username: Some(user.username().to_string()), 
                    email: Some(user.email().to_string()), 
                    error: None,
                })
            ),
            Err(e) => (StatusCode::BAD_REQUEST, Json(UserResponse::error(&e))),
        }
    }

    async fn login_user(
        state: State<Arc<Self>>,
        Json(payload): Json<LoginUserRequest>
    ) -> (StatusCode, Json<UserResponse>) {
        let self_ref = state.0;
        println!("Incoming login-request with payload: {:?}", payload);
        
        match self_ref.user_service.login_user(&payload.email, &payload.password).await {
            Ok(user) => (
                StatusCode::OK,
                Json(UserResponse {
                    id: Some(user.id().to_string()),
                    username: Some(user.username().to_string()), 
                    email: Some(user.email().to_string()), 
                    error: None,
                })
            ),
            Err(e) => (StatusCode::UNAUTHORIZED, Json(UserResponse::error(&e))),
        }
    }
}
