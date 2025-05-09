/* ===========================================================
   Student Payment Management System – MySQL Schema
   Compatible with MySQL 8.x / phpMyAdmin
   =========================================================== */

CREATE DATABASE IF NOT EXISTS student_payment_db
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE student_payment_db;

/* -----------------------------------------------------------
   1.  programs  (library of courses)
   ----------------------------------------------------------- */
CREATE TABLE programs (
    program_id     INT AUTO_INCREMENT PRIMARY KEY,
    program_name   VARCHAR(100) NOT NULL UNIQUE,
    code           VARCHAR(20)  NULL,
    year_levels    INT          NOT NULL   -- e.g., 4 for a 4-year degree
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   2.  users  (all login accounts)
   ----------------------------------------------------------- */
CREATE TABLE users (
    user_id            INT AUTO_INCREMENT PRIMARY KEY,
    email              VARCHAR(150) NOT NULL UNIQUE,
    password           VARCHAR(255) NOT NULL,                 -- store hashed
    role               ENUM('admin','cashier','student') NOT NULL,
    security_question  VARCHAR(255) NULL,
    security_answer    VARCHAR(255) NULL                      -- store hashed/obfuscated
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   3.  students  (profile, linked to users & programs)
   ----------------------------------------------------------- */
CREATE TABLE students (
    student_id     VARCHAR(20) PRIMARY KEY,                   -- e.g., 230400001
    user_id        INT        NOT NULL,
    full_name      VARCHAR(150) NOT NULL,
    program_id     INT        NOT NULL,
    section        VARCHAR(20)  NULL,
    year_level     INT          NOT NULL,                     -- 1–4
    CONSTRAINT fk_students_user
        FOREIGN KEY (user_id)    REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_students_program
        FOREIGN KEY (program_id) REFERENCES programs(program_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   4.  tuition_matrix  (tuition per program & year)
   ----------------------------------------------------------- */
CREATE TABLE tuition_matrix (
    tuition_id    INT AUTO_INCREMENT PRIMARY KEY,
    program_id    INT NOT NULL,
    year_level    INT NOT NULL,
    amount        DECIMAL(12,2) NOT NULL,
    UNIQUE KEY uq_tuition (program_id, year_level),
    CONSTRAINT fk_tuition_program
        FOREIGN KEY (program_id) REFERENCES programs(program_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   5.  books_library  (free-purchase items)
   ----------------------------------------------------------- */
CREATE TABLE books_library (
    book_id   INT AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(150) NOT NULL,
    price     DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   6.  uniforms_library  (size-priced uniforms, optional program)
   ----------------------------------------------------------- */
CREATE TABLE uniforms_library (
    uniform_id  INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    size        ENUM('XS','S','M','L','XL') NOT NULL,
    price       DECIMAL(10,2) NOT NULL,
    program_id  INT NULL,
    CONSTRAINT fk_uniform_program
        FOREIGN KEY (program_id) REFERENCES programs(program_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   7.  other_fees_library  (custom fees)
   ----------------------------------------------------------- */
CREATE TABLE other_fees_library (
    fee_id      INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    amount      DECIMAL(10,2) NOT NULL,
    program_id  INT  NULL,
    year_level  INT  NULL,
    CONSTRAINT fk_otherfee_program
        FOREIGN KEY (program_id) REFERENCES programs(program_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   8.  student_fees  (all charges assigned to a student)
   ----------------------------------------------------------- */
CREATE TABLE student_fees (
    student_fee_id    INT AUTO_INCREMENT PRIMARY KEY,
    student_id        VARCHAR(20) NOT NULL,
    category          ENUM('tuition','book','uniform','other') NOT NULL,
    ref_id            INT NOT NULL,           -- points to tuition_matrix / book / uniform / other_fees
    original_amount   DECIMAL(12,2) NOT NULL,
    remaining_balance DECIMAL(12,2) NOT NULL,
    status            ENUM('unpaid','partial','paid') DEFAULT 'unpaid',
    INDEX ix_student_category (student_id, category),
    CONSTRAINT fk_sfee_student
        FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   9.  transactions  (actual payments)
   ----------------------------------------------------------- */
CREATE TABLE transactions (
    transaction_id   INT AUTO_INCREMENT PRIMARY KEY,
    student_fee_id   INT NOT NULL,
    student_id       VARCHAR(20) NOT NULL,
    cashier_id       INT NOT NULL,
    amount_paid      DECIMAL(12,2) NOT NULL,
    payment_date     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    remarks          TEXT NULL,
    CONSTRAINT fk_txn_fee
        FOREIGN KEY (student_fee_id) REFERENCES student_fees(student_fee_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_txn_student
        FOREIGN KEY (student_id)   REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_txn_cashier
        FOREIGN KEY (cashier_id)   REFERENCES users(user_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB;

/* -----------------------------------------------------------
   10. audit_log  (admin / system actions)
   ----------------------------------------------------------- */
CREATE TABLE audit_log (
    log_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT  NOT NULL,
    action     VARCHAR(100) NOT NULL,
    details    TEXT NULL,                     -- JSON / description
    log_time   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_log_user
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

/* ===========================================================
   Quick seed examples (remove or adjust as needed)
   =========================================================== */
INSERT INTO programs (program_name, code, year_levels)
VALUES ('BSIT','BSIT',4), ('BSEDUC','BSED',4);

INSERT INTO tuition_matrix (program_id, year_level, amount)
VALUES (1,1,20000),(1,2,23000),(1,3,25000),(1,4,27000);

-- Admin default user (password must be hashed in real use)
INSERT INTO users (email, password, role)
VALUES ('admin@school.local', 'hashed_admin_pw', 'admin');
