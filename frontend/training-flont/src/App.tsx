import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AuthPage from "./pages/AuthPage";
import WorkoutPage from "./pages/WorkoutPage";
import './index.css'; 

const App: React.FC = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<AuthPage />} />
                <Route path="/workout" element={<WorkoutPage />} />
            </Routes>
        </BrowserRouter>
    );
};

export default App;
