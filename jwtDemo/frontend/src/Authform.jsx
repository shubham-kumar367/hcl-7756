
import { useState, useActionState } from "react";
import api from "./api";
import SubmitButton from "./SubmitButton";

const AuthForm = ({ onLogin }) => {
    const [mode, setMode] = useState("login");

    const submitAction = async (prevState, formData) => {
        const username = formData.get("username")?.trim();
        const password = formData.get("password")?.trim();

        if (!username || !password) {
            return {
                error: "Username and password are required",
                msg: "",
                username: username || ""
            };
        }

        try {
            if (mode === "register") {
                await api.post("/auth/register", {
                    username,
                    password
                });

                setMode("login");

                return {
                    error: null,
                    msg: "User registered successfully! Please login.",
                    username
                };
            }

            const { data } = await api.post("/auth/login", {
                username,
                password
            });

            if (!data.token) {
                throw new Error("Authentication token not received");
            }

            const loggedInUser = data.username || username;

            localStorage.setItem("token", data.token);
            localStorage.setItem("username", loggedInUser);

            onLogin?.(loggedInUser);

            return {
                error: null,
                msg: "Login successful!",
                username: loggedInUser
            };

        } catch (error) {
            return {
                error:
                    error.response?.data?.error ||
                    error.response?.data?.message ||
                    error.message ||
                    "Something went wrong. Please try again.",
                msg: "",
                username
            };
        }
    };

    const [state, formAction, isPending] = useActionState(
        submitAction,
        {
            error: null,
            msg: "",
            username: ""
        }
    );

    return (
        <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-slate-950 via-indigo-950 to-purple-950 px-4 py-10">

            <div className="w-full max-w-md">

                {/* Logo and Heading */}

                <div className="text-center mb-8">

                    <div className="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-indigo-500 shadow-lg shadow-indigo-500/30 mb-4">
                        <span className="text-3xl text-white font-bold">
                            A
                        </span>
                    </div>

                    <h1 className="text-3xl font-extrabold text-white tracking-tight">
                        Auth<span className="text-indigo-400">Secure</span>
                    </h1>

                    <p className="text-gray-400 mt-2">
                        Secure authentication for your account
                    </p>

                </div>

                {/* Authentication Card */}

                <div className="bg-white rounded-3xl shadow-2xl p-8 sm:p-10">

                    <div className="mb-7">

                        <h2 className="text-2xl font-bold text-gray-900">
                            {mode === "login"
                                ? "Welcome Back!"
                                : "Create Account"}
                        </h2>

                        <p className="text-gray-500 text-sm mt-2">
                            {mode === "login"
                                ? "Enter your credentials to access your account."
                                : "Fill in your details to get started."}
                        </p>

                    </div>

                    {/* Success Message */}

                    {state.msg && (
                        <div
                            role="status"
                            className="bg-green-50 border border-green-200 text-green-700 rounded-xl p-3 mb-5 text-sm"
                        >
                            {state.msg}
                        </div>
                    )}

                    {/* Error Message */}

                    {state.error && (
                        <div
                            role="alert"
                            className="bg-red-50 border border-red-200 text-red-600 rounded-xl p-3 mb-5 text-sm"
                        >
                            {state.error}
                        </div>
                    )}

                    {/* Authentication Form */}

                    <form action={formAction} className="space-y-5">

                        {/* Username */}

                        <div>

                            <label
                                htmlFor="username"
                                className="block text-sm font-semibold text-gray-700 mb-2"
                            >
                                Username
                            </label>

                            <input
                                type="text"
                                name="username"
                                id="username"
                                placeholder="Enter your username"
                                defaultValue={state.username}
                                required
                                disabled={isPending}
                                className="w-full px-4 py-3 rounded-xl border border-gray-300 bg-gray-50 text-gray-900 placeholder-gray-400 outline-none transition duration-200 focus:border-indigo-500 focus:ring-4 focus:ring-indigo-100 disabled:opacity-60"
                            />

                        </div>

                        {/* Password */}

                        <div>

                            <label
                                htmlFor="password"
                                className="block text-sm font-semibold text-gray-700 mb-2"
                            >
                                Password
                            </label>

                            <input
                                type="password"
                                name="password"
                                id="password"
                                placeholder="Enter your password"
                                required
                                disabled={isPending}
                                className="w-full px-4 py-3 rounded-xl border border-gray-300 bg-gray-50 text-gray-900 placeholder-gray-400 outline-none transition duration-200 focus:border-indigo-500 focus:ring-4 focus:ring-indigo-100 disabled:opacity-60"
                            />

                        </div>

                        {/* Submit Button */}

                        <div className="pt-2">
                            <SubmitButton />
                        </div>

                    </form>

                    {/* Switch Login/Register */}

                    <div className="mt-7 text-center border-t border-gray-100 pt-6">

                        <p className="text-sm text-gray-600">

                            {mode === "login"
                                ? "Don't have an account?"
                                : "Already have an account?"}

                            <button
                                type="button"
                                disabled={isPending}
                                onClick={() => {
                                    setMode(
                                        mode === "login"
                                            ? "register"
                                            : "login"
                                    );
                                }}
                                className="ml-2 font-bold text-indigo-600 hover:text-indigo-800 transition duration-200 disabled:opacity-50"
                            >
                                {mode === "login"
                                    ? "Create Account"
                                    : "Login"}
                            </button>

                        </p>

                    </div>

                </div>

                {/* Footer */}

                <p className="text-center text-gray-400 text-xs mt-7">
                    © 2026 AuthSecure. All rights reserved.
                </p>

            </div>

        </div>
    );
};

export default AuthForm;
