
import { useActionState } from "react";
import SubmitButton from "./SubmitButton";
import api from "./api";

const PostForm = ({ onCreated }) => {

  const submitAction = async (prev, formData) => {

    const title = formData.get("title")?.trim();
    const content = formData.get("content")?.trim();

    if (!title || !content) {
      return {
        error: "Title and content are required",
        msg: "",
        title: title || "",
        content: content || "",
      };
    }

    try {
      await api.post("/posts", { title, content });

      await onCreated?.();

      return {
        error: null,
        msg: "Post created successfully!",
        title: "",
        content: "",
      };

    } catch (error) {
      return {
        error:
          error.response?.data?.error ||
          error.response?.data?.message ||
          "Failed to create post. Please try again.",
        msg: "",
        title,
        content,
      };
    }
  };

  const [state, formAction, isPending] = useActionState(
    submitAction,
    {
      error: null,
      msg: "",
      title: "",
      content: "",
    }
  );

  return (
    <div className="w-full bg-gradient-to-br from-slate-50 via-indigo-50 to-purple-50 py-10 px-4">

      <div className="max-w-2xl mx-auto">

        {/* Heading */}

        <div className="text-center mb-8">

          <div className="inline-flex items-center justify-center w-16 h-16 bg-indigo-600 rounded-2xl shadow-lg shadow-indigo-200 mb-4">
            <span className="text-3xl">✍️</span>
          </div>

          <h1 className="text-3xl font-extrabold text-gray-900">
            Create a Post
          </h1>

          <p className="text-gray-500 mt-2">
            Share your thoughts and ideas with the community.
          </p>

        </div>

        {/* Form Card */}

        <div className="bg-white rounded-3xl shadow-xl border border-gray-100 p-6 sm:p-10">

          <h3 className="text-xl font-bold text-gray-800 mb-2">
            Write Something
          </h3>

          <p className="text-sm text-gray-500 mb-7">
            Fill in the details below to publish your post.
          </p>

          <form action={formAction} className="space-y-6">

            {/* Title */}

            <div>

              <label
                htmlFor="title"
                className="block text-sm font-semibold text-gray-700 mb-2"
              >
                Post Title
              </label>

              <input
                type="text"
                name="title"
                id="title"
                placeholder="Enter an interesting title..."
                defaultValue={state.title}
                required
                disabled={isPending}
                className="w-full px-4 py-3 rounded-xl border border-gray-300 bg-gray-50 text-gray-800 placeholder-gray-400 outline-none transition duration-200 focus:bg-white focus:border-indigo-500 focus:ring-4 focus:ring-indigo-100 disabled:opacity-60"
              />

            </div>

            {/* Content */}

            <div>

              <label
                htmlFor="content"
                className="block text-sm font-semibold text-gray-700 mb-2"
              >
                Post Content
              </label>

              <textarea
                name="content"
                id="content"
                placeholder="What would you like to share today?"
                rows={6}
                defaultValue={state.content}
                required
                disabled={isPending}
                className="w-full px-4 py-3 rounded-xl border border-gray-300 bg-gray-50 text-gray-800 placeholder-gray-400 outline-none resize-y transition duration-200 focus:bg-white focus:border-indigo-500 focus:ring-4 focus:ring-indigo-100 disabled:opacity-60"
              />

            </div>

            {/* Error Message */}

            {state.error && (
              <div
                role="alert"
                className="flex items-center gap-2 bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded-xl text-sm"
              >
                <span>⚠️</span>
                {state.error}
              </div>
            )}

            {/* Success Message */}

            {state.msg && (
              <div
                role="status"
                className="flex items-center gap-2 bg-green-50 border border-green-200 text-green-700 px-4 py-3 rounded-xl text-sm"
              >
                <span>✓</span>
                {state.msg}
              </div>
            )}

            {/* Submit Button */}

            <div className="pt-2">
              <SubmitButton />
            </div>

          </form>

        </div>

        {/* Footer */}

        <p className="text-center text-sm text-gray-500 mt-6">
          Your ideas matter. Start sharing today!
        </p>

      </div>

    </div>
  );
};

export default PostForm;
