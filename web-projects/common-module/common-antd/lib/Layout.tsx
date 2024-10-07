import { ProLayout, ProLayoutProps } from "@ant-design/pro-components";

export type LayoutProps = {} & ProLayoutProps;

export const Layout = (props: LayoutProps) => {
  return (
    <ProLayout {...props}>
    </ProLayout>
  );
};
