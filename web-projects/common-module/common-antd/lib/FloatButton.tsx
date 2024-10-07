import {
  FullscreenOutlined,
  MoonOutlined,
  SettingOutlined
} from "@ant-design/icons";
import { FloatButton } from "antd";

export const FloatButtonGroup = () => {
  return (
    <FloatButton.Group
      trigger="hover"
      type="primary"
      icon={<SettingOutlined />}
    >
      <FloatButton icon={<FullscreenOutlined />} />
      <FloatButton icon={<MoonOutlined />} />
    </FloatButton.Group>
  );
};
