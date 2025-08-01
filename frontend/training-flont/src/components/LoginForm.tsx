import React, { useState } from 'react';
import axios from 'axios';
import Button from './UI/Botton';

interface Props {
    onSuccess: () => void;
    switchToRegister: () => void;
}

/**
 * ログインフォームコンポーネント
 *
 * ユーザーのログイン処理と状態管理を行う
 */
const LoginForm: React.FC<Props> = ({ onSuccess, switchToRegister }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage('');

        try {
            const response = await axios.post('http://localhost:8080/user/login', {
                email,
                password,
            });

            const { userId, email: returnedEmail, username } = response.data;
            localStorage.setItem('userId', String(userId));
            localStorage.setItem('userEmail', returnedEmail);
            localStorage.setItem('username', username);
            onSuccess();
        } catch {
            setMessage('ログインに失敗しました');
        }
    };

    return (
        <form
            onSubmit={handleLogin}
            className="w-full max-w-md space-y-4"
        >
            <h2 className="text-2xl font-bold text-center text-gray-800">ログイン</h2>

            <input
                type="email"
                placeholder="メールアドレス"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="w-full px-4 py-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-gray-500"
            />

            <input
                type="password"
                placeholder="パスワード"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="w-full px-4 py-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-gray-500"
            />

            {message && (
                <p className="text-sm text-center text-red-600">{message}</p>
            )}

            {/* 共通ボタンでログイン */}
            <Button type="submit">ログイン</Button>

            <p className="text-sm text-center text-gray-600">
                アカウントがない方は{' '}
                <Button
                    type="button"
                    onClick={switchToRegister}
                    className="text-red-600 font-semibold hover:underline w-fit mx-auto bg-transparent shadow-none"
                >
                    新規登録
                </Button>
            </p>
        </form>
    );
};

export default LoginForm;
