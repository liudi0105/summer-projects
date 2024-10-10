import {
  ConfigProvider,
  FloatButtonGroup
} from "@common-module/common-antd";
import { routerMenu, RouterMenuItem } from "@common-module/common-api";
import {
  createBrowserRouter,
  createGlobalStyle,
  RouterProvider,
} from "@common-module/common-react";
import "./App.css";
import { Layout } from "./pages/Layout";
import { Main } from "./pages/MainPage";

const SGlobalStyle = createGlobalStyle`
  html, body, #root {
    height: 100%;
    width: 100%;
    margin: 0;
    padding: 0;
  }

  pre {
    font-family: 'Cascadia Mono', 'Consolas', 'Courier New', Courier, monospace;
    margin: 0;
  }
`;

const router: RouterMenuItem[] = [
  {
    path: "welcome",
    name: "欢迎",
  },
  {
    path: "admin",
    name: "管理页",
    children: [
      {
        path: "user",
        name: "用户管理",
        element: <Main />,
      },
      {
        path: "role",
        name: "角色管理",
        element: "role",
      },
    ],
  },
];

const AppRouter = createBrowserRouter([
  {
    path: "login",
    element: "login",
  },
  {
    path: "*",
    element: (
    <Layout />
    ),
    children: routerMenu(router).routes,
  },
]);

function App() {
  return (
    <ConfigProvider>
      <RouterProvider router={AppRouter} />
      <SGlobalStyle />
      <FloatButtonGroup />
    </ConfigProvider>
  );
}

export default App;
