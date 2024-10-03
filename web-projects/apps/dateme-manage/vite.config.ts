import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react({
      tsDecorators: true,
    }),
  ],
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        rewrite: (path) => {
          return path;
        },
        changeOrigin: true,
      },
    },
  },
});
