use axum::Router;
use log::{debug, info};
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
    println!("Loading environment variables...");
    // Load environment variables from .env file
    dotenv().ok();

    // Initialize logging
    env_logger::init();
    info!("Starting the server...");


    info!("Setting up database connection...");
    // Set up database connection
    let database_url = std::env::var("DATABASE_URL").expect("DATABASE_URL must be set");
    debug!("Connecting to database: {}", database_url);
    
    let pool = PgPool::connect(&database_url)
        .await
        .expect("Failed to connect to the database");

    info!("Initializing repositories, services, and controllers...");
    // Initialize repositories, services, and controllers
    let user_repository = UserRepository::new(pool);
    let user_service = UserService::new(user_repository);
    let user_controller = UserController::new(user_service);

    info!("Setting up routes...");
    let app = Router::new()
        .nest("/api/users", user_controller.routes());

    let addr = "0.0.0.0:3000";
    debug!("Server is being configured to listen on {}", addr);

    let listener = tokio::net::TcpListener::bind(addr)
        .await
        .expect("Failed to bind to address");
    debug!("Server is now listening on http://{}", addr);

    info!("Server is running!");
    axum::serve(listener, app).await.unwrap();
}
