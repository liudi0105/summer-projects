import {
  MenuDataItem,
  PageContainer,
  ProLayout,
} from "@ant-design/pro-components";
import { RouterMenuItem } from "@liudi0105/core-api";
import { Outlet } from "@liudi0105/core-react";

export type MenuPageProps = {
  title: string;
  routerMenuItems: RouterMenuItem[];
  userEmail: string;
  onMenuItemClick: (menuDataItem: MenuDataItem) => void;
};

export const MenuPage = (props: MenuPageProps) => {
  const { userEmail, onMenuItemClick } = props;

  return (
    <ProLayout
      route={{
        path: "",
        routes: [],
      }}
      avatarProps={{
        src: "/assets/avatar.jpg",
        title: userEmail,
      }}
      menuItemRender={(item, dom) => (
        <div onClick={() => onMenuItemClick(item)}>{dom}</div>
      )}
      headerTitleRender={(logo, title) => (
        <a>
          {logo}
          {title}
        </a>
      )}
      fixSiderbar={true}
      layout="mix"
    >
      <PageContainer title={false}>
        <Outlet />
      </PageContainer>
    </ProLayout>
  );
};
