ALTER TABLE stat_items
    ADD COLUMN user_id BIGINT NOT NULL DEFAULT (1);

ALTER TABLE stat_items
    ADD CONSTRAINT stat_items__user_id_users_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id);