import { useState, useEffect } from "react";
import { fetchExercises, createExercise } from "../api/Api";
import type { Exercise, WorkoutRequestDTO } from "../types/workoutTypes";
import Button from "./UI/Botton";

type WorkoutFormProps = {
  onSubmit: (data: WorkoutRequestDTO) => void;
};

/**
 * トレーニング記録用フォームコンポーネント
 *
 * カテゴリ・種目を選択して記録を入力・保存できる
 * 新しいカテゴリ・種目の追加にも対応
 */

export default function WorkoutForm({ onSubmit }: WorkoutFormProps) {
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [selectedCategory, setSelectedCategory] = useState("");
  const [selectedExerciseId, setSelectedExerciseId] = useState<number>(0);

  const [form, setForm] = useState<Omit<WorkoutRequestDTO, "userId">>({
    exerciseId: 0,
    weight: 0,
    sets: 0,
    reps: 0,
    date: new Date().toISOString().slice(0, 10),
  });

  const [newCategory, setNewCategory] = useState("");
  const [newExerciseName, setNewExerciseName] = useState("");

  useEffect(() => {
    const load = async () => {
      const data = await fetchExercises();
      setExercises(data);
    };
    load();
  }, []);

  const uniqueCategories = [...new Set(exercises.map((e) => e.category))];
  const filteredExercises = exercises.filter((e) => e.category === selectedCategory);

  const handleCategoryChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const category = e.target.value;
    setSelectedCategory(category);
    const first = exercises.find((ex) => ex.category === category);
    if (first) {
      setSelectedExerciseId(first.exerciseId);
      setForm({ ...form, exerciseId: first.exerciseId });
    } else {
      setSelectedExerciseId(0);
      setForm({ ...form, exerciseId: 0 });
    }
  };

  const handleExerciseChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const id = parseInt(e.target.value);
    setSelectedExerciseId(id);
    setForm({ ...form, exerciseId: id });
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm({
      ...form,
      [name]: name === "weight" ? parseFloat(value) || 0 : name === "date" ? value : parseInt(value) || 0,
    });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({ ...form, userId: 1 });
  };

  const handleNewExerciseSubmit = async () => {
    if (!newCategory || !newExerciseName) return;
    await createExercise(newCategory, newExerciseName);
    const updated = await fetchExercises();
    setExercises(updated);
    setNewCategory("");
    setNewExerciseName("");
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="bg-white p-6 rounded-xl shadow-md w-full max-w-4xl mx-auto space-y-6"
    >
      <h2 className="text-xl font-bold text-gray-800 text-center">トレーニング記録フォーム</h2>

      {/* カテゴリ・種目 */}
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
        <div>
          <label className="block text-sm font-medium text-gray-700">カテゴリ選択</label>
          <select
            value={selectedCategory}
            onChange={handleCategoryChange}
            className="w-full px-4 py-2 border border-gray-300 rounded-md"
          >
            <option value="">-- 選択 --</option>
            {uniqueCategories.map((cat) => (
              <option key={cat} value={cat}>
                {cat}
              </option>
            ))}
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">種目選択</label>
          <select
            value={selectedExerciseId}
            onChange={handleExerciseChange}
            className="w-full px-4 py-2 border border-gray-300 rounded-md"
          >
            <option value="0">-- 選択 --</option>
            {filteredExercises.map((ex) => (
              <option key={ex.exerciseId} value={ex.exerciseId}>
                {ex.name}
              </option>
            ))}
          </select>
        </div>
      </div>

      {/* 記録入力 */}
      <div className="grid grid-cols-1 sm:grid-cols-4 gap-4">
        <div>
          <label className="block text-sm font-medium text-gray-700">重量 (kg)</label>
          <input
            type="number"
            name="weight"
            value={form.weight}
            onChange={handleChange}
            className="w-full px-4 py-2 border border-gray-300 rounded-md"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">セット数</label>
          <input
            type="number"
            name="sets"
            value={form.sets}
            onChange={handleChange}
            className="w-full px-4 py-2 border border-gray-300 rounded-md"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">回数</label>
          <input
            type="number"
            name="reps"
            value={form.reps}
            onChange={handleChange}
            className="w-full px-4 py-2 border border-gray-300 rounded-md"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">日付</label>
          <input
            type="date"
            name="date"
            value={form.date}
            onChange={handleChange}
            className="w-full px-4 py-2 border border-gray-300 rounded-md"
          />
        </div>
      </div>

      <Button type="submit">記録を保存</Button>

      <hr />

      {/* 新規追加 */}
      <h3 className="text-md font-semibold text-gray-800">新しいカテゴリ・種目を追加</h3>
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
        <input
          type="text"
          placeholder="新規カテゴリ"
          value={newCategory}
          onChange={(e) => setNewCategory(e.target.value)}
          className="w-full px-4 py-2 border border-gray-300 rounded-md"
        />
        <input
          type="text"
          placeholder="新規種目名"
          value={newExerciseName}
          onChange={(e) => setNewExerciseName(e.target.value)}
          className="w-full px-4 py-2 border border-gray-300 rounded-md"
        />
      </div>

      <Button type="button" onClick={handleNewExerciseSubmit}>
        種目を追加
      </Button>
    </form>
  );
}
