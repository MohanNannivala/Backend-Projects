 # Task Manager App
 
## JSON Entities

### Task

    {
        "id": 1,
        "title": "I am a task!",
        "description": "This is the task description",
        "dueDate": null,
        "status": "COMPLETED",
        "createdAt": "YYYY-MM-DD",
        "notes": [
                    {
                        "id": 2,
                        "body": "I am the note body"
                    },
                    {
                        "id": 3,
                        "body": "I am the note body"
                    }
                ]
    }

## API Endpoints

### `POST /tasks`
Create a new task

### `GET /tasks`
Get all tasks Available filters :
- `/tasks/title?title=I am a task!`
- `/tasks/status?status=COMPLETED/NOT_STARTED/WORK_IN_PROGRESS`

### `GET /tasks/{task_id}`
Get the details of a particular task including notes

### `PATCH /tasks/{task_id}`
Edit the details of a particular task.

### `DELETE /tasks/{task_id}`
Delete a particular task

### `GET  /tasks/{task_id}/notes`
Fetch all the notes under a particular task

### `POST  /tasks/{task_id}/notes`
Create a new note under the task with given task id

### `DELETE /tasks/{task_id}/notes/{notes_id}`
Delete a note