import { createRoot } from "react-dom/client";
import "reflect-metadata";
import App from "./App.tsx";
import "./index.css";
import "/node_modules/@xterm/xterm/css/xterm.css";
import { registerConfig } from "@common-module/common-api";

registerConfig({
  apiPrefix: "/api",
});

createRoot(document.getElementById("root")!).render(<App />);
