CREATE TABLE custom_metric_types (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    user_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    default_unit VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

ALTER TABLE exercise_metric_definitions 
    RENAME COLUMN metric_type TO built_in_type;

ALTER TABLE exercise_metric_definitions 
    ADD COLUMN custom_metric_type_id BIGINT,
    ADD CONSTRAINT fk_custom_metric_type 
    FOREIGN KEY (custom_metric_type_id) 
    REFERENCES custom_metric_types(id);

ALTER TABLE exercise_metric_definitions 
    ALTER COLUMN built_in_type DROP NOT NULL;
