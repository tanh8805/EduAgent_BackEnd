-- 1. Bảng lưu tài liệu giáo trình/bài học
CREATE TABLE lessons (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Bảng lưu phiên trò chuyện
CREATE TABLE chat_sessions (
                               id SERIAL PRIMARY KEY,
                               title VARCHAR(255) DEFAULT 'Đoạn chat mới',
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Bảng lưu lịch sử tin nhắn trong phiên chat
CREATE TABLE chat_messages (
                               id SERIAL PRIMARY KEY,
                               session_id INT REFERENCES chat_sessions(id) ON DELETE CASCADE,
                               sender VARCHAR(50) NOT NULL, -- 2 giá trị: 'USER'/ 'AI'
                               message_text TEXT NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);