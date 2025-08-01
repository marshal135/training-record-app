// src/components/ui/Button.tsx

import React from 'react';

type Props = {
    children: React.ReactNode;
    type?: 'button' | 'submit' | 'reset';
    onClick?: () => void;
    className?: string;
};

const Button: React.FC<Props> = ({
    children,
    type = 'button',
    onClick,
    className = '',
}) => {
    return (
        <button
            type={type}
            onClick={onClick}
            className={`w-full py-3 text-base bg-gray-800 hover:bg-gray-900 text-black font-semibold rounded-lg shadow-md transition duration-200 ${className}`}
        >
            {children}
        </button>
    );
};

export default Button;
