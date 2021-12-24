let login_form = document.getElementById('login-validation');
window.onload = fetch_info;

login_form.addEventListener('submit', async (e) => {
   e.preventDefault();
   e.stopPropagation();

   if(login_form.checkValidity() === true) {
       let response = await  fetch('api/employee/login', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json;charset=utf-8'
           },
           body: JSON.stringify({
               email: document.getElementById('email').value,
               password: document.getElementById('password').value,
               department: document.getElementById('department').value,
           })
       }).then(
           response => {
               return response.json();
           },
           error => {
               document.getElementById('login-alert').style.display = "block";
           }
       ).then (
           responsedata => {

               localStorage.setItem("employee_id", responsedata.employee_id);
               localStorage.setItem("name", responsedata.first_name + " " + responsedata.last_name);
               localStorage.setItem("title", responsedata.title);
               localStorage.setItem("department", responsedata.department);

               if(localStorage.getItem("department").localeCompare("Administration") == 0){
                   location.href = "adminDashboard.html";
               }else if(localStorage.getItem("department").localeCompare("Accounts") == 0){
                   location.href = "accountantDashboard.html";
               }else{
                   location.href = "pageNotFound.html";
               }
           },
           error => {
               console.error("Error Occurred !!");
               document.getElementById('login-alert').style.display = "block";
           }
       );
   }else{
       login_form.classList.add('was-validated');
   }
});


async function fetch_info(){

    let response = await fetch("api/employee/departments");
    let departments = await response.json();
    console.log(departments);
    let department_option = document.getElementById('department');
    department_option.innerHTML = '<option value=""> Choose...</option>';

    for(let i = 0 ; i<departments.length ; i++){
        department_option.innerHTML += '<option value="'+departments[i]+'">'+departments[i]+'</option>';
    }
}

