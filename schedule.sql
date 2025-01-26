USE schedule_db;

CREATE TABLE IF NOT EXISTS User (
             id INT AUTO_INCREMENT PRIMARY KEY,
             name VARCHAR(255) NOT NULL,
              email VARCHAR(255) UNIQUE NOT NULL,
              password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Schedule (
             id INT AUTO_INCREMENT PRIMARY KEY,
             user_id INT NOT NULL,
             title VARCHAR(255) NOT NULL,
            description TEXT,
             date DATETIME NOT NULL,
             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            password VARCHAR(255) NOT NULL,
             FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);
