import { useNavigate } from "react-router-dom";
import { UserService } from "./api";
import { useState } from "react";

const SignupPage = () => {
    const [username, setUserame] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [role, setRole] = useState('EMPLOYEE');
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleSignup = async () => {
        try {
           if (!username || !email || !password || !confirmPassword) {
                setError('Please fill in all fields.');
                return;
           }
           if (password !== confirmPassword) {
            throw new Error("Passwords do not match");
           }
           const response = UserService.signUp(username, email, password, role);
           localStorage.setItem("token", response.data.jwt);
           navigate('/dashboard');
        } catch (error){
            console.error('Signup failed:', error.response ? error.response.data : error.message);
            setError(error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{width: '600px', height: 'auto'}}>
                <div className="p-3">
                    <h2 className="mb-4 text-center">Sign Up Page</h2>
                    {/* Render error message if exists */}
                    {error && <p className="text-danger">{error}</p>}
                    <input className='form-control mb-3' id='username' placeholder={"Username"} value={username} type='text'
                              onChange={(e) => setUserame(e.target.value)}/>
                    <input className='form-control mb-3' id='name' placeholder={"Name"} value={name} type='text'
                              onChange={(e) => setName(e.target.value)}/>
                    <input className='form-control mb-3' id='surname' placeholder={"Surname"} value={surname} type='text'
                              onChange={(e) => setSurname(e.target.value)}/>
                    <input className='form-control mb-3' placeholder='Email Address' id='email' value={email} type='email'
                              onChange={(e) => setEmail(e.target.value)}/>
                    <input className='form-control mb-3' placeholder='Password' id='password' type='password' value={password}
                              onChange={(e) => setPassword(e.target.value)}/>
                    <input className='form-control mb-3' placeholder='Confirm Password' id='confirmPassword' type='password'
                              value={confirmPassword}
                              onChange={(e) => setConfirmPassword(e.target.value)}/>

                    <label className="form-label mb-1">Role:</label>
                    <select className="form-select mb-4" value={role} onChange={(e) => setRole(e.target.value)}>
                        <option value="EMPLOYEE">Employee</option>
                        <option value="ADMIN">Admin</option>
                        <option value="PROJECT_MANAGER">Project manager</option>
                        <option value="DEPARTMENT_MANAGER">Department manager</option>
                    </select>
                    <button className="mb-4 d-block mx-auto fixed-action-btn btn-primary"
                            style={{height: '40px', width: '100%'}}
                            onClick={handleSignup}>Sign Up
                    </button>

                    <div className="text-center">
                        <p>Already Registered? <a href="/">Login</a></p>
                    </div>

                </div>
            </div>
        </div>
    );
}
export default SignupPage;