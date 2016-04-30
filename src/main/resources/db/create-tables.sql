
CREATE TABLE kinks (
	id VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    category VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    
    PRIMARY KEY (id),
    UNIQUE(name,category)
);

CREATE TABLE kinksters (
	id VARCHAR(100) NOT NULL,
    nickname VARCHAR(100) NOT NULL,
    group_id VARCHAR(100) NOT NULL,
    gender VARCHAR(100) NOT NULL,
    orientation VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    
    PRIMARY KEY (id),
    UNIQUE(nickname,group_id)
);

CREATE TABLE acknowledged_kinks (
	kink_id VARCHAR(100) NOT NULL,
    kinkster_id VARCHAR(100) NOT NULL,
    interest_level VARCHAR(100) NOT NULL,
    direction VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    
    PRIMARY KEY (kink_id,kinkster_id),
    FOREIGN KEY (kink_id) REFERENCES kinks (id),
    FOREIGN KEY (kinkster_id) REFERENCES kinksters (id)
);