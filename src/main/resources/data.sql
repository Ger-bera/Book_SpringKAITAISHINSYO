-- employeeテーブルへのサンプルデータ挿入
INSERT INTO employee (id, name, age) VALUES ('1', 'Tom', 30);

-- ユーザーマスタテーブルへのデータ挿入
INSERT INTO m_user (user_id, password, user_name, birthday, age, gender, department_id, role)
VALUES
    -- システム管理者のデータ
    ('system@co.jp', '$2a$10$4Ky8te8nrjyta4oTyOPTx.bcWMot/SqjU/v7RyEHCGmWqrmwwr8VW', 'システム管理者', '2000-01-01', 21, 1, 1, 'ROLE_ADMIN'),
    -- 一般ユーザーのデータ
    ('user@co.jp', '$2a$10$4Ky8te8nrjyta4oTyOPTx.bcWMot/SqjU/v7RyEHCGmWqrmwwr8VW', 'ユーザー1', '2000-01-01', 21, 2, 2, 'ROLE_GENERAL');

-- 部署マスタテーブルへのデータ挿入
INSERT INTO m_department (department_id, department_name)
VALUES
    (1, 'システム管理部'),
    (2, '営業部');

-- 給料テーブルへのデータ挿入
INSERT INTO t_salary (user_id, year_month, salary)
VALUES
    ('user@co.jp', '2020/11', 280000),
    ('user@co.jp', '2020/12', 290000),
    ('user@co.jp', '2021/01', 300000);

-- 注意: 本番環境ではこのようなサンプルデータの挿入は避け、適切なデータ投入プロセスを使用すべきです