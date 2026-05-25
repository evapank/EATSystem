const MockProjects = [
    {
        "idProject": 1,
        "projectNumber": 23232,
        "title": "Mobile app testing",
        "dateStart": [2026, 4, 10],
        "dateEnd": [2026, 4, 20],
        "projectManager": {
            "idEmployee": 1,
            "name": "John",
            "surname": "Smith",
            "position": "Software engineer",
            "department": {
                "idDepartment": 2,
                "title": "Software engineering"
            },
            "email": "jsmith@gmail.com",
            "manager": true
        }
    },
    {
        "idProject": 2,
        "projectNumber": 20006,
        "title": "Budget planning",
        "dateStart": [2026, 1, 2],
        "dateEnd": [2026, 1, 12],
        "projectManager": {
            "idEmployee": 1,
            "name": "John",
            "surname": "Smith",
            "position": "Software engineer",
            "department": {
                "idDepartment": 2,
                "title": "Software engineering"
            },
            "email": "jsmith@gmail.com",
            "manager": true
        }
    }
]

describe("Get all projects", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/project/all')
        cy.intercept("GET", "/project/all", MockProjects).as("AllProjects");
        
        cy.request("/project/all")
        cy.get("table").should("contain", "Mobile app testing").and("contain","Budget planning")
    });

});

describe("Get one project", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/project/all/1')
        cy.intercept("GET", "/project/all/1", MockProjects[0]).as("OneProject");
        
        cy.request("/project/all/1")
        cy.get("table").should("contain", "Mobile app testing")
    });

});