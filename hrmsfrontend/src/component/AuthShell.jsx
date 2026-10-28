function AuthShell({ title, subtitle, children, footer }) {
  return (
    <div className="auth">
      <aside className="auth-side">
        <div className="brand">
          <span className="brand-mark">H</span>
          HRMS
        </div>
        <div>
          <h2>Every working day, on the record.</h2>
          <p>
            Mark attendance, check people out and see who is in the office,
            all in one place.
          </p>
        </div>
        <span style={{ color: "#aeb9d6", fontSize: 13 }}>
          Attendance management
        </span>
      </aside>

      <main className="auth-main">
        <div className="auth-card">
          <h1>{title}</h1>
          {subtitle && <p className="sub">{subtitle}</p>}
          {children}
          {footer && <p className="auth-foot">{footer}</p>}
        </div>
      </main>
    </div>
  );
}

export default AuthShell;
