import React, { useState } from 'react';
import LoginForm from '../components/LoginForm';
import RegisterForm from '../components/RegisterForm';
import { useNavigate } from 'react-router-dom';
import Layout from '../components/UI/Layout';

/**
 * ログイン・新規登録画面の切り替えを管理するページコンポーネント
 * 
 * フォームは LoginForm / RegisterForm を条件に応じて表示
 */

const AuthPage: React.FC = () => {
    const [isLogin, setIsLogin] = useState(true);
    const navigate = useNavigate();

    return (
        <Layout>
            <div className="bg-white p-8 rounded-xl shadow-xl w-full max-w-md border border-gray-300">
                {isLogin ? (
                    <LoginForm
                        onSuccess={() => navigate('/workout')}
                        switchToRegister={() => setIsLogin(false)}
                    />
                ) : (
                    <RegisterForm switchToLogin={() => setIsLogin(true)} />
                )}
            </div>
        </Layout>
    );
};

export default AuthPage;
