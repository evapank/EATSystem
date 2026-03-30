import { MeetingService } from "../static/api";

const NewMeeting = () => {
    const [employeeStatuses, setEmployeeStatuses] = useState([]);
    const [dateTime, setDateTime] = useState({});

    const onChangeDateTime = async (dateTime) => {
        const response = await MeetingService.getEmployeeStatuses(dateTime);
        setDateTime(response.data);
    };
}
export default NewMeeting;