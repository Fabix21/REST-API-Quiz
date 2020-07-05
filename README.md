# REST_API_Quiz
Web Quiz engine, using Spring and REST API.
Uses embedded H2 database to store data and Basic Auth to provide Security.

## Running application

Clone it from repo and build with gradle.
* Building app
```
./gradlew build
```
* Running app
```
./gradlew bootEun
```
By deafult, quiz runs on port `8889` using `localhost` and h2 database `quizdb` in user's home directory.
## Description
API supports 
* registrating users.
* authenticate users by email and password.
* creating new quzzies by user.
* deleteing quzzes only by user who created it.
* getting quizzes by id or getting all quizzes.
* solving quizzes by passing answers in JSON format.

To perform any operations with quizzes **(create, solve, get one, get all, delete)**, the user has to be registered and then authorized via **HTTP Basic Auth** by sending their email and password for each request. Otherwise, the service returns the a`401 (Unauthorized)` status code. Thus, the only operation that does not require authorization is the registration.

## Register a new user

To register a new user, the client needs to send a JSON with `email` and `password` via 'POST' request to `/api/register`:
```
{
  "email": "test@gmail.com",
  "password": "secret"
}
```
If the email is already taken by another user, the service will return `HTTP 400`.

Here are some additional restrictions to the format of user credentials:
- an email must have a valid format (with `@` and `.`);
- password must have at least **five** characters.

If any of them are not satisfied, the service will also return `HTTP 400`.

## Create a new quiz

To create a new quiz, you need to send a JSON via `POST` request with the following keys: 
- `title`: string, required;
- `text`: string, required;
- `options`: an array of strings, it's required, and should contain at least 2 items; 
- `answer`: an array of indexes of correct options, it's optional since all options can be wrong.

Also at this point you have to be authenticated by HTTP Basic Auth with account createad via POST request to `/api/register`.

Here is a new JSON quiz as an example:


```
{
  "title": "Coffee drinks",
  "text": "Select only coffee drinks.",
  "options": ["Americano","Tea","Cappuccino","Sprite"],
  "answer": [0,2]
}
```

The response contains the same JSON with generated `id`, but does not include `answer`.
```json
{
  "id": 1,
  "title": "Coffee drinks",
  "text": "Select only coffee drinks.",
  "options": ["Americano","Tea","Cappuccino","Sprite"]
}
```

If the request JSON does not contain `title` or `text`, or they are empty strings (`""`), then the response is `404`.
If the number of options in the quiz is less than 2, the response is `404` as well.

## Get a quiz by id
To get a quiz by `id`, the client sends the `GET` request to `/api/quizzes/{id}`.

Here is a response example:
```
{
  "id": 1,
  "title": "The Java Logo",
  "text": "What is depicted on the Java logo?",
  "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
}
```
If the specified quiz does not exist, the server should return the 1404 (Not found)1 status code.

## Get all quizzes
To get all existing quizzes in the service, the client sends the `GET` request to `/api/quizzes`.

The response contains a JSON array of quizzes like the following:
```
[
  {
    "id": 1,
    "title": "The Java Logo",
    "text": "What is depicted on the Java logo?",
    "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
  },
  {
    "id": 2,
    "title": "The Ultimate Question",
    "text": "What is the answer to the Ultimate Question of Life, the Universe and Everything?",
    "options": ["Everything goes right","42","2+2=4","11011100"]
  }
]
```
## Solving a quiz
To solve the quiz, the client sends a POST request to `/api/quizzes/{id}/solve` and passes the `answer` parameter in the content. This parameter is the index of a chosen option from options array.

The service returns a JSON with two fields: `success` (`true` or `false`) and feedback (just a string). There are three possible responses.

If the passed answer is correct (e.g., `POST` to `/api/quizzes/1/solve` with content `answer=2`):
`{"success":true,"feedback":"Congratulations, you're right!"}`
If the answer is incorrect (e.g., POST to /api/quizzes/1/solve with content `answer=1`):
`{"success":false,"feedback":"Wrong answer! Please, try again."}`
If the specified quiz does not exist, the server returns the `404 (Not found)` status code.

## Delete a quiz
A user can delete their quiz by sending the `DELETE` request to `/api/quizzes/{id}`.

If the operation was successful, the service returns the `204 (No content)` status code without any content.

If the specified quiz does not exist, the server returns `404 (Not found)`.

## Credits
Idea for this project was taken from JetBrains Academy. 
> https://hyperskill.org/projects/91?track=1
