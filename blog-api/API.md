# Blogging App

## JSON Entities

### User

```bash
{
    "id": 31,
    "username": "mohan",
    "email":	"mohan@blog.com",
    "password": "xxxxxxxx",
    "authToken": "dakjghadlghadlghladhgkgdklgladkgjadlkgd"
    "bio": 		"writes really good articles!",
    "image":	"https://imgur.com/ahkbtqe.png"
}
```

### Profile

```bash
{
    "username": "mohan",
    "bio": 		"writes really good articles!",
    "image":	"https://imgur.com/ahkbtqe.png"
}
```

### Article

```bash
{
    "id": 134,
    "title": "How the stock market fell in 2023",
    "slug": "how-stock-market-fell-2023"
    "subtitle": "An article about how the stock market had a crash in 2022",
    "body"	: "This is an article about ..... <b>stock market</b> .... <i>2022</i> .........",
    "createdAt":  "2023-03-15 03:40:55",
    "tags"	: ["finance", "stocks"]
}
```

### Comment

    {
	    "id": 1344,
	    "title": "great article",
	    "body" : "this was a great article, loved reading it!",
	    "createdAt: "2023-03-15 03:40:55"
    }

### Errors
```
{
    "message": "User with username: mohan123 not found"
}
```

## API Endpoints

### `POST /users`
create a new user

### `POST /users/login`

### `GET /profiles`📄

### `GET /profiles/{username}`


### `GET /articles` 📄
get all articles (default page size 10)
available filters

- `/articles?tag=stocks`
- `/articles?author=mohan`
- `/articles?page=3&size=10`

### `GET /articles/{article-slug}`

### `POST /articles` 🔐
create a new article

### `PATCH /articles/{article-slug}` 🔐👤
edit an article


### `GET /article/{article-slug}/comments` 📄
get all comments of an article

### `POST /article/{article-slug}/comments` 🔐

### `DELETE /article/{article-slug}/comments/{comment-id}` 🔐👤