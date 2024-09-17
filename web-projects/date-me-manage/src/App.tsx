import { CrownFilled, Layout } from "@liudi0105/core-antd";
import "./App.css";

function App() {
  return (
    <Layout
      route={{
        path: "/",
        routes: [
          {
            path: "/welcome",
            name: "欢迎",
            component: "./Welcome",
          },
          {
            path: "/admin",
            name: "管理页",
            access: "canAdmin",
            component: "./Admin",
            routes: [
              {
                path: "/admin/sub-page1",
                name: "用户管理",
                component: "./Welcome",
              },
              {
                path: "/admin/sub-page2",
                name: "角色管理",
                icon: <CrownFilled />,
                component: "./Welcome",
              },
            ],
          },
        ],
      }}
    ></Layout>
  );
}

export default App;
