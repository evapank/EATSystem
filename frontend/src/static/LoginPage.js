import { useState } from "react"
import { UserService } from "./api";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();

     const handleLogin = async () => {
        try {
            if (!username || !password) {
                setError('Please enter both username and password.');
                return;
            }
            const response = await UserService.logIn(username, password);
            console.log('Login successful:', response.data);
            if(response.data.role==='EMPLOYEE'){
                navigate(`/auth/user/employee/${response.data.id}`);
           }else{
                navigate('/dashboard');
           }
        } catch (error) {
            console.error('Login failed:', error.response ? error.response.data : error.message);
            setError('Invalid username or password.');
        }
    };
    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{ width: '500px', height: 'auto' }}>
                <div className=" p-3">
                    <h2 className="mb-4 text-center">Login Page</h2>
                    <input className='form-control mb-4' placeholder='Username' id='username' value={username} type='username' onChange={(e) => setUsername(e.target.value)} />
                    <input className='form-control mb-4' placeholder='Password' id='password' type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
                    {error && <p className="text-danger">{error}</p>}
                    <button className="mb-4 d-block btn-primary" style={{ height:'50px',width: '100%' }} onClick={handleLogin}>Log in</button>
                    <div className="text-center">
                        <p>Don't have an account? <a href="/auth/signup" >Register</a></p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginPage;
