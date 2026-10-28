import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthShell from "./AuthShell";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  function handleLogin(e) {
    e.preventDefault();
    // TODO: call your backend login endpoint here and check the result
    // before navigating. For now this goes straight to the dashboard.
    navigate("/dashboard");
  }

  return (
    <AuthShell
      title="Log in"
      subtitle="Use your work email and password."
      footer={
        <>
          New employee? <Link to="/register">Register here</Link>
        </>
      }
    >
      <form onSubmit={handleLogin}>
        <div className="field">
          <label htmlFor="loginEmail">Email address</label>
          <input
            id="loginEmail"
            type="email"
            className="input"
            placeholder="name@company.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="field">
          <label htmlFor="loginPassword">Password</label>
          <input
            id="loginPassword"
            type="password"
            className="input"
            placeholder="Your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary" style={{ width: "100%" }}>
          Log in
        </button>
      </form>
    </AuthShell>
  );
}

export default Login;
