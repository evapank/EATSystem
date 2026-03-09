import { useState } from "react";

const ProfilePage = () => {
    const [employee, setEmployee] = useState({
            name : '',
            surname : '',
            position : '',
            department : {},
            isManager : false
        });
    }

export default ProfilePage;