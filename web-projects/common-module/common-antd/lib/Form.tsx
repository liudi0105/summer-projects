import * as Pro from "@ant-design/pro-components";
import { Button } from "antd";

export type ModalFormProps = {
  onOk?: (e: React.MouseEvent<HTMLButtonElement>) => void;
  onCancel?: (e: React.MouseEvent<HTMLButtonElement>) => void;
} & Pro.ModalFormProps;

export const ModalForm = (props: ModalFormProps) => {
  const {
    layout = "horizontal",
    labelCol = { span: 6 },
    width = 500,
    onOk,
    onCancel,
  } = props;

  return (
    <Pro.ModalForm
      layout={layout}
      labelCol={labelCol}
      width={width}
      {...props}
      modalProps={{ destroyOnClose: true, ...props.modalProps, onOk, onCancel }}
    />
  );
};

export const ButtonModalFrom = (props: ModalFormProps) => {
  return <ModalForm trigger={<Button>{props.title}</Button>} {...props} />;
};
