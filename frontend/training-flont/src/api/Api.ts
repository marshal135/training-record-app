import axios from "axios";
import type { WorkoutRequestDTO, WorkoutResponseDTO } from "../types/workoutTypes";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

/** Workout関連のAPI通信をまとめたユーティリティ */
export const fetchWorkouts = async (
    userId: number,
    year: number,
    month: number
): Promise<WorkoutResponseDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/workout`, {
        params: { userId, year, month },
    });
    return response.data;
};

/** Workoutを新規登録するためのAPI */
export const createWorkout = async (workout: WorkoutRequestDTO): Promise<void> => {
    await axios.post(`${API_BASE_URL}/workout`, workout);
};

/** Workoutを削除するAPI */
export const deleteWorkout = async (id: number): Promise<void> => {
    await axios.delete(`${API_BASE_URL}/workout/${id}`);
};

/** 種目一覧を取得するAPI */
export const fetchExercises = async () => {
    const response = await axios.get(`${API_BASE_URL}/exercisemaster/exercises`);
    return response.data;
};

/** 新しい種目を登録するAPI */
export const createExercise = async (category: string, name: string): Promise<void> => {
    await axios.post(`${API_BASE_URL}/exercisemaster/exercise`, {
        category,
        name,
    });
};
