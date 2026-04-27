const NewMeetingAdd = () => {

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
            </form>
        </div>
    )
};

export default NewMeetingAdd;