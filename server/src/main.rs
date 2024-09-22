use axum::{
    routing::get,
    Router,
};

pub mod models;
pub mod repositories;
pub mod services;
pub mod controllers;

#[tokio::main]
async fn main() {
    println!("Hello, world!");

    let app = Router::new().route("/", get(|| async { "Hello, World!" }));

    // run our app with hyper, listening globally on port 3000
    let listener = tokio::net::TcpListener::bind("0.0.0.0:3000").await.unwrap();
    axum::serve(listener, app).await.unwrap();

}