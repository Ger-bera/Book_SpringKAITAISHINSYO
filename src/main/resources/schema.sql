-- employeeテーブルの作成
-- 注意: このテーブルは他のテーブルと命名規則が異なります
CREATE TABLE IF NOT EXISTS employee (
    id VARCHAR(50) PRIMARY KEY,  -- 従業員ID: 主キー
    name VARCHAR(50),            -- 従業員名
    age INT                      -- 従業員の年齢
);

-- ユーザーマスタテーブルの作成
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY,  -- ユーザーID: 主キー
    password VARCHAR(100),            -- パスワード（ハッシュ化することを推奨）
    user_name VARCHAR(50),            -- ユーザー名
    birthday DATE,                    -- 誕生日（注意: 元のSQLではbirthdayがbithday とタイプミスされています）
    age INT,                          -- 年齢
    gender INT,                       -- 性別（1: 男性, 2: 女性 など。ENUM型の使用も検討可）
    department_id INT,                -- 部署ID（m_departmentテーブルの外部キーとすべき）
    role VARCHAR(50)                  -- ユーザーの役割（ROLE_ADMIN, ROLE_GENERAL など）
);

-- 部署マスタテーブルの作成
CREATE TABLE IF NOT EXISTS m_department (
    department_id INT PRIMARY KEY,   -- 部署ID: 主キー
    department_name VARCHAR(50)      -- 部署名
);

-- 給料テーブルの作成
CREATE TABLE IF NOT EXISTS t_salary (
    user_id VARCHAR(50),              -- ユーザーID（m_userテーブルの外部キーとすべき）
    year_month VARCHAR(50),           -- 年月
    salary INT,                       -- 給与額
    PRIMARY KEY (user_id, year_month) -- 複合主キー
);

-- TODO: 外部キー制約の追加を検討
-- TODO: 適切なインデックスの追加を検討
