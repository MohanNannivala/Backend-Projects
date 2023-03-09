# Development

## Database Setup

#### PostgreSQL

To login to postgres, run the following command:

```bash
psql -U postgres
```

1. Create Database, User and Password

```postgresql
CREATE DATABASE blog;
CREATE USER blog_user WITH ENCRYPTED PASSWORD 'blog_password';
GRANT ALL PRIVILEGES ON DATABASE blog TO blog_user;
```