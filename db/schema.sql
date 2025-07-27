CREATE TABLE CUSTOMER (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE AGENT (
    agent_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    is_available BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE CATEGORY (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    description TEXT
);

CREATE TABLE AGENT_CATEGORY (
    agent_id INT,
    category_id INT,
    PRIMARY KEY (agent_id, category_id),
    FOREIGN KEY (agent_id) REFERENCES AGENT(agent_id),
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)
);

CREATE TABLE TICKET (
    ticket_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    category_id INT,
    agent_id INT,
    priority ENUM('low', 'medium', 'high', 'urgent'),
    title VARCHAR(500),
    description TEXT,
    status ENUM('open', 'in_progress', 'resolved', 'closed'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    assigned_at TIMESTAMP NULL,
    resolved_at TIMESTAMP NULL,
    closed_at TIMESTAMP NULL,
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER(customer_id),
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id),
    FOREIGN KEY (agent_id) REFERENCES AGENT(agent_id)
);

CREATE TABLE MESSAGE (
    message_id INT PRIMARY KEY AUTO_INCREMENT,
    ticket_id INT,
    sender_type ENUM('CUSTOMER', 'AGENT'),
    sender_id INT,
    content TEXT,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ticket_id) REFERENCES TICKET(ticket_id)
);

CREATE TABLE SLA (
    sla_id INT PRIMARY KEY AUTO_INCREMENT,
    category_id INT,
    max_response_time INT,
    max_resolution_time INT,
    sla_name ENUM('Standard', 'Premium', 'VIP'),
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)
);

CREATE TABLE ESCALATION (
    escalation_id INT PRIMARY KEY AUTO_INCREMENT,
    ticket_id INT,
    escalated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reason TEXT,
    FOREIGN KEY (ticket_id) REFERENCES TICKET(ticket_id)
);

CREATE TABLE FEEDBACK (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    ticket_id INT,
    customer_id INT,
    agent_id INT,
    rating INT,
    comments TEXT,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ticket_id) REFERENCES TICKET(ticket_id),
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER(customer_id),
    FOREIGN KEY (agent_id) REFERENCES AGENT(agent_id)
);

CREATE TABLE AGENT_METRICS (
    agent_id INT PRIMARY KEY,
    total_tickets_resolved INT,
    resolution_rate DECIMAL(5,2),
    avg_handling_time INT,
    last_updated TIMESTAMP,
    FOREIGN KEY (agent_id) REFERENCES AGENT(agent_id)
);

CREATE TABLE TICKET_HISTORY (
    history_id INT PRIMARY KEY AUTO_INCREMENT,
    ticket_id INT,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    priority ENUM('low', 'medium', 'high', 'urgent'),
    status ENUM('open', 'in_progress', 'resolved', 'closed'),
    FOREIGN KEY (ticket_id) REFERENCES TICKET(ticket_id)
);
