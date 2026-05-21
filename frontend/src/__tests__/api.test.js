import AllDepartments from "../department/AllDepartments";
import { DepartmentService } from "../static/api";
import { mockDepartments } from "./mockValues"

jest.mock("api",() => ({
    get: (url) => {
        if (url.includes("/department")){
            return Promise.resolve({data: mockDepartments});
        }
        return Promise.reject(new Error('api test error occured (get)'));
    }
}));

test("renders a list of departments", async () => {
    DepartmentService.getAll();
    render(<AllDepartments />);
    await waitFor(() => expect(screen.getByText("Sales department")).toBeInTheDocument());
    expect(screen.getByText("Planning department")).toBeInTheDocument();
});