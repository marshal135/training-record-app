import React from "react";
import type { WorkoutResponseDTO } from "../types/workoutTypes";
import Button from "./UI/Botton";

type Props = {
    workouts: WorkoutResponseDTO[];
    onDeleteWorkout: (id: number) => void;
    selectedYear: number;
    selectedMonth: number;
    setSelectedYear: (y: number) => void;
    setSelectedMonth: (m: number) => void;
};

/**
 * トレーニング記録の一覧を表示するコンポーネント
 *
 * 年月の切り替え・削除操作に対応
 */

const WorkoutList: React.FC<Props> = ({
    workouts,
    onDeleteWorkout,
    selectedYear,
    selectedMonth,
    setSelectedYear,
    setSelectedMonth,
}) => {
    return (
        <div className="max-w-5xl mx-auto mt-6 p-6 bg-white rounded-lg shadow-md">
            {/* 年月セレクト */}
            <div className="flex gap-4 mb-4 items-center">
                <div>
                    <label className="mr-2">年:</label>
                    <select
                        value={selectedYear}
                        onChange={(e) => setSelectedYear(Number(e.target.value))}
                        className="border rounded px-2 py-1"
                    >
                        {[2024, 2025, 2026].map((y) => (
                            <option key={y} value={y}>{y}</option>
                        ))}
                    </select>
                </div>

                <div>
                    <label className="mr-2">月:</label>
                    <select
                        value={selectedMonth}
                        onChange={(e) => setSelectedMonth(Number(e.target.value))}
                        className="border rounded px-2 py-1"
                    >
                        {Array.from({ length: 12 }, (_, i) => i + 1).map((m) => (
                            <option key={m} value={m}>{m}</option>
                        ))}
                    </select>
                </div>
            </div>

            {/* テーブル */}
            {workouts.length === 0 ? (
                <p className="text-gray-600 text-center">今月の記録はありません。</p>
            ) : (
                <div className="overflow-x-auto">
                    <table className="table-auto w-full border-collapse">
                        <thead>
                            <tr className="bg-gray-200 text-left">
                                <th className="px-4 py-2 border">日付</th>
                                <th className="px-4 py-2 border">カテゴリ</th>
                                <th className="px-4 py-2 border">種目</th>
                                <th className="px-4 py-2 border">重量</th>
                                <th className="px-4 py-2 border">セット</th>
                                <th className="px-4 py-2 border">回数</th>
                                <th className="px-4 py-2 border">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            {workouts.map((w) => (
                                <tr key={w.workoutId} className="hover:bg-gray-100">
                                    <td className="px-4 py-2 border">{new Date(w.workoutDate).toLocaleDateString("ja-JP")}</td>
                                    <td className="px-4 py-2 border">{w.category}</td>
                                    <td className="px-4 py-2 border">{w.name}</td>
                                    <td className="px-4 py-2 border">{w.weight}kg</td>
                                    <td className="px-4 py-2 border">{w.sets}set</td>
                                    <td className="px-4 py-2 border">{w.reps}rep</td>
                                    <td className="px-4 py-2 border text-center">
                                        <Button onClick={() => onDeleteWorkout(w.workoutId)} className="bg-red-500 hover:bg-red-600">
                                            削除
                                        </Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default WorkoutList;
