const API = "http://localhost:8080/api/todos";
const taskList = document.getElementById("taskList");
const taskInput = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");

async function fetchTasks() {
  const res = await fetch(API);
  const tasks = await res.json();
  renderTasks(tasks);
}

function renderTasks(tasks) {
  taskList.innerHTML = "";
  if (tasks.length === 0) {
    taskList.innerHTML =
      '<p class="text-gray-500 text-center">No hay tareas aún</p>';
    return;
  }

  tasks.forEach((task) => {
    const li = document.createElement("li");
    li.className =
      "flex justify-between items-center bg-white shadow rounded-lg px-4 py-2 mb-2";

    const span = document.createElement("span");
    span.textContent = task.title;
    span.className =
      "cursor-pointer " + (task.done ? "line-through text-gray-400" : "");
    span.onclick = () => toggleTask(task.id);

    const btn = document.createElement("button");
    btn.textContent = "✕";
    btn.className = "text-red-500 hover:text-red-700 font-bold";
    btn.onclick = () => deleteTask(task.id);

    li.appendChild(span);
    li.appendChild(btn);
    taskList.appendChild(li);
  });
}

addBtn.onclick = async () => {
  const title = taskInput.value.trim();
  if (!title) return;
  await fetch(API, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title }),
  });
  taskInput.value = "";
  fetchTasks();
};

async function toggleTask(id) {
  await fetch(`${API}/${id}`, { method: "PATCH" });
  fetchTasks();
}

async function deleteTask(id) {
  await fetch(`${API}/${id}`, { method: "DELETE" });
  fetchTasks();
}

fetchTasks();
