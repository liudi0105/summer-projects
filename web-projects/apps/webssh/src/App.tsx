import {
  ConfigProvider,
  FloatButtonGroup,
  MenuPage,
} from "@common-module/common-antd";
import { routerMenu, RouterMenuItem } from "@common-module/common-api";
import {
  createBrowserRouter,
  createGlobalStyle,
  RouterProvider,
} from "@common-module/common-react";
import "./App.css";
import { TerminalView } from "./pages/TerminalView";
import { UserView } from "./pages/UserView";
import { ServerView } from "./pages/ServerView";

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
        element: <UserView />,
      },
      {
        path: "server",
        name: "服务器管理",
        element: <ServerView />,
      },
      {
        path: "terminal",
        name: "Terminal",
        element: <TerminalView />,
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
    path: "terminal",
    element: <TerminalView />,
  },
  {
    path: "*",
    element: (
      <MenuPage
        title="Dateme"
        userEmail="小明"
        routerMenuItems={router}
      />
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
