import * as Pro from "@ant-design/pro-components";
import { Button } from "antd";

export type ModalFormProps = {} & Pro.ModalFormProps;

export const ModalFrom = (props: ModalFormProps) => {
  return <Pro.ModalForm {...props} />;
};

export const ButtonModalFrom = (props: ModalFormProps) => {
  return <Pro.ModalForm {...props} trigger={<Button>{props.title}</Button>} />;
};
