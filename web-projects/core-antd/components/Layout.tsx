import { ProLayout, ProLayoutProps } from "@ant-design/pro-components";
import { createGlobalStyle } from "@liudi0105/core-react";

export type LayoutProps = {} & ProLayoutProps;

const SLayout = createGlobalStyle`
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

export const Layout = (props: LayoutProps) => {
  return (
    <SLayout>
      <ProLayout {...props}></ProLayout>
    </SLayout>
  );
};
