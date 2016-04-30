psql -U postgres -d postgres
CREATE DATABASE dev;
CREATE USER dev WITH PASSWORD 'dev';
GRANT ALL PRIVILEGES ON DATABASE dev to dev;