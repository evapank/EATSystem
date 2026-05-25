const MockEmployeeStatuses = [
    {
        "employee": {
            "idEmployee": 1,
            "name": "Carl",
            "surname": "Jefferson",
            "position": "System administrator",
            "department": {
                "idDepartment": 2,
                "title": "IT"
            },
            "email": "cjefferson1234@gmail.com",
            "manager": false
        },
        "generalStatus": "Online",
        "idEmployeeStatus": 1,
        "dateTimeStart": [
            2026,
            15,
            7,
            9,
            0
        ],
        "dateTimeEnd": [
            2026,
            15,
            7,
            17,
            30
        ]
    },
    {
        "employee": {
            "idEmployee": 1,
            "name": "Marie",
            "surname": "Smith",
            "position": "Software engineer",
            "department": {
                "idDepartment": 3,
                "title": "Software engineering"
            },
            "email": "mariesmith@gmail.com",
            "manager": true
        },
        "generalStatus": "InPerson",
        "idEmployeeStatus": 2,
        "dateTimeStart": [
            2026,
            11,
            7,
            8,
            0
        ],
        "dateTimeEnd": [
            2026,
            11,
            7,
            17,
            0
        ]
    }
]

describe("Get all employee statuses", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/employeestatus/all')
        cy.intercept("GET", "/employeestatus/all", MockEmployeeStatuses).as("AllEmployeeStatuses");
        
        cy.request("/employeestatus/all")
        cy.get("table").should("contain", "Online").and("contain", "InPerson")
        .and("contain", "Carl Jefferson").and("contain", "Marie Smith")
    });

});

describe("Get one employee status", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/employeestatus/all/1')
        cy.intercept("GET", "/employeestatus/all/1", MockEmployeeStatuses[0]).as("OneEmployeeStatus");
        
        cy.request("/employeestatus/all/1")
        cy.get("table").should("contain", "Online")
        .and("contain", "Carl Jefferson")
    });

});