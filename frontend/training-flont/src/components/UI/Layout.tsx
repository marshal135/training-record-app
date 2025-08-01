import React from 'react';

type Props = {
    children: React.ReactNode;
};

const Layout: React.FC<Props> = ({ children }) => {
    return (
        <div className="min-h-screen bg-gray-100 flex flex-col w-screen overflow-x-hidden">
            {/* ヘッダー */}
            <header className="w-full bg-gray-800 text-white py-6 shadow-md">
                <h1 className="text-3xl font-bold text-center">筋トレ記録アプリ</h1>
            </header>

            {/* メインエリア（中央寄せ） */}
            <main className="flex-1 w-full flex justify-center items-center">
                {children}
            </main>
        </div>
    );
};

export default Layout;
