import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";


function Welcome() {

    const [credential, setCredential] = useState({email: '', password: ''});

    const navigate = useNavigate();

    const credentialChangeHandler = (event) => {
        const name = event.target.name;
        const value = event.target.value;

        const tempCredential = {...credential};
        tempCredential[name] = value;
        setCredential(tempCredential);
    };

    const signInSubmitHandler = () => {
        axios.post('http://localhost:8080/login', credential).then(response => {
            navigate("/home", {replace: true});
        }).catch(error => {
            console.log("Sign in error.")
        });
    }

    return (
        <div className="container">
            <div className="border rounded">
                <form className="mx-3 my-2">
                    <div className="form-group">
                        <label for="inputEmail">Email</label>
                        <input type="email" name="email" onChange={credentialChangeHandler} value={credential.email} className="form-control" id="inputEmail" placeholder="example@email.com" />
                        
                    </div>
                    <div className="form-group">
                        <label for="inputPassword">Password</label>
                        <input type="password" name="password" onChange={credentialChangeHandler} value={credential.password} className="form-control" id="inputPassword" placeholder="Password" />
                    </div>
                    <button type="button" className="btn btn-primary mb-2" onClick={signInSubmitHandler}>Sign In</button>
                    <small id="emailHelp" className="form-text text-muted">Not a user? Sign up <Link to="/sign-up"> here</Link>.</small>
                </form>
            </div>
        </div>
    );
}
  
export default Welcome;