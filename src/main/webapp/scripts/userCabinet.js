function openCreateExpenseModal() {
    document.getElementById('createExpenseModal').style.display = 'block';
}

function closeCreateExpenseModal() {
    document.getElementById('createExpenseModal').style.display = 'none';
    document.getElementById('createExpenseForm').reset();
}

window.onclick = function (event) {
    let createModal = document.getElementById('createExpenseModal');
    let editModal = document.getElementById('editExpenseModal');
    if (event.target === createModal) {
        createModal.style.display = 'none';
        document.getElementById('createExpenseForm').reset();
    }
    else if (event.target === editModal) {
        editModal.style.display = 'none';
        document.getElementById('editExpenseForm').reset();
    }
};


function submitExpenseForm(event) {
    event.preventDefault();
    let form = document.getElementById('createExpenseForm');
    let formData = {
        userId: document.getElementById('user_id').value,
        amount: document.getElementById('amount').value,
        source: document.getElementById('source').value,
        categoryId: document.getElementById('category_id').value
    };
    console.log(formData)

    fetch('/expense', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.error);
                });
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('message').innerHTML = 'Запись успешно добавлена! '
            setTimeout(() => location.reload(),1000)
        })
        .catch(error => {
            console.error('Error:', error.message);
            console.log('Server Error:', error.message);
            document.getElementById('message').innerHTML = 'Произошла ошибка! ' +error.message
        });

}


function deleteExpense(expenseId) {
    console.log(expenseId)

    fetch(`/expense?id=${expenseId}`, {
        method: 'DELETE',
    })
        .then(response => response.text())
        .then(data => {
            location.reload()
        })
        .catch(error => {
            console.error('Error:', error);
        })
}

document.getElementById('createExpenseForm').addEventListener('submit', submitExpenseForm);

function openEditExpenseModal(expenseId,amount,source) {
    document.getElementById("edit-expense-id").value = expenseId;
    document.getElementById("edit-amount").value = amount;
    document.getElementById("edit-source").value = source;
    document.getElementById('editExpenseModal').style.display = 'block';
}

function closeEditExpenseModal() {
    document.getElementById('editExpenseModal').style.display = 'none';
    document.getElementById('editExpenseForm').reset();
}

function submitEditExpenseForm(event) {
    event.preventDefault();

    let form = document.getElementById('createExpenseForm');
    let formData = {
        userId: document.getElementById('user_id').value,
        id:document.getElementById("edit-expense-id").value,
        amount: document.getElementById('edit-amount').value,
        source: document.getElementById('edit-source').value,
        categoryId: document.getElementById('edit-category-id').value
    };
    console.log(formData)

    fetch('/expense', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.error);
                });
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('edit-message').innerHTML = 'Изменения сохранены! '
            setTimeout(() => location.reload(),1000)
        })
        .catch(error => {
            console.error('Error:', error.message);
            console.log('Server Error:', error.message);
            document.getElementById('edit-message').innerHTML = 'Произошла ошибка! ' +error.message
        });
}
document.getElementById('editExpenseForm').addEventListener('submit', submitEditExpenseForm);

