import React, { useState, useEffect } from 'react';
import { MeetingService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';
import { Calendar } from 'react-big-calendar';
import { dateFnsLocalizer } from 'react-big-calendar';
import { format, getDay, parse, startOfWeek } from 'date-fns';
import { enGB } from 'date-fns/locale';
import moment from 'moment';
import { momentLocalizer } from 'react-big-calendar';
import { localDateTimeArrayToDate } from '../static/DateConverters';

const AllMeetingsCalendar = () => {
	const [meeting, setMeetings] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const location = useLocation();
	//const locales = {'en-GB': enGB};
	//const localizer = dateFnsLocalizer({format, parse, startOfWeek, getDay, locales});
	moment.locale('es', {
    week: {
        dow: 1,
        doy: 1,
    },
});
	const localizer = momentLocalizer(moment);
	
	useEffect(() => {
		const fetchMeetings = async () => {
			try {
				const response = await MeetingService.getAll();
				console.log(response.data);
				const meetingEvents = response.data.map((item) => ({
					start: localDateTimeArrayToDate(item.dateTimeStart),
					end: localDateTimeArrayToDate(item.dateTimeEnd),
					title: item.generalStatus,
				}));
				console.log("meetingEvents: ", meetingEvents);
				setMeetings(meetingEvents);
				console.log("meeting: ", meeting);
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
      <Calendar localizer={localizer} events={meeting} startAccessor="start" endAccessor="end" style={{ height: 500 }}/>
    </div>
  );
};

export default AllMeetingsCalendar;