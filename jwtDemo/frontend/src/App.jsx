import { useState } from "react";
import Authform from "./Authform";
import PostForm from "./PostForm";

const App = () => {

    const [username, setUsername] = useState(
        localStorage.getItem("username") || ""
    );

    const [isLoggedIn, setIsLoggedIn] = useState(
        !!localStorage.getItem("token")
    );

    const handleLogin = (user) => {
        setUsername(user);
        setIsLoggedIn(true);
    };

    const handleLogout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("username");

        setUsername("");
        setIsLoggedIn(false);
    };

    return (
        <div className="min-h-screen bg-gradient-to-br from-slate-100 via-indigo-50 to-purple-100">

            {/* Navbar */}

            <nav className="bg-gradient-to-r from-indigo-700 to-purple-700 shadow-lg">

                <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">

                    <h1 className="text-2xl font-extrabold text-white">
                        Auth<span className="text-indigo-200">Secure</span>
                    </h1>

                    {isLoggedIn && (
                        <div className="flex items-center gap-4">

                            <span className="text-white font-medium hidden sm:block">
                                Welcome, {username}
                            </span>

                            <button
                                onClick={handleLogout}
                                className="bg-white text-indigo-700 px-5 py-2 rounded-lg font-semibold hover:bg-indigo-100 transition duration-300"
                            >
                                Logout
                            </button>

                        </div>
                    )}

                </div>

            </nav>

            {/* Main Content */}

            <main className="max-w-7xl mx-auto px-4 py-10">

                {!isLoggedIn ? (

                    <Authform onLogin={handleLogin} />

                ) : (

                    <div>

                        <div className="text-center mb-8">

                            <h2 className="text-3xl font-bold text-gray-900">
                                Welcome to Your Dashboard
                            </h2>

                            <p className="text-gray-600 mt-2">
                                Create and share your posts with the community.
                            </p>

                        </div>

                        <PostForm />

                    </div>

                )}

            </main>

            {/* Footer */}

            <footer className="text-center py-6 text-gray-500 text-sm">
                © 2026 AuthSecure | React + Tailwind CSS + Spring Boot
            </footer>

        </div>
    );
};

export default App;
