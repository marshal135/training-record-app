import React, { useState } from 'react';
import axios from 'axios';
import Button from './UI/Botton';

interface Props {
    switchToLogin: () => void;
}

/**
 * ユーザー登録フォームコンポーネント
 *
 * ユーザー名・メールアドレス・パスワードを入力して新規登録を行う。
 * 登録後はメッセージを表示し、ログイン画面への切り替えも可能。
 */
const RegisterForm: React.FC<Props> = ({ switchToLogin }) => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleRegister = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage('');
        try {
            await axios.post('http://localhost:8080/user', {
                username,
                email,
                password,
            });
            setMessage('登録に成功しました！ログインしてください');
        } catch (err) {
            setMessage('登録に失敗しました');
        }
    };

    return (
        <form
            onSubmit={handleRegister}
            className="bg-white p-6 rounded-lg shadow-md w-full max-w-md mx-auto mt-10 space-y-4"
        >
            <h2 className="text-2xl font-bold text-center">新規登録</h2>

            <input
                type="text"
                placeholder="ユーザー名"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            />
            <input
                type="email"
                placeholder="メールアドレス"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            />
            <input
                type="password"
                placeholder="パスワード"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            />

            {message && (
                <p className="text-sm text-center text-green-600">{message}</p>
            )}

            {/* 登録ボタン*/}
            <Button
                type="submit"
                className="bg-blue-600 hover:bg-blue-700"
            >
                登録
            </Button>

            <p className="text-sm text-center text-gray-600">
                すでにアカウントをお持ちの方は{' '}
                <Button
                    type="button"
                    onClick={switchToLogin}
                    className="text-blue-600 font-semibold hover:underline w-fit mx-auto bg-transparent shadow-none"
                >
                    ログイン
                </Button>
            </p>
        </form>

    );
};

export default RegisterForm;
