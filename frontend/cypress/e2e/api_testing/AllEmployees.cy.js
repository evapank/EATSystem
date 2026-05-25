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
        cy.visit('http://localhost:3000/employee/all/1')
        cy.intercept("GET", "/employee/all/1", MockEmployees[0]).as("OneEmployee");
        
        cy.request("employee/all/1")
        cy.get("table").should("contain", "Jessica").and("contain", "Johnnson")
    });

});