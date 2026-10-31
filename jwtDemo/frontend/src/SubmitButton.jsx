import React from 'react'
import { useFormStatus } from 'react-dom'

const SubmitButton = () => {
    const { pending } = useFormStatus()

    return (
        <button
            disabled={pending}
            type="submit"
            className={`w-full px-6 py-3 rounded-lg font-semibold text-white shadow-md transition duration-300 flex items-center justify-center gap-2
            ${pending
                ? 'bg-gray-400 cursor-not-allowed'
                : 'bg-indigo-600 hover:bg-indigo-700 active:scale-95 cursor-pointer'
            }`}
        >
            {pending ? (
                <>
                    <span className="w-5 h-5 border-2 border-white border-t-transparent rounded-full animate-spin"></span>
                    Submitting Securely...
                </>
            ) : (
                'Submit'
            )}
        </button>
    )
}

export default SubmitButton