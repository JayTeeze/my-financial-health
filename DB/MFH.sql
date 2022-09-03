-- Personal finance application database

CREATE DATABASE mfh_db;
USE mfh_db;

CREATE TABLE user (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR (25),
    last_name VARCHAR (25),
    state VARCHAR (3),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE = 'InnoDB';

CREATE TABLE credential (
	id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR (50),
    password VARCHAR (50),
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE = 'InnoDB';

CREATE TABLE financial (
	id INT PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    title VARCHAR (50) NOT NULL,
    description VARCHAR (100),
    transaction_date DATE,
    category ENUM('asset', 'liability', 'income', 'expense') NOT NULL,
    amount DECIMAL (12, 2),
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE = 'InnoDB';

CREATE TABLE balance_snapshot (
	id INT PRIMARY KEY AUTO_INCREMENT,
    amount DECIMAL (11, 2),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    date DATE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE = 'InnoDB';