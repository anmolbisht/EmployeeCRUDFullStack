window.onload = fetch_info()

let salaryForm = document.getElementById('submitSalary')
let salaryRecordsForm = document.getElementById('submitSalaryRecord')
var  salList;

async function fetch_info() {
    // Yet to Disburse Salary
    employees = await  fetch('api/employee/getEmployees', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            employee_id: localStorage.getItem("employee_id"),
        })
    }).then(
        response => {
            return response.json();
        }
    );

    let empDisplay = document.getElementById('employee-search');
    console.log(employees);

    for(let i = 0; i < employees.length; i++) {
        empDisplay.innerHTML += '<tr> ' +
            '<td> ' +
            '<div class="custom-control custom-checkbox">' +
            ' <input type="checkbox" class="custom-control-input" id="' + employees[i].employee_id + '"> ' +
            '<label class="custom-control-label" for="' + employees[i].employee_id + '"> ' +
            ' </label>' +
            ' </div> ' +
            ' </td>' +
            '<td>' + employees[i].first_name + '</td>' +
            '<td>' + employees[i].last_name + '</td>' +
            '<td>' + employees[i].department + '</td>' +
            '<td>' + employees[i].email + '</td>' +
            '<td>' + employees[i].title + '</td> ' +
            '<td> ' +
            '<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#individualDisbursePopup" onclick="fetchSalaryRecords(this.id)" id="but' + employees[i].employee_id + '">Disburse</button>' +
            '</td>' +
            '</tr>';
    }


    // Already Disbursed Salary

    let alreadyDisbursed = await fetch('api/employeesalary/alreadyDisbursedSalary');
    let alreadyDisbursedSal = await alreadyDisbursed.json();
    console.log(alreadyDisbursedSal);

    let empSalDisplay = document.getElementById('Already-Salary-Table');

    console.log(alreadyDisbursedSal.length);

    for(let i = 0; i < alreadyDisbursedSal.length; i++) {

        empSalDisplay.innerHTML += '<tr> ' +
            '<td>' + alreadyDisbursedSal[i].employee.first_name + '</td>' +
            '<td>' + alreadyDisbursedSal[i].employee.last_name + '</td>' +
            '<td>' + alreadyDisbursedSal[i].employee.email + '</td>' +
            '<td>' + alreadyDisbursedSal[i].employee.department + '</td>' +
            '<td>' + alreadyDisbursedSal[i].employee.title + '</td> ' +
            '<td>' + alreadyDisbursedSal[i].amount + '</td> ' +
            '<td>' + alreadyDisbursedSal[i].description + '</td> ' +
            '</tr>';
    }

}

salaryForm.onclick = async function(){
    if(salaryForm.checkValidity() === true) {
        let checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        let ids = [];
        let sal = document.getElementById('amount').value;
        let des = document.getElementById('description').value;

        checkboxes.forEach((checkbox) => {
            var employee = {};
            employee["employee_id"] = checkbox.id;

            var employee_sal = {};
            employee_sal["employee"] = employee
            employee_sal["amount"] = sal
            employee_sal["description"] = des
            ids.push(employee_sal);
        });

        let response = await fetch('api/employeesalary/addsalary', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(ids)
        }).then(
            response => {
                if(response['status'] === 200){
                    console.log("Koi problem nahi hain maze karo.");
                    return response.json();
                }
                else {
                    console.log("Kuchh to problem hain.");
                }
            }
        );

    }else{
        salaryForm.classList.add('was-validated')
    }
};

async function fetchSalaryRecords(id) {

    var emp_id = id.slice(3);

    salList = await fetch('api/employeesalary/getSalaryRecords', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            employee_id: emp_id,
        })
    }).then(
        response => {
            return response.json();
        }
    );

    console.log(salList);

    document.getElementById("individualDisburseFormTable").innerHTML = "";
    let salDisplay = document.getElementById('individualDisburseFormTable');

    salDisplay.innerHTML = '';

    for(let i = 0; i < salList.length; i++) {
        salDisplay.innerHTML += '<tr> ' +
            '<td> ' +
            ' <div> ' +
            '<input type="radio" name="salaryRecord" value="'+ i +'" id="' + salList[i].id + '"> ' +
            '<label for="' + salList[i].id + '"> ' +
            ' </label>' +
            ' </div> ' +
            ' </td>' +
            '<td>' + salList[i].amount + '</td>' +
            '<td>' + salList[i].description + '</td>' +
            '<td>' + JSON.stringify(salList[i].payment_date).slice(1,11) + '</td>' +
            '</tr>';
    }

}


salaryRecordsForm.onclick = async function(){


    let radioBtnValue = document.querySelector('input[type="radio"]:checked').value;


    let response = await fetch('api/employeesalary/addSalaryByRecord', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                employee: {employee_id : salList[radioBtnValue].employee.employee_id},
                amount: salList[radioBtnValue].amount,
                description: salList[radioBtnValue].description,
            })
        }).then(
            response => {
                if(response['status'] === 200){
                    console.log("Koi problem nahi hain maze karo.");
                    return response.json();
                }
                else {
                    console.log("Kuchh to problem hain.");
                }
            }
        );

};

