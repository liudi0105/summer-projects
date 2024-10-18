import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "reflect-metadata";
import App from "./App.tsx";
import "./index.css";
import { registerConfig } from "@common-module/common-api";

registerConfig({
  apiUrl: '/api'
})

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <App />
  </StrictMode>
);
