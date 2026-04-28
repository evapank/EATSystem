import { MeetingService } from "../static/api";

const NewMeetingAdd = () => {
    const [meeting, setMeeting] = useState({
            dateTimeStart : '',
            dateTimeEnd : '',
            generalStatus: ''
        });
    const [statusArray, setStatusArray] = useState([]);
    const [employeeStatuses, setEmployeeStatuses] = useState([]);
    const [generalStatuses, setGeneralStatuses] = useState([]);
    const [employees, setEmployees] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    
    useEffect(() => {
                const fetchMeeting = async() => {
                    try {
                        const response = await OtherService.getGeneralStatus();
                        console.log(response.data);
                        setGeneralStatuses(response.data);
                        setLoading(false);
                    } catch (error){
                        setError('cannot find meeting');
                        setLoading(false);
                        console.log(error);
                    }
                };
                fetchMeeting();
        }, []);

        const handleSubmit = async (e) => {
           e.preventDefault();
            try{
                await MeetingService.create(meeting);
                navigate('/meeting/all');
            } catch (error) {
                console.error('Submit error: ', error);
            }
       };
 


        const onChangeEmployees = (employee) => {
            setEmployees(previousEmployee => ({
                employees: [...previousEmployee.employees, employee]
            }));
        };

    return (
         <div className="container mt-4">
            <h2>Create new meeting</h2>
            <form action="@{/meeting/create}" object={meeting} method="post" onSubmit={handleSubmit}>
                <div>
                    <label>Employees:</label>
                    <select multiple={true} options={employeeStatuses} name='employees' className='form-control' onChange={onChangeEmployees}>
                        <option value=''>-- Select Employee --</option>
                                {employeeStatuses.map(e => (
                                    <option key={employeeStatuses.employee.idEmployee} value={employeeStatuses.employee.idEmployee}>{e.employee.name} {e.employee.surname} / {e.generalStatus}</option>
                                ))};
                    </select>
                </div>
                <div>
                <label>General status:</label>
                    <select options={generalStatuses} name='generalStatus' className='form-control' onChange= {e => setMeeting({...meeting, generalStatus: e.target.value})}>
                                <option value=''>-- Select status --</option>
                                {generalStatuses.map((e, x) => (
                                    <option key={x} value={x}>{e}</option>
                                ))};
                            </select>
                </div>
                <button type='submit' className="btn btn-success mb-3">Submit</button>
            </form>
        </div>
    )
};

export default NewMeetingAdd;