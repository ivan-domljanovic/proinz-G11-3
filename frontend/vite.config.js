import { defineConfig } from "vite";

export default defineConfig({
  server: {
    proxy: {
      "/me": {
        target: "http://127.0.0.1:8080",
        changeOrigin: true,
      },
      "/api": {
        target: "http://127.0.0.1:8080",
        changeOrigin: true,
      },
    },
  },
});
