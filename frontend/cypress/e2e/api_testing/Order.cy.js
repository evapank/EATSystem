const MockOrders = [
{
        "idOrder": 1,
        "orderNumber": 123455,
        "project": null,
        "orderDate": [2026, 8, 28],
        "dateTimeStart": [2026, 11, 3, 8, 0],
        "dateTimeEnd": [2026, 11, 7, 17, 0],
        "orderStatus": "Vacation",
        "employeeOrderStatus": {
            "employee": {
                "idEmployee": 1,
                "name": "Chloe",
                "surname": "Anderson",
                "position": "Software engineer",
                "department": {
                    "idDepartment": 2,
                    "title": "Software engineering"
                },
                "email": "chloe.anderson@gmail.com",
                "manager": true
            },
            "generalStatus": "Online",
            "idEmployeeOrderStatus": 1
        }
    },
    {
        "idOrder": 2,
        "orderNumber": 2929292,
        "project": {
            "idProject": 2,
            "projectNumber": 20006,
            "title": "Social media website testing",
            "dateStart": [
                2026,
                2,
                9
            ],
            "dateEnd": [
                2026,
                4,
                16
            ],
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
        "orderDate": [2025, 10, 20],
        "dateTimeStart": [2025, 1, 12, 9, 0],
        "dateTimeEnd": [2025, 5, 12, 12, 0],
        "orderStatus": "BusinessTrip",
        "employeeOrderStatus": null
    }
];

describe("Get all orders", ()=> {
    it("Intercept request", ()=>{
        cy.visit('http://localhost:3000/order/all')
        cy.intercept("GET", "/order/all", MockOrders).as("AllOrders");
        
        cy.request("/order/all")
        cy.get("table").should("contain", "Social media website testing").and("contain", 123455)
    });

});

describe("Get one order", ()=> {
    it("Intercept request", ()=>{
        cy.visit(`http://localhost:3000/order/all/${MockOrders[0].idOrder}`)
        cy.intercept("GET", `/order/all/${MockOrders[0].idOrder}`, MockOrders[0]).as("OneOrder");
        
        cy.request(`/order/all/${MockOrders[0].idOrder}`)
        cy.get("table").should("contain", "Vacation").and("contain", 123455)
    });

});