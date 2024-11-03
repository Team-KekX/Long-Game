ALTER TABLE custom_metric_types
ADD COLUMN value_type VARCHAR(50) NOT NULL DEFAULT 'NUMBER';

ALTER TABLE exercise_metrics
ADD COLUMN number_value DOUBLE PRECISION,
ADD COLUMN boolean_value BOOLEAN;

-- Migrate existing data
UPDATE exercise_metrics SET number_value = CAST(value AS DOUBLE PRECISION);
