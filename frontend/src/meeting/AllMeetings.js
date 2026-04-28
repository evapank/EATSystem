import React, { useState, useEffect } from 'react';
import { MeetingService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';

const AllMeetings = () => {
	const [meeting, setMeetings] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const location = useLocation();
	
	useEffect(() => {
		const fetchMeetings = async () => {
			try {
				const response = await MeetingService.getAll();
				setMeetings(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot find meetings');
				setLoading(false);
				console.log(error);
			}
		};
		fetchMeetings();
	}, []);
	
	const handleDelete = async (id) => {
		const confirm = window.confirm("Record will be deleted");
		if(confirm){
			await MeetingService.delete(id);
			window.location.reload();
		};
	};
	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Meetings</h2>
      <Link to="/meeting/employeestatuses" className="btn btn-success mb-3">
        Add New Meeting
      </Link>
       <div className="row">
        {meeting.length === 0 ? (
          <p>No Meetings found</p>
        ) : (
          meeting.map(meeting => (
            <div>
              <table className="table table-hover">
              	<thead>
					<tr>
              			<th scope="col">ID</th>
              			<th scope="col">Title</th>
					</tr>
              	</thead>
              	<tbody>
					<tr key={meeting.idMeeting}>
						<th scope='row'>{meeting.idMeeting}</th>
                  		<td>{meeting.dateTimeStart}</td>
                        <td>{meeting.dateTimeEnd}</td>
                        <td>{meeting.generalStatus}</td>
                  		<td>
                  			<Link to={`/meeting/all/${meeting.idMeeting}`} className="btn btn-primary">
                   	 		View
                 	 		</Link>
                  		</td>
                  		<td>
                  			<Link to={`/meeting/update/${meeting.idMeeting}`} className="btn btn-secondary">
                   	 		Update
                 	 		</Link>
                  		</td>
                  		<td>
                  			<button onClick={e =>handleDelete(meeting.idMeeting)} className="btn btn-danger">
                   	 		Delete
                 	 		</button>
                  		</td>
					</tr>
                  </tbody>
              </table>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default AllMeetings;