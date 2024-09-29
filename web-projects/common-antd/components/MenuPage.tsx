import { PageContainer } from "@ant-design/pro-components";
import { routerMenu, RouterMenuItem } from "@liudi0105/core-api";
import { Outlet, useNavigate } from "@liudi0105/core-react";
import { Layout } from "./Layout";

export type MenuPageProps = {
  title: string;
  routerMenuItems: RouterMenuItem[];
  userEmail: string;
};

export const MenuPage = (props: MenuPageProps) => {
  const { routerMenuItems, userEmail } = props;

  const routerMenus = routerMenu(routerMenuItems);

  const navigate = useNavigate();

  return (
    <Layout
      route={{
        path: "/",
        routes: routerMenus.menus,
      }}
      avatarProps={{
        src: "/assets/avatar.jpg",
        title: userEmail,
      }}
      menuItemRender={(item, dom) => (
        <div
          onClick={() => {
            navigate(item.path || "/welcome");
          }}
        >
          {dom}
        </div>
      )}
      headerTitleRender={(logo, title) => (
        <a>
          {logo}
          {title}
        </a>
      )}
      fixSiderbar={true}
      defaultCollapsed={false}
      menu={{ defaultOpenAll: false, type: "sub" }}
      layout="mix"
      splitMenus={false}
      {...props}
    >
      <PageContainer title={false}>
        <Outlet />
      </PageContainer>
    </Layout>
  );
};
