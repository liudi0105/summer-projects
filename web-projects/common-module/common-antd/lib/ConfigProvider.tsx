import * as Ant from "antd";
import * as Pro from "@ant-design/pro-components";

export const ConfigProvider = (props: Ant.ConfigProviderProps) => {
  const { children } = props;

  return (
    <Ant.ConfigProvider
      theme={{
        algorithm: [Ant.theme.darkAlgorithm, Ant.theme.compactAlgorithm],
      }}
    >
      <Pro.ProConfigProvider dark={true}>{children}</Pro.ProConfigProvider>
    </Ant.ConfigProvider>
  );
};
