use crate::models::user::User;
use sqlx::PgPool;
use sqlx::types::Uuid;

pub struct UserRepository {
    pool: PgPool,
}

impl UserRepository {
    pub fn new(pool: PgPool) -> Self {
        UserRepository { pool }
    }

    pub async fn save_user(&self, user: &User) -> Result<(), sqlx::Error> {
        sqlx::query!(
            "INSERT INTO users (id, username, email, password, creation_date) VALUES ($1, $2, $3, $4, $5)",
            user.id as Uuid,
            user.username,
            user.email,
            user.password,
            user.creation_date
        )
        .execute(&self.pool)
        .await?;

        Ok(())
    }

    pub async fn find_user_by_email(&self, email: &str) -> Result<Option<User>, sqlx::Error> {
        let user = sqlx::query_as!(
            User,
            "SELECT * FROM users WHERE email = $1",
            email
        )
        .fetch_optional(&self.pool)
        .await?;

        Ok(user)
    }

    pub async fn update_user(&self, user: &User) -> Result<bool, sqlx::Error> {
        let result = sqlx::query!(
            r#"
            UPDATE users
            SET username = $1, email = $2, password = $3, version = version + 1
            WHERE id = $4 AND version = $5
            "#,
            user.username,
            user.email,
            user.password,
            user.id,
            user.version
        )
        .execute(&self.pool)
        .await?;

        Ok(result.rows_affected() > 0)
    }
}
