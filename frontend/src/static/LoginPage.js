import { useState } from "react"
import { UserService } from "./api";

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

     const handleLogin = async () => {
        try {
            if (!username || !password) {
                setError('Please enter both username and password.');
                return;
            }
            const response = await UserService.logIn({...username, password: password});
            console.log('Login successful:', response.data);
            history('/dashboard');
        } catch (error) {
            console.error('Login failed:', error.response ? error.response.data : error.message);
            setError('Invalid username or password.');
        }
    };
    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{ width: '500px', height: 'auto' }}>
                <container className="p-3">
                    <h2 className="mb-4 text-center">Login Page</h2>
                    <input wrapperClass='mb-4' placeholder='Email address' id='email' value={username} type='email' onChange={(e) => setUsername(e.target.value)} />
                    <input wrapperClass='mb-4' placeholder='Password' id='password' type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
                    {error && <p className="text-danger">{error}</p>} {/* Render error message if exists */}
                    <button className="mb-4 d-block btn-primary" style={{ height:'50px',width: '100%' }} onClick={handleLogin}>Sign in</button>
                    <div className="text-center">
                        <p>Not a member? <a href="/signup" >Register</a></p>
                    </div>
                </container>
            </div>
        </div>
    );
}
