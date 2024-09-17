import { MenuPage } from "@liudi0105/core-antd";
import "./App.css";

function App() {
  return (
    <MenuPage
      title="sss"
      userEmail="ssdf"
      onMenuItemClick={() => {}}
      routerMenuItems={[
        {
          path: "/welcome",
          name: "欢迎",
        },
        {
          path: "/admin",
          name: "管理页",
          routes: [
            {
              path: "/admin/sub-page1",
              name: "用户管理",
            },
            {
              path: "/admin/sub-page2",
              name: "角色管理",
            },
          ],
        },
      ]}
    ></MenuPage>
  );
}

export default App;
