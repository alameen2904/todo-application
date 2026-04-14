const SERVER_URL = "http://localhost:8081";

// ---------------- AUTH ----------------
async function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const res = await fetch(`${SERVER_URL}/auth/login`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({email, password})
    });

    const data = await res.json();

    if (data.token) {
        localStorage.setItem("token", data.token);
        window.location.href = "todos.html";
    } else {
        alert("Login Failed");
    }
}

async function register() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const res = await fetch(`${SERVER_URL}/auth/register`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({email, password})
    });

    if (res.ok) {
        alert("Registered successfully!");
        window.location.href = "login.html";
    }
}

// ---------------- LOGOUT ----------------
function logout() {
    localStorage.removeItem("token");
    window.location.href = "login.html";
}

// ---------------- TODOS ----------------
async function loadTodos() {
    const token = localStorage.getItem("token");
    if (!token) {
        window.location.href = "login.html";
        return;
    }

    const res = await fetch(`${SERVER_URL}/api/v1/todo`, {
        headers: { "Authorization": `Bearer ${token}` }
    });

    const todos = await res.json();
    const list = document.getElementById("todo-list");
    list.innerHTML = "";

    if (!todos.length) {
        list.innerHTML = "<p>No tasks yet</p>";
        return;
    }

    todos.forEach(todo => {
        const card = document.createElement("div");
        card.className = `todo-card ${todo.isCompleted ? 'completed' : ''}`;

        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.className = "todo-checkbox";
        checkbox.checked = todo.isCompleted;

        const span = document.createElement("span");
        span.textContent = todo.title;

        checkbox.addEventListener("change", async () => {
            await updateTodo(todo.id, span.textContent, checkbox.checked);
        });

        const deleteBtn = document.createElement("button");
        deleteBtn.textContent = "X";
        deleteBtn.className = "delete-btn";

        deleteBtn.onclick = async () => {
            await deleteTodo(todo.id);
        };

        card.appendChild(checkbox);
        card.appendChild(span);
        card.appendChild(deleteBtn);

        list.appendChild(card);
    });
}

async function updateTodo(id, title, status) {
    const token = localStorage.getItem("token");

    await fetch(`${SERVER_URL}/api/v1/todo`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ id, title, isCompleted: status })
    });

    await loadTodos();
}

async function addTodo() {
    const input = document.getElementById("new-todo");
    const token = localStorage.getItem("token");

    if (!input.value.trim()) return;

    await fetch(`${SERVER_URL}/api/v1/todo/create`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ title: input.value, isCompleted: false })
    });

    input.value = "";
    await loadTodos();
}

async function deleteTodo(id) {
    const token = localStorage.getItem("token");

    await fetch(`${SERVER_URL}/api/v1/todo/${id}`, {
        method: "DELETE",
        headers: { "Authorization": `Bearer ${token}` }
    });

    await loadTodos();
}

// Enter key support
document.addEventListener("DOMContentLoaded", () => {
    if (document.getElementById("todo-list")) loadTodos();

    const input = document.getElementById("new-todo");
    if (input) {
        input.addEventListener("keypress", (e) => {
            if (e.key === "Enter") addTodo();
        });
    }
});