import { Ant, MenuPage, Pro } from "@common-module/common-antd";
import {
  createBrowserRouter,
  createGlobalStyle,
  RouterProvider,
} from "@common-module/common-react";
import { routerMenu, RouterMenuItem } from '@common-module/common-api'
import "./App.css";
import { RoleView } from "./views/RoleView";
import { UserView } from "./views/UserView";

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
        path: "role",
        name: "角色管理",
        element: <RoleView />,
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
      <MenuPage
        title="sss"
        userEmail="ssdf"
        routerMenuItems={router}
      ></MenuPage>
    ),
    children: routerMenu(router).routes,
  },
]);

function App() {
  return (
    <Ant.ConfigProvider
      theme={{
        algorithm: [Ant.theme.darkAlgorithm, Ant.theme.compactAlgorithm],
      }}
    >
      <Pro.ProConfigProvider dark={true}>
        <RouterProvider router={AppRouter} />
        <SGlobalStyle />
      </Pro.ProConfigProvider>
    </Ant.ConfigProvider>
  );
}

export default App;
