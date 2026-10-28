import { useEffect, useMemo, useState } from "react";
import { Link, NavLink } from "react-router-dom";

const API = "http://localhost:8080/v1/user/attendance";

function formatTime(dateTime) {
  if (!dateTime) return "-";
  const d = new Date(dateTime);
  if (isNaN(d)) return "-";
  return d.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
}

function workedHours(inTime, outTime) {
  if (!inTime || !outTime) return "-";
  const ms = new Date(outTime) - new Date(inTime);
  if (isNaN(ms) || ms < 0) return "-";
  return `${Math.floor(ms / 3600000)}h ${Math.floor((ms % 3600000) / 60000)}m`;
}

function initials(name) {
  if (!name) return "?";
  return name.split(" ").filter(Boolean).slice(0, 2).map((w) => w[0].toUpperCase()).join("");
}

function Dashboard() {
  const [records, setRecords] = useState([]);
  const [empId, setEmpId] = useState("");
  const [comment, setComment] = useState("");
  const [search, setSearch] = useState("");
  const [status, setStatus] = useState("all");
  const [message, setMessage] = useState({ text: "", type: "warning" });
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [now, setNow] = useState(new Date());

  // Live clock
  useEffect(() => {
    const t = setInterval(() => setNow(new Date()), 1000);
    return () => clearInterval(t);
  }, []);

  async function loadToday() {
    try {
      const res = await fetch(`${API}/today`);
      if (!res.ok) throw new Error("Server returned " + res.status);
      const data = await res.json();
      setRecords(Array.isArray(data) ? data : []);
    } catch (err) {
      console.log(err);
      setMessage({
        text: "Could not load attendance. Check that the backend is running on port 8080.",
        type: "danger",
      });
    } finally {
      setLoading(false);
    }
  }

  // Load on open, then refresh every 30 seconds
  useEffect(() => {
    loadToday();
    const t = setInterval(loadToday, 30000);
    return () => clearInterval(t);
  }, []);

  function showResult(text) {
    const ok = text.toLowerCase().includes("successfully");
    setMessage({ text, type: ok ? "success" : "warning" });
  }

  async function markAttendance(e) {
    e.preventDefault();
    setSubmitting(true);
    try {
      const res = await fetch(`${API}/markattendance`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ employee: { emp_id: Number(empId) }, comment }),
      });
      showResult(await res.text());
      setEmpId("");
      setComment("");
      loadToday();
    } catch (err) {
      console.log(err);
      setMessage({ text: "Request failed. Is the backend running?", type: "danger" });
    } finally {
      setSubmitting(false);
    }
  }

  async function checkOut(id) {
    try {
      const res = await fetch(`${API}/checkout/${id}`, { method: "PUT" });
      showResult(await res.text());
      loadToday();
    } catch (err) {
      console.log(err);
      setMessage({ text: "Request failed. Is the backend running?", type: "danger" });
    }
  }

  const total = records.length;
  const checkedOut = records.filter((r) => r.outTime).length;
  const stillIn = total - checkedOut;

  const filtered = useMemo(() => {
    const q = search.trim().toLowerCase();
    return records.filter((r) => {
      if (status === "in" && r.outTime) return false;
      if (status === "out" && !r.outTime) return false;
      if (!q) return true;
      return (
        String(r.employee?.emp_id ?? "").includes(q) ||
        (r.employee?.emp_name ?? "").toLowerCase().includes(q) ||
        (r.employee?.designation ?? "").toLowerCase().includes(q)
      );
    });
  }, [records, search, status]);

  const hh = now.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit", hour12: false });
  const ss = String(now.getSeconds()).padStart(2, "0");

  return (
    <div className="shell">
      <aside className="side">
        <div className="brand">
          <span className="brand-mark">H</span>
          HRMS
        </div>
        <nav className="nav">
          <NavLink to="/dashboard" className={({ isActive }) => (isActive ? "active" : "")}>
            Attendance
          </NavLink>
          <NavLink to="/register" className={({ isActive }) => (isActive ? "active" : "")}>
            Register employee
          </NavLink>
        </nav>
        <nav className="nav nav-bottom">
          <Link to="/login">Sign out</Link>
        </nav>
      </aside>

      <main className="main">
        {/* Clock + summary */}
        <section className="clock">
          <div>
            <div className="clock-time">
              {hh}
              <small>{ss}</small>
            </div>
            <div className="clock-date">
              {now.toLocaleDateString([], {
                weekday: "long",
                day: "numeric",
                month: "long",
                year: "numeric",
              })}
            </div>
          </div>
          <div>
            <div className="figures">
              <div className="figure">
                <b>{total}</b>
                <span>Marked present</span>
              </div>
              <div className="figure">
                <b>{stillIn}</b>
                <span>In office now</span>
              </div>
              <div className="figure">
                <b>{checkedOut}</b>
                <span>Checked out</span>
              </div>
            </div>
            <div className="split" role="img" aria-label={`${stillIn} in office, ${checkedOut} checked out`}>
              <div className="seg-in" style={{ width: total ? `${(stillIn / total) * 100}%` : 0 }} />
              <div className="seg-out" style={{ width: total ? `${(checkedOut / total) * 100}%` : 0 }} />
            </div>
          </div>
        </section>

        {/* Mark attendance */}
        <section className="panel">
          <h3>Mark attendance</h3>
          <form className="mark" onSubmit={markAttendance}>
            <input
              type="number"
              min="1"
              className="input"
              placeholder="Employee ID"
              aria-label="Employee ID"
              value={empId}
              onChange={(e) => setEmpId(e.target.value)}
              required
            />
            <input
              type="text"
              className="input"
              placeholder="Comment (optional)"
              aria-label="Comment"
              value={comment}
              onChange={(e) => setComment(e.target.value)}
            />
            <button type="submit" className="btn btn-primary" disabled={submitting}>
              {submitting ? "Saving..." : "Mark attendance"}
            </button>
          </form>

          {message.text && (
            <div className={`note note-${message.type}`} role="status">
              <span>{message.text}</span>
              <button aria-label="Dismiss" onClick={() => setMessage({ text: "", type: "warning" })}>
                ×
              </button>
            </div>
          )}
        </section>

        {/* Today's table */}
        <section className="panel">
          <div className="toolbar">
            <h3>Today's attendance</h3>
            <div className="controls">
              <input
                type="search"
                className="input"
                placeholder="Search ID, name or role"
                aria-label="Search"
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />
              <select
                className="select"
                aria-label="Filter by status"
                value={status}
                onChange={(e) => setStatus(e.target.value)}
              >
                <option value="all">All</option>
                <option value="in">In office</option>
                <option value="out">Checked out</option>
              </select>
              <button className="btn btn-ghost btn-sm" onClick={loadToday}>
                Refresh
              </button>
            </div>
          </div>

          {loading ? (
            <div className="empty">Loading attendance...</div>
          ) : (
            <div className="table-wrap">
              <table>
                <thead>
                  <tr>
                    <th>Employee</th>
                    <th>Designation</th>
                    <th>In</th>
                    <th>Out</th>
                    <th>Hours</th>
                    <th>Status</th>
                    <th>Comment</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {filtered.length === 0 ? (
                    <tr>
                      <td colSpan="8" className="empty">
                        {records.length === 0
                          ? "No one has marked attendance yet. Enter an employee ID above to start."
                          : "No employees match this search."}
                      </td>
                    </tr>
                  ) : (
                    filtered.map((r) => (
                      <tr key={r.attendanceId}>
                        <td>
                          <div className="person">
                            <span className="avatar">{initials(r.employee?.emp_name)}</span>
                            <div>
                              {r.employee?.emp_name}
                              <small>ID {r.employee?.emp_id}</small>
                            </div>
                          </div>
                        </td>
                        <td>{r.employee?.designation || "-"}</td>
                        <td>{formatTime(r.inTime)}</td>
                        <td>{formatTime(r.outTime)}</td>
                        <td>{workedHours(r.inTime, r.outTime)}</td>
                        <td>
                          {r.outTime ? (
                            <span className="pill pill-out">Checked out</span>
                          ) : (
                            <span className="pill pill-in">In office</span>
                          )}
                        </td>
                        <td>{r.comment || "-"}</td>
                        <td>
                          <button
                            className="btn btn-ghost btn-sm"
                            disabled={!!r.outTime}
                            onClick={() => checkOut(r.employee.emp_id)}
                          >
                            Check out
                          </button>
                        </td>
                      </tr>
                    ))
                  )}
                </tbody>
              </table>
            </div>
          )}
        </section>
      </main>
    </div>
  );
}

export default Dashboard;
