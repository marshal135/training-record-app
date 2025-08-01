import React, { useState } from "react";
import { useNavigate, Navigate } from "react-router-dom";
import WorkoutForm from "../components/WorkoutForm";
import WorkoutList from "../components/WorkoutList";
import { useWorkout } from "../hooks/useWorkout";
import type { WorkoutRequestDTO } from "../types/workoutTypes";

/**
 * トレーニング記録を扱うメインページ
 *
 * 登録フォームと一覧表示を切り替えて使える画面
 */

const WorkoutPage: React.FC = () => {
    const navigate = useNavigate();
    const storedUserId = localStorage.getItem("userId");
    const username = localStorage.getItem("username");

    // 未ログインならログイン画面へ
    if (!storedUserId) {
        return <Navigate to="/" />;
    }

    const userId = parseInt(storedUserId, 10);
    const today = new Date();
    const [selectedYear, setSelectedYear] = useState(today.getFullYear());
    const [selectedMonth, setSelectedMonth] = useState(today.getMonth() + 1);

    const {
        workouts,
        loading,
        error,
        addWorkout,
        removeWorkout
    } = useWorkout(userId, selectedYear, selectedMonth);

    const [view, setView] = useState<"form" | "list">("form");

    const handleAddWorkout = async (data: Omit<WorkoutRequestDTO, "userId">) => {
        await addWorkout({ ...data, userId });
        setView("list");
    };

    const handleLogout = () => {
        localStorage.removeItem("userId");
        navigate("/");
    };

    return (
        <div className="w-screen min-h-screen bg-gray-100 flex items-center justify-center px-4">
            <div className="w-full max-w-5xl bg-white rounded-xl shadow-md p-8">
                {/* ヘッダー */}
                <div className="flex items-center justify-between mb-6">
                    <div className="flex items-baseline gap-4">
                        <h1 className="text-2xl font-bold text-gray-800">トレーニング記録</h1>
                        <p className="text-gray-700">{username} さん、ようこそ！</p>
                    </div>
                    <button
                        onClick={handleLogout}
                        className="bg-gray-800 text-black px-4 py-2 rounded hover:bg-gray-900 transition"
                    >
                        ログアウト
                    </button>
                </div>

                {/* 表示切り替えボタン */}
                <div className="flex gap-4 mb-6">
                    <button
                        onClick={() => setView("form")}
                        className="bg-blue-600 text-black px-4 py-2 rounded hover:bg-blue-700 transition"
                    >
                        トレーニング登録
                    </button>
                    <button
                        onClick={() => setView("list")}
                        className="bg-blue-600 text-black px-4 py-2 rounded hover:bg-blue-700 transition"
                    >
                        トレーニング一覧
                    </button>
                </div>

                {/* ロード/エラー */}
                {loading && <p className="text-gray-500">読み込み中...</p>}
                {error && <p className="text-red-600">エラーが発生しました。</p>}

                {/* フォーム or 一覧 */}
                {view === "form" && <WorkoutForm onSubmit={handleAddWorkout} />}
                {view === "list" && (
                    <WorkoutList
                        workouts={workouts}
                        onDeleteWorkout={removeWorkout}
                        selectedYear={selectedYear}
                        selectedMonth={selectedMonth}
                        setSelectedYear={setSelectedYear}
                        setSelectedMonth={setSelectedMonth}
                    />
                )}
            </div>
        </div>
    );



};

export default WorkoutPage;
