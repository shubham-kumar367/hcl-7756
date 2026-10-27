import React, { useState } from 'react'

function Registration()
{
    const [empId, setEmpId] = useState();
    const [EmpName, setEmpName] = useState();
    const [EmpDesignation, setEmpDesignation] = useState();
    const [EmpStack, setEmpStack] = useState();
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();


    async function sendData(e) {
        try {
          const employee = {
          emp_id: Number(empId),
          emp_name: EmpName,
          stack: EmpStack,
          designation: EmpStack,
          email: email,
          password: password
}
          e.preventDefault();
          const server = await fetch("http://localhost:8080/v1/user/addemp",{
            method:'Post',
            body:JSON.stringify(employee),
            headers:{"Content-Type":"application/json"}
          });
          const status = await server.text();
          alert(status);
        }
        catch(e) {
          console.log(e);
        }
    }


  return (
    <div>
      <form>
        <div className="form-group">
          <label htmlFor="empid">Empid</label>
          <input type="number" onChange={(e) => setEmpId(e.target.value)} className="form-control" id="empid" placeholder="Enter emp_id" required />
          
        </div>

        <div className="form-group">
          <label htmlFor="empname">Name</label>
          <input type="text" onChange = {(e) => setEmpName(e.target.value)} className="form-control" id="empname" placeholder="Enter emp_name" required />
          
        </div>

        <div className="form-group">
          <label htmlFor="email">Email address</label>
          <input type="email"  onChange = {(e) => setEmail(e.target.value)} className="form-control" id="email" placeholder="Enter emp_email" required />
          <small className="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>

        <div className="form-group">
          <label htmlFor="designation">Designation</label>
          <input type="text"  onChange = {(e) => setEmpDesignation(e.target.value)} className="form-control" id="designation" placeholder="Enter emp_designation" required />
        </div>

        <div className="form-group">
          <label htmlFor="designation">Tech Stack</label>
          <input type="text"  onChange = {(e) => setEmpStack(e.target.value)} className="form-control" id="designation" placeholder="Enter emp_techStack" required />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input type="password"  onChange = {(e) => setPassword(e.target.value)}className="form-control" id="password" placeholder='Enter emp_password'required />
        </div>

        <br />

        <button type="submit" onClick={sendData} className="btn btn-primary">Register</button>
      </form>
    </div>
  )
}

export default Registration