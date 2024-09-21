CREATE TABLE category_value (
                                id SERIAL PRIMARY KEY,
                                code VARCHAR(100) NOT NULL UNIQUE,
                                value VARCHAR(100) NOT NULL,
                                created_at TIMESTAMP NOT NULL,
                                modified_at TIMESTAMP NOT NULL
);

CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          domain_code VARCHAR(100) NOT NULL,
                          code VARCHAR(100) NOT NULL UNIQUE,
                          parent_code VARCHAR(100),
                          created_at TIMESTAMP NOT NULL,
                          modified_at TIMESTAMP NOT NULL,
                          FOREIGN KEY (parent_code) REFERENCES category(code) ON DELETE CASCADE,
                          FOREIGN KEY (code) REFERENCES category_value(code)
);

CREATE TABLE criteria (
                          id SERIAL PRIMARY KEY,
                          filter_id INTEGER,
                          criteria_type VARCHAR(100) NOT NULL,
                          condition VARCHAR(100) NOT NULL,
                          value TEXT NOT NULL,
                          is_selected BOOLEAN NOT NULL DEFAULT FALSE,
                          created_at TIMESTAMP NOT NULL,
                          modified_at TIMESTAMP NOT NULL
);

CREATE TABLE filter (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        modified_at TIMESTAMP NOT NULL
);

ALTER TABLE criteria ADD FOREIGN KEY (filter_id) REFERENCES filter(id) ON DELETE CASCADE;
ALTER TABLE criteria ADD FOREIGN KEY (criteria_type) REFERENCES category(code) ON DELETE CASCADE;
ALTER TABLE criteria ADD FOREIGN KEY (condition) REFERENCES category(code) ON DELETE CASCADE;

CREATE INDEX idx_category_value_code ON category_value (code);

CREATE INDEX idx_category_domain_code ON category (domain_code);
CREATE INDEX idx_category_code ON category (code);
CREATE INDEX idx_category_parent_code ON category (parent_code);

CREATE INDEX idx_criteria_filter_id ON criteria (filter_id);
CREATE INDEX idx_criteria_criteria_type ON criteria (criteria_type);
CREATE INDEX idx_criteria_condition ON criteria (condition);

INSERT INTO category_value (code, value, created_at, modified_at) VALUES
                                                                      ('CRITERIA_TYPE_AMOUNT', 'Amount', now(), now()),
                                                                      ('CRITERIA_TYPE_DATE', 'Date', now(), now()),
                                                                      ('CRITERIA_TYPE_TITLE', 'Title', now(), now()),
                                                                      ('CRITERIA_COMPARISON_MORE', 'More than', now(), now()),
                                                                      ('CRITERIA_COMPARISON_LESS', 'Less than', now(), now()),
                                                                      ('CRITERIA_COMPARISON_EQUAL', 'Equals', now(), now()),
                                                                      ('CRITERIA_COMPARISON_FROM', 'From', now(), now()),
                                                                      ('CRITERIA_COMPARISON_UNTIL', 'Until', now(), now()),
                                                                      ('CRITERIA_COMPARISON_AT_THIS_TIME', 'Exactly at', now(), now()),
                                                                      ('CRITERIA_COMPARISON_STARTS_WITH', 'Starts with', now(), now()),
                                                                      ('CRITERIA_COMPARISON_ENDS_WITH', 'Ends with', now(), now()),
                                                                      ('CRITERIA_COMPARISON_IS_LIKE', 'Exactly like', now(), now());

INSERT INTO category (domain_code, code, parent_code, created_at, modified_at) VALUES
                                                                                   ('CRITERIA_TYPE', 'CRITERIA_TYPE_AMOUNT', null, now(), now()),
                                                                                   ('CRITERIA_TYPE', 'CRITERIA_TYPE_DATE', null, now(), now()),
                                                                                   ('CRITERIA_TYPE', 'CRITERIA_TYPE_TITLE', null, now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_MORE', 'CRITERIA_TYPE_AMOUNT', now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_LESS', 'CRITERIA_TYPE_AMOUNT', now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_EQUAL', 'CRITERIA_TYPE_AMOUNT', now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_FROM', 'CRITERIA_TYPE_DATE', now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_UNTIL', 'CRITERIA_TYPE_DATE', now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_AT_THIS_TIME', 'CRITERIA_TYPE_DATE', now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_STARTS_WITH', 'CRITERIA_TYPE_TITLE', now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_ENDS_WITH', 'CRITERIA_TYPE_TITLE', now(), now()),
                                                                                   ('CRITERIA_COMPARISON', 'CRITERIA_COMPARISON_IS_LIKE', 'CRITERIA_TYPE_TITLE', now(), now());

INSERT INTO filter (name, created_at, modified_at) VALUES ('Test Filter12', now(), now());

INSERT INTO criteria (filter_id, criteria_type, condition, value, is_selected, created_at, modified_at)
VALUES
    (
        (SELECT id FROM filter WHERE name = 'Test Filter12'),
        (SELECT code FROM category WHERE code = 'CRITERIA_TYPE_AMOUNT'),
        (SELECT code FROM category WHERE code = 'CRITERIA_COMPARISON_LESS'),
        '5225',
        true,
        now(),
        now()
    ),
    (
        (SELECT id FROM filter WHERE name = 'Test Filter12'),
        (SELECT code FROM category WHERE code = 'CRITERIA_TYPE_AMOUNT'),
        (SELECT code FROM category WHERE code = 'CRITERIA_COMPARISON_MORE'),
        '5200',
        false,
        now(),
        now()
    );

INSERT INTO filter (name, created_at, modified_at) VALUES ('Filter for testing', now(), now());

INSERT INTO criteria (filter_id, criteria_type, condition, value, is_selected, created_at, modified_at)
VALUES
    (
        (SELECT id FROM filter WHERE name = 'Filter for testing'),
        (SELECT code FROM category WHERE code = 'CRITERIA_TYPE_TITLE'),
        (SELECT code FROM category WHERE code = 'CRITERIA_COMPARISON_STARTS_WITH'),
        'Tere',
        true,
        now(),
        now()
    ),
    (
        (SELECT id FROM filter WHERE name = 'Filter for testing'),
        (SELECT code FROM category WHERE code = 'CRITERIA_TYPE_TITLE'),
        (SELECT code FROM category WHERE code = 'CRITERIA_COMPARISON_ENDS_WITH'),
        'Tulemast',
        false,
        now(),
        now()
    );
