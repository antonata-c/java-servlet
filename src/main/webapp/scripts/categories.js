function openCreateCategoryModal() {
    document.getElementById('createCategoryModal').style.display = 'block';
}

function closeCreateCategoryModal() {
    document.getElementById('createCategoryModal').style.display = 'none';
    document.getElementById('createCategoryForm').reset();
}

window.onclick = function (event) {
    let createModal = document.getElementById('createCategoryModal');
    let editModal = document.getElementById('editCategoryModal');
    if (event.target === createModal) {
        createModal.style.display = 'none';
        document.getElementById('createCategoryForm').reset();
    }
    else if (event.target === editModal) {
        editModal.style.display = 'none';
        document.getElementById('editCategoryForm').reset();
    }
};


function submitCategoryForm(event) {
    event.preventDefault();
    let form = document.getElementById('createCategoryForm');
    let formData = {
        name: document.getElementById('name').value
    };
    console.log(formData)

    fetch('/category', {
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


function deleteCategory(categoryId) {
    console.log(categoryId)

    fetch(`/category?id=${categoryId}`, {
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

document.getElementById('createCategoryForm').addEventListener('submit', submitCategoryForm);

function openEditCategoryModal(categoryId,name) {
    document.getElementById("edit-category-id").value = categoryId;
    document.getElementById("edit-name").value = name;
    document.getElementById('editCategoryModal').style.display = 'block';
}

function closeEditCategoryModal() {
    document.getElementById('editCategoryModal').style.display = 'none';
    document.getElementById('editCategoryForm').reset();
}

function submitEditCategoryForm(event) {
    event.preventDefault();

    let form = document.getElementById('editCategoryForm');
    let formData = {
        id:document.getElementById("edit-category-id").value,
        name: document.getElementById('edit-name').value,
    };
    console.log(formData)

    fetch('/category', {
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
document.getElementById('editCategoryForm').addEventListener('submit', submitEditCategoryForm);

