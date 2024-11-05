CREATE TABLE exercise_metric_definitions (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    exercise_id BIGINT NOT NULL,
    metric_type VARCHAR(50) NOT NULL,
    required BOOLEAN NOT NULL DEFAULT false,
    unit VARCHAR(50),
    FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);