const MockEos = [
     {
        "employee": {
            "idEmployee": 1,
            "name": "Sam",
            "surname": "Smith",
            "position": "Accountant",
            "department": {
                "idDepartment": 2,
                "title": "Accounting"
            },
            "email": "sam_smith@gmail.com",
            "manager": true
        },
        "generalStatus": "Online",
        "idEmployeeOrderStatus": 1
    },
    {
        "employee": {
            "idEmployee": 1,
            "name": "Noel",
            "surname": "Jordan",
            "position": "Accounting assistant",
            "department": {
                "idDepartment": 2,
                "title": "Accounting"
            },
            "email": "noeljordan1@gmail.com",
            "manager": false
        },
        "generalStatus": "InPerson",
        "idEmployeeOrderStatus": 2
    }
]

describe("Get all employee order statuses", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/employeeorderstatus/all')
        cy.intercept("GET", "/employeeorderstatus/all", MockEos).as("AllEos");
        
        cy.request("/employeeorderstatus/all")
        cy.get("table").should("contain","Online").and("contain", "InPerson")
        .and("contain", "Sam Smith").and("contain", "Noel Jordan")
    });

});

describe("Get one employee order status", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/employeeorderstatus/all/1')
        cy.intercept("GET", "/employeeorderstatus/all/1", MockEos[0]).as("OneEos");
        
        cy.request("/employeeorderstatus/all")
        cy.get("table").should("contain","Online")
        .and("contain", "Sam Smith")
    });

});