const MockDepartments = [
    {
        "idDepartment": 1,
        "title": "UI design"
    },
    {
        "idDepartment": 2,
        "title": "Frontend testing"
    }
]

describe("Get all departments", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/department/all');
        cy.intercept("GET", "/department/all", MockDepartments).as("AllDepartments");
        
        cy.request("/department/all");
        cy.get("table").should("contain", "UI design").and("contain", "Frontend testing");
    });
});

describe("Get one department", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/department/all/1');
        cy.intercept("GET", "/department/all/1", MockDepartments[0]).as("OneDepartment");
        
        cy.request("/department/all/1")
        cy.get("table").should("contain", "UI design");
    });
});