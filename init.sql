CREATE EXTENSION IF NOT EXISTS vector;

-- 1. BẢNG NGƯỜI DÙNG
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(255),
    avatar_url TEXT,

    -- Cấu hình phục vụ OAuth2 Google
    provider VARCHAR(50) DEFAULT 'GOOGLE',    -- 'GOOGLE' hoặc 'LOCAL'
    provider_id VARCHAR(255) UNIQUE,          -- Lưu Sub ID (chuỗi số định danh) từ Google gửi về

    -- Quản lý phân hạng tài khoản và giới hạn nâng cao
    account_role VARCHAR(50) DEFAULT 'FREE',  -- 'FREE', 'PREMIUM', 'ADMIN'
    premium_expires_at TIMESTAMP,              -- Ngày hết hạn Premium (NULL nếu là tài khoản FREE)

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. BẢNG GIỚI HẠN UPLOAD THEO NGÀY
CREATE TABLE user_upload_limits (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    upload_date DATE DEFAULT CURRENT_DATE,    -- Chỉ lưu ngày (yyyy-MM-dd) để dễ đối chiếu
    upload_count INT DEFAULT 0,               -- Đếm số lần đã upload trong ngày hôm đó
    CONSTRAINT unique_user_date UNIQUE (user_id, upload_date) -- Ràng buộc: Mỗi ngày 1 user chỉ có 1 dòng dữ liệu
);

-- 3. BẢNG LỊCH SỬ THANH TOÁN (Nâng cấp Premium)
CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE SET NULL, -- User bị xóa thì vẫn giữ lại lịch sử dòng tiền để kế toán đối soát
    order_code VARCHAR(100) UNIQUE NOT NULL,             -- Mã đơn hàng
    amount DECIMAL(10, 2) NOT NULL,                      -- Số tiền thanh toán thành công
    status VARCHAR(50) DEFAULT 'PENDING',                -- Trạng thái: 'PENDING', 'SUCCESS', 'FAILED'
    package_month INT DEFAULT 1,                         -- Số tháng mua (1 tháng, 6 tháng, 12 tháng...)
    paid_at TIMESTAMP,                                   -- Thời điểm cổng thanh toán gọi webhook báo thành công
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. BẢNG PHIÊN TRÒ CHUYỆN (Chat Session)
CREATE TABLE chat_sessions (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) DEFAULT 'Đoạn chat mới',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. BẢNG LỊCH SỬ TIN NHẮN
CREATE TABLE chat_messages (
    id SERIAL PRIMARY KEY,
    session_id INT REFERENCES chat_sessions(id) ON DELETE CASCADE,
    sender VARCHAR(50) NOT NULL,              -- Giá trị định danh: 'USER' hoặc 'AI'
    message_text TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 6. BẢNG TÀI LIỆU/GIÁO TRÌNH
CREATE TABLE documents (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE, -- Tác giả upload tài liệu này
    title VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 7. BẢNG CÁC ĐOẠN CHUNK NHỎ
CREATE TABLE lessons (
    id SERIAL PRIMARY KEY,
    document_id INT REFERENCES documents(id) ON DELETE CASCADE,
    chunk_index INT NOT NULL,
    content TEXT NOT NULL,
    embedding vector(768),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

+
CREATE INDEX idx_lessons_embedding_cosine ON lessons USING hnsw (embedding vector_cosine_ops);
CREATE INDEX idx_lessons_doc_chunk ON lessons(document_id, chunk_index);
CREATE INDEX idx_user_upload_limits_date ON user_upload_limits(user_id, upload_date);
CREATE INDEX idx_chat_messages_session_id ON chat_messages(session_id);
CREATE INDEX idx_chat_sessions_created_at ON chat_sessions(created_at DESC);
CREATE INDEX idx_documents_user_id ON documents(user_id);