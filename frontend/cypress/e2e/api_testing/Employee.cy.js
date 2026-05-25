const MockEmployees = [
     {
        "idEmployee": 1,
        "name": "Jessica",
        "surname": "Johnnson",
        "position": "Frontend tester",
        "department": {
            "idDepartment": 2,
            "title": "Frontend testing"
        },
        "email": "jjohnnson123@gmail.com",
        "manager": true
    },
    {
        "idEmployee": 2,
        "name": "Angelica",
        "surname": "Smith",
        "position": "Junior UI designer",
        "department": {
            "idDepartment": 1,
            "title": "UI design"
        },
        "email": "angelica.smith@gmail.com",
        "manager": false
    }
]

describe("Get all employees", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/employee/all')
        cy.intercept("GET", "/employee/all", MockEmployees).as("AllEmployees");
        
        cy.request("employee/all")
        cy.get("table").should("contain", "Jessica").and("contain", "Angelica")
    });

});

describe("Get one employee", ()=> {
    it("Intercept request", ()=>{
        cy.visit(`http://localhost:3000/employee/all/${MockEmployees[0].idEmployee}`)
        cy.intercept("GET", `/employee/all/${MockEmployees[0].idEmployee}`, MockEmployees[0]).as("OneEmployee");
        
        cy.request(`/employee/all/${MockEmployees[0].idEmployee}`)
        cy.get("table").should("contain", "Jessica").and("contain", "Johnnson")
    });

});