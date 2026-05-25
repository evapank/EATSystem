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
        cy.visit(`http://localhost:3000/department/all/${MockDepartments[0].idDepartment}`);
        cy.intercept("GET", `/department/all/${MockDepartments[0].idDepartment}`, MockDepartments[0]).as("OneDepartment");
        
        cy.request(`/department/all/${MockDepartments[0].idDepartment}`)
        cy.get("table").should("contain", "UI design");
    });
});