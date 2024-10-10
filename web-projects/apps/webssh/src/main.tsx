import { createRoot } from "react-dom/client";
import "reflect-metadata";
import App from "./App.tsx";
import "./index.css";
import '/node_modules/@xterm/xterm/css/xterm.css'

createRoot(document.getElementById("root")!).render(<App />);
