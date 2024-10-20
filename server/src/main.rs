use axum::Router;
use sqlx::PgPool;
use dotenv::dotenv;

mod models;
mod repositories;
mod services;
mod presentation;

use presentation::controllers::user_controller::UserController;
use repositories::user_repository::UserRepository;
use services::user_service::UserService;

#[tokio::main]
async fn main() {
    println!("Starting the server...");

    // Load environment variables from .env file
    dotenv().ok();

    // Set up database connection
    let database_url = std::env::var("DATABASE_URL").expect("DATABASE_URL must be set");
    println!("Connecting to database: {}", database_url);
    
    let pool = PgPool::connect(&database_url)
        .await
        .expect("Failed to connect to the database");

    // Initialize repositories, services, and controllers
    let user_repository = UserRepository::new(pool);
    let user_service = UserService::new(user_repository);
    let user_controller = UserController::new(user_service);

    let app = Router::new()
        .nest("/api/users", user_controller.routes());

    let addr = "0.0.0.0:3000";
    println!("Server is being configured to listen on {}", addr);

    let listener = tokio::net::TcpListener::bind(addr)
        .await
        .expect("Failed to bind to address");
    println!("Server is now listening on http://{}", addr);

    axum::serve(listener, app).await.unwrap();
}
