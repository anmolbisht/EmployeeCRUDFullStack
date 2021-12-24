let employee_form = document.getElementById('employee-validation');
window.onload = fetch_info;

employee_form.addEventListener('submit', async (e) =>{
    e.preventDefault();
    e.stopPropagation();

    if(employee_form.checkValidity() === true){
        let response = await fetch('api/employee/addEmployee', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                first_name: document.getElementById('first_name').value,
                last_name: document.getElementById('last_name').value,
                email: document.getElementById('email').value,
                password: document.getElementById('password').value,
                title: document.getElementById('title').value,
                department: document.getElementById('department').value,
            })
        }).then(
            response => {
                if(response['status'] === 203){
                    document.getElementById('login-success').style.display = "none";
                    document.getElementById('login-alert').style.display = "block";
                }else{
                    document.getElementById('login-success').style.display = "block";
                    document.getElementById('login-alert').style.display = "none";
                }
            }
        );
    }else{
        employee_form.classList.add('was-validated')
    }
});


async function fetch_info(){

    let response = await fetch("api/employee/titles");
    let titles = await response.json(); // read response body and parse as JSON
    console.log(titles);
    let title_option = document.getElementById('title');
    title_option.innerHTML = '<option value=""> Choose...</option>';

    for(let i = 0 ; i<titles.length ; i++){
        title_option.innerHTML += '<option value="'+titles[i]+'">'+titles[i]+'</option>';
    }


    response = await fetch("api/employee/departments");
    let departments = await response.json(); // read response body and parse as JSON
    console.log(departments);
    let department_option = document.getElementById('department');
    department_option.innerHTML = '<option value=""> Choose...</option>';

    for(let i = 0 ; i<departments.length ; i++){
        department_option.innerHTML += '<option value="'+departments[i]+'">'+departments[i]+'</option>';
    }
}

