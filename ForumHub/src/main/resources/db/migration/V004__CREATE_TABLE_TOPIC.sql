CREATE TABLE IF NOT EXISTS TOPICS (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(55) NOT NULL,
    message TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    status ENUM('OPEN', 'CLOSED', 'PENDING')  NOT NULL,
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(id),
    FOREIGN KEY (course_id) REFERENCES COURSES(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
