-- Tạo database
CREATE DATABASE novel_website;
USE novel_website;

-- Tạo bảng users
CREATE TABLE users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(100),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng stories
CREATE TABLE stories (
                         story_id INT PRIMARY KEY AUTO_INCREMENT,
                         title VARCHAR(255) NOT NULL,
                         author VARCHAR(100),
                         description TEXT,
                         cover_image TEXT,
                         status ENUM('ongoing', 'completeduser_rolesuser_roles', 'dropped') DEFAULT 'ongoing',
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng genres
CREATE TABLE genres (
                        genre_id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(50) NOT NULL UNIQUE,
                        description TEXT
);

-- Tạo bảng story_genres
CREATE TABLE story_genres (
                              story_id INT,
                              genre_id INT,
                              PRIMARY KEY (story_id, genre_id),
                              FOREIGN KEY (story_id) REFERENCES stories(story_id) ON DELETE CASCADE,
                              FOREIGN KEY (genre_id) REFERENCES genres(genre_id) ON DELETE CASCADE
);

-- Tạo bảng chapters
CREATE TABLE chapters (
                          chapter_id INT PRIMARY KEY AUTO_INCREMENT,
                          story_id INT NOT NULL,
                          chapter_number INT NOT NULL,
                          title VARCHAR(255),
                          content MEDIUMTEXT NOT NULL,
                          audio_file VARCHAR(255),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (story_id) REFERENCES stories(story_id) ON DELETE CASCADE
);

-- Tạo bảng comments
CREATE TABLE comments (
                          comment_id INT PRIMARY KEY AUTO_INCREMENT,
                          user_id INT NOT NULL,
                          story_id INT,
                          chapter_id INT,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                          FOREIGN KEY (story_id) REFERENCES stories(story_id) ON DELETE CASCADE,
                          FOREIGN KEY (chapter_id) REFERENCES chapters(chapter_id) ON DELETE CASCADE
);

-- Tạo bảng favorites
CREATE TABLE favorites (
                           user_id INT,
                           story_id INT,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (user_id, story_id),
                           FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                           FOREIGN KEY (story_id) REFERENCES stories(story_id) ON DELETE CASCADE
);

-- Tạo bảng reading_history
CREATE TABLE reading_history (
                                 history_id INT PRIMARY KEY AUTO_INCREMENT,
                                 user_id INT NOT NULL,
                                 story_id INT NOT NULL,
                                 chapter_id INT NOT NULL,
                                 last_read_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                                 FOREIGN KEY (story_id) REFERENCES stories(story_id) ON DELETE CASCADE,
                                 FOREIGN KEY (chapter_id) REFERENCES chapters(chapter_id) ON DELETE CASCADE
);

-- Tạo bảng roles
CREATE TABLE roles (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- Tạo bảng user_roles
CREATE TABLE user_roles (
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);

-- Thêm chỉ mục để tối ưu tìm kiếm
CREATE INDEX idx_stories_title ON stories(title);
CREATE INDEX idx_story_genres_genre_id ON story_genres(genre_id);
CREATE INDEX idx_chapters_story_id ON chapters(story_id);
CREATE INDEX idx_comments_story_id ON comments(story_id);
CREATE INDEX idx_comments_chapter_id ON comments(chapter_id);
CREATE INDEX idx_favorites_user_id ON favorites(user_id);
CREATE INDEX idx_favorites_story_id ON favorites(story_id);
CREATE INDEX idx_reading_history_user_id ON reading_history(user_id);
CREATE INDEX idx_reading_history_story_id ON reading_history(story_id);