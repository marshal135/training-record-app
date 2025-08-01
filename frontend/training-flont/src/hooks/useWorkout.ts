import { useState, useEffect } from "react";
import type { WorkoutRequestDTO, WorkoutResponseDTO } from "../types/workoutTypes";
import { fetchWorkouts, createWorkout, deleteWorkout } from "../api/Api";

/**
 * Workout の取得・登録・削除を扱うカスタムフック
 * 
 * 年月とユーザーごとにデータを管理し、再読み込みにも対応
 */

export const useWorkout = (
    userId: number,
    year: number,
    month: number
) => {
    const [workouts, setWorkouts] = useState<WorkoutResponseDTO[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);

    const loadWorkouts = async () => {
        try {
            setLoading(true);
            const data = await fetchWorkouts(userId, year, month);
            setWorkouts(data);
        } catch (err) {
            setError("データの取得に失敗しました");
        } finally {
            setLoading(false);
        }
    };
    //登録処理
    const addWorkout = async (workout: WorkoutRequestDTO) => {
        try {
            await createWorkout(workout);
            await loadWorkouts();
        } catch (err) {
            setError("登録に失敗しました");
        }
    };

    //削除処理
    const removeWorkout = async (id: number) => {
        try {
            await deleteWorkout(id);
            await loadWorkouts();
        } catch (err) {
            setError("削除に失敗しました");
        }
    };

    //初期読み込み
    useEffect(() => {
        loadWorkouts();
    }, [userId, year, month]);

    return {
        workouts,
        loading,
        error,
        addWorkout,
        removeWorkout,
        reload: loadWorkouts,
    };
};