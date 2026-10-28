import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthShell from "./AuthShell";

function Registration() {
  const [empId, setEmpId] = useState("");
  const [empName, setEmpName] = useState("");
  const [email, setEmail] = useState("");
  const [empDesignation, setEmpDesignation] = useState("");
  const [empStack, setEmpStack] = useState("");
  const [password, setPassword] = useState("");
  const [saving, setSaving] = useState(false);
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  async function sendData(e) {
    e.preventDefault();
    setSaving(true);
    setMessage("");
    try {
      const employee = {
        emp_id: Number(empId),
        emp_name: empName,
        stack: empStack,
        designation: empDesignation,
        email: email,
        password: password,
      };
      const res = await fetch("http://localhost:8080/v1/user/addemp", {
        method: "POST",
        body: JSON.stringify(employee),
        headers: { "Content-Type": "application/json" },
      });
      const text = await res.text();
      setMessage(text);
      if (res.ok) setTimeout(() => navigate("/login"), 1200);
    } catch (err) {
      console.log(err);
      setMessage("Could not reach the server. Check that the backend is running.");
    } finally {
      setSaving(false);
    }
  }

  return (
    <AuthShell
      title="Register employee"
      subtitle="Add a new employee so they can be marked present."
      footer={
        <>
          Already registered? <Link to="/login">Log in</Link>
        </>
      }
    >
      <form onSubmit={sendData}>
        <div className="row-2">
          <div className="field">
            <label htmlFor="empid">Employee ID</label>
            <input id="empid" type="number" min="1" className="input" placeholder="e.g. 101"
              value={empId} onChange={(e) => setEmpId(e.target.value)} required />
          </div>
          <div className="field">
            <label htmlFor="empname">Full name</label>
            <input id="empname" type="text" className="input" placeholder="Full name"
              value={empName} onChange={(e) => setEmpName(e.target.value)} required />
          </div>
        </div>

        <div className="field">
          <label htmlFor="email">Email address</label>
          <input id="email" type="email" className="input" placeholder="name@company.com"
            value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>

        <div className="row-2">
          <div className="field">
            <label htmlFor="designation">Designation</label>
            <input id="designation" type="text" className="input" placeholder="e.g. Developer"
              value={empDesignation} onChange={(e) => setEmpDesignation(e.target.value)} required />
          </div>
          <div className="field">
            <label htmlFor="stack">Tech stack</label>
            <input id="stack" type="text" className="input" placeholder="e.g. Java, React"
              value={empStack} onChange={(e) => setEmpStack(e.target.value)} required />
          </div>
        </div>

        <div className="field">
          <label htmlFor="password">Password</label>
          <input id="password" type="password" className="input" placeholder="Choose a password"
            value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>

        <button type="submit" className="btn btn-primary" style={{ width: "100%" }} disabled={saving}>
          {saving ? "Saving..." : "Register employee"}
        </button>

        {message && <div className="note note-warning">{message}</div>}
      </form>
    </AuthShell>
  );
}

export default Registration;
