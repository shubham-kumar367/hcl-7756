import { Link } from "react-router-dom";
import AuthShell from "./AuthShell";

function Home() {
  return (
    <AuthShell
      title="Welcome back"
      subtitle="Sign in to open today's attendance, or add a new employee."
    >
      <div className="stack">
        <Link to="/login" className="btn btn-primary">
          Log in
        </Link>
        <Link to="/register" className="btn btn-ghost">
          Register employee
        </Link>
      </div>
    </AuthShell>
  );
}

export default Home;
