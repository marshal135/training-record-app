// src/types/workoutTypes.ts

export type WorkoutRequestDTO = {
    userId: number;
    exerciseId: number;
    weight: number;
    sets: number;
    reps: number;
    date: string;
};

export type WorkoutResponseDTO = {
    workoutId: number;
    name: string;
    category: string;
    workoutDate: string;
    weight: number;
    sets: number;
    reps: number;
};

export type Exercise = {
    exerciseId: number;
    name: string;
    category: string;
};