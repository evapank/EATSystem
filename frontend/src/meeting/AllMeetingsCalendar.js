import React, { useState, useEffect } from 'react';
import { MeetingService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';
import { Calendar } from 'react-big-calendar';
import { dateFnsLocalizer } from 'react-big-calendar';
import { format, getDay, parse, startOfWeek } from 'date-fns';
import { enUS } from 'date-fns/locale';

const AllMeetingsCalendar = () => {
	const [meeting, setMeetings] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const location = useLocation();
	const locales = {'en-US': enUS};
	const localizer = dateFnsLocalizer({format, parse, startOfWeek, getDay, locales});
	
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
	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Meetings</h2>
      <Link to="/meeting/employeestatuses" className="btn btn-success mb-3">
        Add New Meeting
      </Link>
      <Calendar localizer={localizer} events={meeting} startAccessor="dateTimeStart" endAccessor="dateTimeEnd" style={{ height: 500 }}/>
    </div>
  );
};

export default AllMeetingsCalendar;